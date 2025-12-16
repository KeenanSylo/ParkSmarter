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
  void licensePlateCannotBeNull() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle(null, "Ford", "Mustang", "Black");
    }, "Should verify license plate is not null");
  }

  @Test
  void licensePlateCannotBeEmpty() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle("", "Ford", "Mustang", "Black");
    }, "Should verify license plate is not empty");
  }

  @Test
  void licensePlateCannotBeBlank() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Vehicle("   ", "Ford", "Mustang", "Black");
    }, "Should verify license plate is not whitespace only");
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