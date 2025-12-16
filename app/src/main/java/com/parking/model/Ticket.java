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
        // Calculate full hours between entry and exit
        long hours = ChronoUnit.HOURS.between(entryTime, exitTime);
        
        return hours * hourlyRate;
    }
}