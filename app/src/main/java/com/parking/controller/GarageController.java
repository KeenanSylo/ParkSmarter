package com.parking.controller;

import com.parking.repository.SpotRepository;

public class GarageController {
    
    private final SpotRepository spotRepository;

    public GarageController(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }
}