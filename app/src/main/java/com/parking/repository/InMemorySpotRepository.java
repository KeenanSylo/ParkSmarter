package com.parking.repository;

import com.parking.model.GarageSpot;
import java.util.ArrayList;
import java.util.List;

public class InMemorySpotRepository implements SpotRepository {

    private final List<GarageSpot> spots;

    public InMemorySpotRepository(int capacity) {
        this.spots = new ArrayList<>();
        // Create the requested number of spots
        for (int i = 1; i <= capacity; i++) {
            this.spots.add(new GarageSpot(i));
        }
    }

    @Override
    public List<GarageSpot> getAllSpots() {
        // Return a copy of the list eller the list itself
        return new ArrayList<>(spots);
    }

    @Override
    public GarageSpot findById(int id) {
        for (GarageSpot spot : spots) { // it loops through the list to find spot with same ID
            if (spot.getId() == id) {
                return spot;
            }
        }
        return null;
    }
}