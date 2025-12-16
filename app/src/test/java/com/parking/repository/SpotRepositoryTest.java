package com.parking.repository;

import com.parking.model.GarageSpot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpotRepositoryTest {

    private SpotRepository repository;

    @BeforeEach
    void setUp() { // Initialize with 5 spots for testing
        repository = new InMemorySpotRepository(5);
    }

    @Test
    void shouldInitializeWithCorrectCapacity() {
        List<GarageSpot> spots = repository.getAllSpots(); //act

        assertEquals(5, spots.size(), "Should have created exactly 5 spots");
    }

    @Test
    void shouldFindSpotById() {
        GarageSpot spot = repository.findById(2); // I try to ask for Spot #2

        assertNotNull(spot, "Should find the spot");
        assertEquals(2, spot.getId(), "Should be ID 2");
    }
}