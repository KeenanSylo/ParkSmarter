package com.parking;

public class GarageSpot {
    private final int id;
    private boolean isEmpty;
    private Vehicle parkedVehicle;

    public GarageSpot(int id) {
        this.id = id;
        this.parkedVehicle = null;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return parkedVehicle == null;
    }

    public boolean occupy(Vehicle vehicle) {
        if (this.parkedVehicle != null) {
            return false; // Already occupied
        }
        this.parkedVehicle = vehicle;
        return true; // Success
    }

    public boolean vacate() {
        if (this.parkedVehicle == null) {
            return false; // Already empty
        }
        this.parkedVehicle = null;
        return true; // Success
    }

    // Getter to see which car is parked
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
