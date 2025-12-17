package com.parking.repository;

public class StandardRateRepository implements RateRepository {

    @Override
    public double getHourlyRate() {
        return 20.0;
    }
}