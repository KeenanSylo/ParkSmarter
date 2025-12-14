package com.parking;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    
    private final LocalDateTime entryTime;
    private final UUID id;

    // Constructor
    public Ticket(LocalDateTime entryTime) {
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time cannot be null");
        }
        this.entryTime = entryTime;
        this.id = UUID.randomUUID();
    }

    // Getter for entryTime
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    // Getter for id
    public UUID getId() {
        return id;
    }
}