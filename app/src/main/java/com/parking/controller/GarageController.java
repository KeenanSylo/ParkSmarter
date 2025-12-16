package com.parking.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.parking.model.GarageSpot;
import com.parking.model.Ticket;
import com.parking.model.Vehicle;
import com.parking.repository.SpotRepository;

public class GarageController {
    
    private final SpotRepository spotRepository;

    public GarageController(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public Ticket enterCar(Vehicle vehicle) {
        List<GarageSpot> availableSpots = spotRepository.findAvailableSpots();
        
        GarageSpot selectedSpot = null;

        // 1. If it's Electric, look for a charger spot first
        if (vehicle.isElectric()) {
            for (GarageSpot spot : availableSpots) {
                if (spot.hasCharger()) {
                    selectedSpot = spot;
                    break; // Found one! Stop looking.
                }
            }
        }

        // 2. If no charger spot found (or not electric), just take the first available one
        if (selectedSpot == null && !availableSpots.isEmpty()) {
            selectedSpot = availableSpots.get(0);
        }

        // 3. Park if we found a place
        if (selectedSpot != null) {
            selectedSpot.occupy(vehicle);
            return new Ticket(java.time.LocalDateTime.now());
        }

        return null; // Garage full
    }

    public boolean exitCar(int spotId) {
        // We need to find the spot by ID
        GarageSpot spot = spotRepository.findById(spotId);

        // If we found it and it's occupied by us, we vacate it
        if (spot != null && !spot.isEmpty()) {
            return spot.vacate();
        }
        
        return false;
    }
}