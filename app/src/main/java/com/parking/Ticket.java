package com.parking;

import java.time.LocalDateTime;

public class Ticket {
    
    private final LocalDateTime entryTime;

    // Constructor
    public Ticket(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    // Getter for entryTime
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}