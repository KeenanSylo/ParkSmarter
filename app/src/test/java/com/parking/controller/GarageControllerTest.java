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
    void shouldAllowExit_WhenVehicleIsParked() {
        GarageSpot occupiedSpot = new GarageSpot(1);
        occupiedSpot.occupy(new Vehicle("ABC-123", "Lexus", "LFA", "Red", false));

        // We tell the mock repository to return the occupied spot when searched by ID
        when(spotRepository.findById(1)).thenReturn(occupiedSpot);

        // Now we try to exit
        boolean success = controller.exitCar(1);

        assertTrue(success, "Exit should be successful");
        assertTrue(occupiedSpot.isEmpty(), "Spot should now be empty");
    }
}