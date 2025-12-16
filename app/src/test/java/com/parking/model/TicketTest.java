package com.parking.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void shouldStoreEntryTime() {
        LocalDateTime testTime = LocalDateTime.of(2025, 11, 23, 10, 10);

        Ticket ticket = new Ticket(testTime);

        assertEquals(testTime, ticket.getEntryTime(), "The ticket should store the correct entry time");
    }

    @Test
    void shouldThrowExceptionWhenEntryTimeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Ticket(null));
    }

    @Test
    void shouldHaveUniqueId() {
        Ticket ticket = new Ticket(LocalDateTime.now());

        assertNotNull(ticket.getId(), "Ticket should have a unique ID");
    }

    @Test
    void shouldStartUnpaid() { // New ticket should be unpaid
        Ticket ticket = new Ticket(LocalDateTime.now());
        
        assertFalse(ticket.isPaid(), "New ticket should be unpaid");
    }

    @Test
    void shouldUpdateStatusWhenMarkedPaid() { // Then update status when marked paid
        Ticket ticket = new Ticket(LocalDateTime.now());
        
        ticket.markPaid();
        
        assertTrue(ticket.isPaid(), "Ticket should be paid after marking it");
    }

    @Test
    void shouldThrowExceptionWhenPaidTwice() {
        Ticket ticket = new Ticket(LocalDateTime.now());
        ticket.markPaid();

        // Pay again then it will throw exception
        assertThrows(IllegalStateException.class, () -> {ticket.markPaid(); 
        }, "Should throw exception if ticket is already paid");
    }
}