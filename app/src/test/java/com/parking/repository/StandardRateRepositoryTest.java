package com.parking.repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StandardRateRepositoryTest {

    @Test
    void shouldReturnDefaultRateOf20() {
        // I expect a class named StandardRateRepository to exist
        RateRepository repo = new StandardRateRepository(); 

        double rate = repo.getHourlyRate();

        assertEquals(20.0, rate, "Default rate should be 20.0");
    }

    @Test
    void shouldReturnConfiguredRate() {
        // We want a constructor that can takes a double
        RateRepository repo = new StandardRateRepository(50.0);

        double rate = repo.getHourlyRate();

        assertEquals(50.0, rate, "Should return the rate set in constructor");
    }
}