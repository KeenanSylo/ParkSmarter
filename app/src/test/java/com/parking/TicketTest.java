package com.parking;

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
    void shouldThrowException_WhenEntryTimeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Ticket(null));
    }

    @Test
    void shouldHaveUniqueId() {
        Ticket ticket = new Ticket(LocalDateTime.now());

        assertNotNull(ticket.getId(), "Ticket should have a unique ID");
    }
}