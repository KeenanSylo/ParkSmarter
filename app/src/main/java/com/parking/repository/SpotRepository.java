package com.parking.repository;

import com.parking.model.GarageSpot;
import java.util.List;

public interface SpotRepository {
    List<GarageSpot> getAllSpots();

    GarageSpot findById(int id); // find a spot by its ID

    List<GarageSpot> findAvailableSpots();
}