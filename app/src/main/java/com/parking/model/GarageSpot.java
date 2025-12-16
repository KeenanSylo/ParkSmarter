package com.parking.model;

public class GarageSpot {
    private final int id;
    private Vehicle parkedVehicle;
    private final boolean hasCharger;
    private Ticket currentTicket;

    public GarageSpot(int id, boolean hasCharger) {
        this.id = id;
        this.parkedVehicle = null;
        this.hasCharger = hasCharger;
    }

    public GarageSpot(int id) {
        this(id, false);
    }

    public Ticket getTicket() {
        return currentTicket;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return parkedVehicle == null;
    }

    public boolean occupy(Vehicle vehicle, Ticket ticket) {
        if (this.parkedVehicle != null) {
            return false;
        }
        this.parkedVehicle = vehicle;
        this.currentTicket = ticket; // Now we assign the ticket
        return true;
    }

    public boolean occupy(Vehicle vehicle) {
        return occupy(vehicle, null); 
    }

    public boolean vacate() {
        if (this.parkedVehicle == null) {
            return false; // Already empty
        }
        this.parkedVehicle = null;
        this.currentTicket = null; // Clear the ticket when vacating
        return true; // Success
    }

    // Getter to see which car is parked
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean hasCharger() {
        return hasCharger;
    }
}
