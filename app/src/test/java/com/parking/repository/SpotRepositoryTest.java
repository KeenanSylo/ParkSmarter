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

    @Test
    void returnNullWhenIdNotFound() { // unknown ID (99 for example)
        GarageSpot spot = repository.findById(99);

        assertNull(spot, "Should return null for unknown ID");
    }

    @Test
    void shouldFindEmptySpotsOnly() {
        GarageSpot spot1 = repository.findById(1); // we occupy spot 1
        spot1.occupy(new com.parking.model.Vehicle("ABC 111", "Lexus", "LFA", "White"));

        List<GarageSpot> available = repository.findAvailableSpots(); // we ask for empty spots

        // we should get 4 empty spots back
        assertEquals(4, available.size(), "Should only return empty spots");
    }
}