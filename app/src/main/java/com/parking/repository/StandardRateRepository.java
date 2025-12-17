package com.parking.repository;

public class StandardRateRepository implements RateRepository {

    private final double rate;

    // Default Constructor rate
    public StandardRateRepository() {
        this.rate = 20.0;
    }

    // for custom rate
    public StandardRateRepository(double rate) {
        this.rate = rate;
    }

    @Override
    public double getHourlyRate() {
        return this.rate;
    }
}