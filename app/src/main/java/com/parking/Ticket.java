package com.parking;

import java.time.LocalDateTime;

public class Ticket {
    
    private final LocalDateTime entryTime;

    // Constructor
    public Ticket(LocalDateTime entryTime) {
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time cannot be null");
        }
        this.entryTime = entryTime;
    }

    // Getter for entryTime
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}