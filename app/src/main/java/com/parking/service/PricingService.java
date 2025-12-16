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
        long hours = duration.toHours();

        // we get rate from repository
        double rate = rateRepository.getHourlyRate();

        return hours * rate;
    }
}