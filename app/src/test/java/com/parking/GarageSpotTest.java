package com.parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GarageSpotTest {

    @Test
    void shouldHaveId() { // Spot have ID
        GarageSpot spot = new GarageSpot(1);
        assertEquals(1, spot.getId(), "Spot should store its ID");
    }

    @Test
    void shouldStartEmpty() { // new spot is empty
        GarageSpot spot = new GarageSpot(1);
        assertTrue(spot.isEmpty(), "New spot should be free");
    }

    @Test
    void shouldBecomeOccupied() { // Spot become occupied
        GarageSpot spot = new GarageSpot(1);
        
        spot.occupy();
        
        assertFalse(spot.isEmpty(), "Spot should NOT be empty after occupy");
    }

    @Test
    void shouldBecomeEmptyWhenVacated() { // Spot become empty when vacated
        GarageSpot spot = new GarageSpot(1);
        spot.occupy(); 
        spot.vacate();

        assertTrue(spot.isEmpty(), "Spot should be empty after vacate");
    }
}