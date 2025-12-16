package com.parking.service;

import com.parking.repository.RateRepository;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {

    @Test
    void shouldCalculatePriceUsingRepoRate() {
        RateRepository mockRepo = mock(RateRepository.class);
        // use mock to return 20.0 as hourly rate
        when(mockRepo.getHourlyRate()).thenReturn(20.0);

        PricingService service = new PricingService(mockRepo);

        LocalDateTime start = LocalDateTime.of(2023, 10, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2023, 10, 1, 14, 0); // 2 Hours

        double price = service.calculatePrice(start, end);

        assertEquals(40.0, price, "Should use rate from repo (20.0) * 2 hours");
        
        // Verify dependency usage
        verify(mockRepo).getHourlyRate();
    }

    @Test
    void shouldRoundUpPartialHours() {
        RateRepository mockRepo = mock(RateRepository.class);
        when(mockRepo.getHourlyRate()).thenReturn(10.0);
        PricingService service = new PricingService(mockRepo);

        // 1 hour 5 minutes
        LocalDateTime start = LocalDateTime.of(2023, 10, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2023, 10, 1, 13, 5); 

        double price = service.calculatePrice(start, end);

        // I expect 2 hours * 10.0 = 20.0
        assertEquals(20.0, price, "1h 5m should be charged as 2 hours");
    }

    @Test
    void shouldThrowExceptionWhenEndBeforeStart() {
        RateRepository mockRepo = mock(RateRepository.class);
        PricingService service = new PricingService(mockRepo);

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.minusHours(1); // not possible

        assertThrows(IllegalArgumentException.class, () -> {
            service.calculatePrice(start, end);
        });
    }
}