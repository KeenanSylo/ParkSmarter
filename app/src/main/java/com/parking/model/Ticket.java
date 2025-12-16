package com.parking.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Ticket {
    
    private final LocalDateTime entryTime;
    private final UUID id;
    private boolean isPaid;

    // Constructor
    public Ticket(LocalDateTime entryTime) {
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time cannot be null");
        }
        this.entryTime = entryTime;
        this.id = UUID.randomUUID();
        this.isPaid = false;
    }

    // Getter for entryTime
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    // Getter for id
    public UUID getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markPaid() {
        if (this.isPaid) {
            throw new IllegalStateException("Ticket is already paid");
        }
        this.isPaid = true;
    }

    public double calculatePrice(LocalDateTime exitTime, double hourlyRate) {
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("Exit time cannot be before entry time");
        }
        
        // we get the total minutes between entry and exit
        long totalMinutes = ChronoUnit.MINUTES.between(entryTime, exitTime);
        
        // calculate whole hours
        long hours = totalMinutes / 60;
        
        // check for remainder minutes
        long remainder = totalMinutes % 60;
        
        // if there's any remainder, we round up to the next hour
        if (remainder > 0) {
            hours++;
        }

        return hours * hourlyRate;
    }
}