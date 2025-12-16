package com.parking.service;

import java.util.regex.Pattern;

public class LicenseValidator {
    private static final Pattern STANDARD_PATTERN = Pattern.compile("^[A-Z]{3} [0-9]{2}[0-9A-Z]$");

    public static boolean isValid(String plate) {
        if (plate == null) 
            return false;
        return STANDARD_PATTERN.matcher(plate.toUpperCase()).matches();
    }
}
