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

    public void occupy() {
        this.isEmpty = false;
    }

    public void vacate() {
        this.isEmpty = true;
    }
}
