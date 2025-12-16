package com.parking.model;

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
    void shouldBecomeEmptyWhenVacated() {
        GarageSpot spot = new GarageSpot(1);
        Vehicle car = new Vehicle("CAR-1", "Jeep", "Wrangler", "Black", false);
        
        spot.occupy(car); // Fill it first

        assertTrue(spot.vacate(), "Vacate should return true");
        assertTrue(spot.isEmpty(), "Spot should be empty now");
        assertNull(spot.getParkedVehicle(), "Vehicle reference should be gone");
    }

    @Test
    void shouldReturnFalseWhenVacatingEmptySpot() {
        GarageSpot spot = new GarageSpot(1);
        
        // Try to leave an empty spot
        boolean result = spot.vacate();

        assertFalse(result, "Need to return false because spot is already empty");
    }

    @Test
    void shouldStoreChargerStatus() {
        // this is for spots with chargers
        GarageSpot spotWithCharger = new GarageSpot(2, true);
        assertTrue(spotWithCharger.hasCharger(), "Spot should have a charger");

        // this is for spots without chargers
        GarageSpot spotWithoutCharger = new GarageSpot(3, false);
        assertFalse(spotWithoutCharger.hasCharger(), "Spot should NOT have a charger");
    }

    @Test
    void shouldStoreTicketWhenOccupying() {
        // ARRANGE
        GarageSpot spot = new GarageSpot(1);
        Vehicle car = new Vehicle("ABC-123", "Ford", "Mustang", "Red", false);
        Ticket ticket = new Ticket(java.time.LocalDateTime.now());

        // We want occupy to take both the car and also the ticket
        boolean success = spot.occupy(car, ticket);

        assertTrue(success, "Should occupy successfully");
        assertEquals(ticket, spot.getTicket(), "Spot should hold the ticket");
    }
}