package com.parking.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

  @Test
  void testVehicleCreation() {
    Vehicle truck = new Vehicle("ABC-999", "Ford", "F-150", "Blue");

    assertEquals("ABC-999", truck.getLicensePlate());
    assertEquals("Ford", truck.getBrand());
    assertEquals("F-150", truck.getModel());
    assertEquals("Blue", truck.getColor());
  }

@Test
  void shouldThrowException_WhenLicensePlateIsInvalid() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle(null, "Ford", "Mustang", "Black");
    }, "Should block null plates");

    // Test Bad Format
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle("BAD-PLATE", "Ford", "Mustang", "Black");
    }, "Should block invalid formats");
  }

   @Test
  void brandCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle("ABC-123", null, "Model S", "Red");
    }, "Should verify brand is not null");
  }

  @Test
  void colorCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle("ABC-123", "Tesla", "Model S", null);
    }, "Should verify color is not null");
  }
}