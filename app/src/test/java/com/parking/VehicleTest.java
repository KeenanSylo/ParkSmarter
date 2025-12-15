package com.parking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

  @Test
  void testElectricVehicleCreation() {
    Vehicle car = new Vehicle("ABC-123", "Tesla", "Model S", "Red", true);

    assertEquals("ABC-123", car.getLicensePlate());
    assertEquals("Tesla", car.getBrand());
    assertEquals("Model S", car.getModel());
    assertEquals("Red", car.getColor());
    assertTrue(car.isElectric(), "Should be identified as electric");
  }

  @Test
  void testGasVehicleCreation() {
    Vehicle truck = new Vehicle("XYZ-999", "Ford", "F-150", "Blue", false);

    assertEquals("XYZ-999", truck.getLicensePlate());
    assertEquals("Ford", truck.getBrand());
    assertEquals("F-150", truck.getModel());
    assertEquals("Blue", truck.getColor());
    assertFalse(truck.isElectric(), "Should NOT be electric");
  }
}