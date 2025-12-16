package com.parking.controller;

import com.parking.repository.SpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
        com.parking.model.Vehicle car = new com.parking.model.Vehicle("ABC-111", "Lexus", "LFA", "White", false);
        com.parking.model.GarageSpot emptySpot = new com.parking.model.GarageSpot(1);

        // Use mock to simulate available spot
        org.mockito.Mockito.when(spotRepository.findAvailableSpots()).thenReturn(java.util.List.of(emptySpot));

        com.parking.model.Ticket ticket = controller.enterCar(car);

        assertNotNull(ticket, "Should receive a ticket");
    }
}