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
    void shouldAcceptVehicleWhenEmpty() {
        GarageSpot spot = new GarageSpot(1);
        Vehicle car = new Vehicle("ABC-123", "Toyota", "Camry", "Grey", false);
        
        boolean success = spot.occupy(car);
        
        assertTrue(success, "Occupy should return true");
        assertFalse(spot.isEmpty(), "Spot should NOT be empty");
        assertEquals(car, spot.getParkedVehicle(), "Spot should hold the specific car");
    }

    @Test
    void shouldBecomeOccupied() { // Spot become occupied
        GarageSpot spot = new GarageSpot(1);
        assertTrue(spot.occupy(), "Occupy should return true on empty spot");
        
        assertFalse(spot.isEmpty(), "Spot should NOT be empty after occupy");
    }

    @Test
    void shouldRejectVehicleWhenAlreadyOccupied() {
        GarageSpot spot = new GarageSpot(1);
        Vehicle car1 = new Vehicle("ABC-123", "Toyota", "Camry", "Grey", false);
        Vehicle car2 = new Vehicle("XYZ-999", "Ford", "Mustang", "Red", false);

        spot.occupy(car1); // First car enters

        boolean success = spot.occupy(car2); // Second car tries to enter

        assertFalse(success, "Should return false because spot is full");
        assertEquals(car1, spot.getParkedVehicle(), "Original car should still be there");
    }

    @Test
    void shouldBecomeEmptyWhenVacated() { // Spot become empty when vacated
        GarageSpot spot = new GarageSpot(1);
        assertTrue(spot.occupy(), "occupy should succeed");
        assertTrue(spot.vacate(), "Vacate should return true on occupied spot");

        assertTrue(spot.isEmpty(), "Spot should be empty after vacate");
    }

    @Test
    void shouldReturnFalseWhenOccupyingFullSpot() {
        GarageSpot spot = new GarageSpot(1);
        assertTrue(spot.occupy(), "Setup: first occupy should succeed");

        // Second time fails
        boolean result = spot.occupy();

        assertFalse(result, "Need to return false because spot is already full");
    }

    @Test
    void shouldReturnFalseWhenVacatingEmptySpot() {
        GarageSpot spot = new GarageSpot(1);
        
        // Try to leave an empty spot
        boolean result = spot.vacate();

        assertFalse(result, "Need to return false because spot is already empty");
    }
}