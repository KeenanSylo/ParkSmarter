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
}