package com.parking;

public class GarageSpot {
    private final int id;
    private boolean isEmpty;

    public GarageSpot(int id) {
        this.id = id;
        this.isEmpty = true;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean occupy() {
        if (!this.isEmpty) {
            return false; // Already occupied
        }
        this.isEmpty = false;
        return true; // Success
    }

    public boolean vacate() {
        if (this.isEmpty) {
            return false; // Already empty
        }
        this.isEmpty = true;
        return true; // Success
    }
}
