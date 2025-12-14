package com.parking;

import java.time.LocalDateTime;
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
        this.isPaid = true;
    }
}