package com.parking.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.parking.model.GarageSpot;
import com.parking.model.Ticket;
import com.parking.model.Vehicle;
import com.parking.repository.SpotRepository;
import com.parking.service.PricingService;

public class GarageController {
    
    private final SpotRepository spotRepository;
    private final PricingService pricingService;

    public GarageController(SpotRepository spotRepository, PricingService pricingService) {
        this.spotRepository = spotRepository;
        this.pricingService = pricingService;
    }

    public GarageSpot enterCar(Vehicle vehicle) {
        List<GarageSpot> availableSpots = spotRepository.findAvailableSpots();
        
        // For when its full
        if (availableSpots.isEmpty()) {
            return null;
        }

        // take the first available spot
        GarageSpot spot = availableSpots.get(0);
        Ticket ticket = new Ticket(LocalDateTime.now());

        spot.occupy(vehicle, ticket);
        
        return spot;
    }

    public double exitCar(int spotId) {
        GarageSpot spot = spotRepository.findById(spotId);

        if (spot != null && !spot.isEmpty()) {
            double price = 0.0;
            Ticket ticket = spot.getTicket();
            
            if (ticket != null) { 
                price = pricingService.calculatePrice(
                    ticket.getEntryTime(), 
                    LocalDateTime.now()
                );
            }

            spot.vacate();
            return price; 
        }
        return -1.0;
    }
}