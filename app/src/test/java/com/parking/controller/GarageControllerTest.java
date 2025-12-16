package com.parking.controller;

import com.parking.model.GarageSpot;
import com.parking.model.Ticket;
import com.parking.model.Vehicle;
import com.parking.repository.SpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GarageControllerTest {

    @Mock
    SpotRepository spotRepository;

    GarageController controller;

    @BeforeEach
    void setUp() {
        controller = new GarageController(spotRepository);
    }

    @Test
    void shouldInitializeSuccessfully() {
        assertNotNull(controller, "Controller should exist");
    }

    @Test
    void receiveTicketWhenSpotIsAvailable() {
        Vehicle car = new Vehicle("ABC-111", "Lexus", "LFA", "White", false);
        GarageSpot emptySpot = new GarageSpot(1);

        // Use mock to simulate available spot
        when(spotRepository.findAvailableSpots()).thenReturn(List.of(emptySpot));

        Ticket ticket = controller.enterCar(car);

        assertNotNull(ticket, "Should receive a ticket");
    }

    @Test
    void rejectEntryWhenNoSpotsAvailable() {
        Vehicle car = new Vehicle("ABC-123", "Ford", "Mustang", "Red", false);

        // We tell the mock repository to return no available spots
        when(spotRepository.findAvailableSpots()).thenReturn(java.util.Collections.emptyList());

        Ticket ticket = controller.enterCar(car);

        assertNull(ticket, "Should NOT receive a ticket when full");
    }

    @Test
    void shouldAllowExitWhenVehicleIsParked() {
        GarageSpot occupiedSpot = new GarageSpot(1);
        occupiedSpot.occupy(new Vehicle("ABC-123", "Lexus", "LFA", "Red", false));

        // We tell the mock repository to return the occupied spot when searched by ID
        when(spotRepository.findById(1)).thenReturn(occupiedSpot);

        // Now we try to exit
        boolean success = controller.exitCar(1);

        assertTrue(success, "Exit should be successful");
        assertTrue(occupiedSpot.isEmpty(), "Spot should now be empty");
    }

    @Test
    void shouldFailExitWhenSpotIsAlreadyEmpty() {
        GarageSpot emptySpot = new GarageSpot(2);
        
        // Mock the repository to return this empty spot
        when(spotRepository.findById(2)).thenReturn(emptySpot);

        boolean success = controller.exitCar(2);

        assertFalse(success, "Should return false because spot was already empty");
    }

    @Test
    void shouldFailExitWhenSpotDoesNotExist() {
        when(spotRepository.findById(99)).thenReturn(null);

        boolean success = controller.exitCar(99);

        assertFalse(success, "Should return false for unknown spot ID"); // spot does not exist
    }

    @Test
    void shouldParkElectricVehicle_InSpotWithCharger() {
        Vehicle electricCar = new Vehicle("EV-333", "Tesla", "3", "White", true); // true = Electric

        GarageSpot regularSpot = new GarageSpot(1, false); // No Charger
        GarageSpot chargingSpot = new GarageSpot(2, true); // Has Charger

        // We mock the repository to return both spots as available
        when(spotRepository.findAvailableSpots()).thenReturn(List.of(regularSpot, chargingSpot));

        // ACT
        controller.enterCar(electricCar);

        // ASSERT
        assertTrue(regularSpot.isEmpty(), "EV should skip the regular spot");
        assertFalse(chargingSpot.isEmpty(), "EV should take the spot with charger");
        assertEquals(electricCar, chargingSpot.getParkedVehicle());
    }
}