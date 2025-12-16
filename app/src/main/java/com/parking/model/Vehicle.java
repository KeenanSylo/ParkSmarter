package com.parking.model;

import com.parking.service.LicenseValidator;

public class Vehicle {

    private final String licensePlate;
    private final String brand;
    private final String model;
    private final String color;

    public Vehicle(String licensePlate, String brand, String model, String color) {
        if (!LicenseValidator.isValid(licensePlate)) {
            throw new IllegalArgumentException("Invalid license plate format");
        }

        if (brand == null || brand.isBlank()) {
             throw new IllegalArgumentException("Brand cannot be null or blank");
        }

        if (color == null || color.isBlank()) {
             throw new IllegalArgumentException("Color cannot be null or blank");
        }
        
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}