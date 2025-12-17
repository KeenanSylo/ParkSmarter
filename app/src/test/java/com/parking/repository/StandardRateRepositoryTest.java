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
}