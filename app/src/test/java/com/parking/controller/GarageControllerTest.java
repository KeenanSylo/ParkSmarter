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
}