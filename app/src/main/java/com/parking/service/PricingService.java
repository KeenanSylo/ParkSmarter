package com.parking.service;

import com.parking.repository.RateRepository;
import java.time.Duration;
import java.time.LocalDateTime;

public class PricingService {

    private final RateRepository rateRepository;

    public PricingService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public double calculatePrice(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        
        // I use ceiling to round up partial hours
        long minutes = duration.toMinutes();
        double hours = Math.ceil(minutes / 60.0);

        double rate = rateRepository.getHourlyRate();
        return hours * rate;
    }
}