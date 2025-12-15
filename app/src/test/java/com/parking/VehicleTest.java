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
}