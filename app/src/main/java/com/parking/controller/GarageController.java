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

    // this original method without exitTime parameter
    public double exitCar(int spotId) {
        return exitCar(spotId, LocalDateTime.now());
    }

    // new method with exitTime parameter 
    public double exitCar(int spotId, LocalDateTime exitTime) {
        GarageSpot spot = spotRepository.findById(spotId);

        if (spot != null && !spot.isEmpty()) {
            double price = 0.0;
            Ticket ticket = spot.getTicket();
            
            if (ticket != null) { 
                // now i use exitTime to calculate price
                price = pricingService.calculatePrice(
                    ticket.getEntryTime(), 
                    exitTime 
                );
            }

            spot.vacate();
            return price; 
        }
        return -1.0;
    }
}