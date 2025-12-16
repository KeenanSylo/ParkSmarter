package com.parking.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LicenseValidatorTest {

    @Test
    void shouldAcceptValidOldFormat() {
        // 3 Letters + Space + 3 Digits
        assertTrue(LicenseValidator.isValid("ABC 123"), "Should accept standard old format");
    }

    @Test
    void shouldAcceptValidNewFormat() {
        // New: 3 Letters + Space + 2 Digits + 1 Letter
        assertTrue(LicenseValidator.isValid("ABC 12A"), "Should accept standard new format");
    }
}