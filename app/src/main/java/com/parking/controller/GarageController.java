package com.parking.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.parking.model.GarageSpot;
import com.parking.model.Ticket;
import com.parking.model.Vehicle;
import com.parking.repository.SpotRepository;

public class GarageController {
    
    private final SpotRepository spotRepository;
    private static final double ticketRate = 10.0;

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

    public double exitCar(int spotId) {
        GarageSpot spot = spotRepository.findById(spotId);

        if (spot != null && !spot.isEmpty()) {
            double price = 0.0;
            
            Ticket ticket = spot.getTicket();
            
            if (ticket != null) { // calculate price only if ticket exists
                price = ticket.calculatePrice(LocalDateTime.now(), ticketRate);
            }

            spot.vacate(); // vacate the spot
            
            return price; 
        }
        return 0.0; // Return 0 if spot was empty or invalid
    }
}