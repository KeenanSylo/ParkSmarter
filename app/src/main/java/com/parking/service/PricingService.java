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
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        Duration duration = Duration.between(start, end);
        long minutes = duration.toMinutes();

        // If duration is positive but less than 1 minute, treat as 1 minute
        if (minutes == 0 && !duration.isZero()) {
            minutes = 1;
        }

        double hours = Math.ceil(minutes / 60.0);
        
        // Ensure minimum 1 hour charge
        if (hours < 1.0) hours = 1.0;

        double rate = rateRepository.getHourlyRate();
        return hours * rate;
    }
}