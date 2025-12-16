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
}