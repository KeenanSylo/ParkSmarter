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
        // 3 Letters + Space + 2 Digits + 1 Letter
        assertTrue(LicenseValidator.isValid("ABC 12A"), "Should accept standard new format");
    }

    @Test
    void shouldHandleLowercaseInput() {
        assertTrue(LicenseValidator.isValid("abc 123"), "Should accept lowercase and normalize it");
    }

    @Test
    void shouldRejectBannedLetters() {
        assertFalse(LicenseValidator.isValid("AIB 123"), "Letter I is banned");
        assertFalse(LicenseValidator.isValid("ABQ 123"), "Letter Q is banned");
        assertFalse(LicenseValidator.isValid("ABV 123"), "Letter V is banned");
    }

    @Test
    void shouldRejectSwedishCharacters() {
        assertFalse(LicenseValidator.isValid("ÅBC 123"), "Letter Å is banned");
        assertFalse(LicenseValidator.isValid("ÄBC 123"), "Letter Ä is banned");
        assertFalse(LicenseValidator.isValid("ÖBC 123"), "Letter Ö is banned");
    }

    @Test
    void shouldRejectLetterO_AtEnd() {
        assertFalse(LicenseValidator.isValid("ABC 12O"), "Last character cannot be O");
    }
}