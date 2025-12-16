package com.parking.service;

import java.util.regex.Pattern;

public class LicenseValidator {
    private static final Pattern STANDARD_PATTERN = Pattern.compile("^[A-Z]{3} [0-9]{2}[0-9A-Z]$");

    public static boolean isValid(String plate) {
        if (plate == null) 
            return false;
        
        String upperPlate = plate.toUpperCase();
        
        if (plate == null || plate.isBlank()) {
            return false;
        }

        // Pattern Check
        if (!STANDARD_PATTERN.matcher(upperPlate).matches()) {
            return false;
        }
        
        // Banned Letters Check
        if (upperPlate.contains("I") || upperPlate.contains("Q") || upperPlate.contains("V")) {
            return false;
        }

        // Last character O check
        if (upperPlate.endsWith("O")) {
            return false;
    }

        return true;
    }
}
