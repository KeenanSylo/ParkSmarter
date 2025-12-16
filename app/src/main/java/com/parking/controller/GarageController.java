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
        
        // For when its full
        if (availableSpots.isEmpty()) {
            return null;
        }

        // take the first available spot
        GarageSpot spot = availableSpots.get(0);
        
        Ticket ticket = new Ticket(LocalDateTime.now());
        spot.occupy(vehicle, ticket);
        
        return ticket;
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