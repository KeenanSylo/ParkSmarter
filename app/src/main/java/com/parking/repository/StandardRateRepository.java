package com.parking.repository;

public class StandardRateRepository implements RateRepository {

    private final double rate;

    // Default Constructor rate
    public StandardRateRepository() {
        this.rate = 20.0;
    }

    // Constructor with custom rate
    public StandardRateRepository(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("Rate cannot be zero or negative");
        }
        this.rate = rate;
    }

    @Override
    public double getHourlyRate() {
        return this.rate;
    }
}