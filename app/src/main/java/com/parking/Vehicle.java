package com.parking;

public class Vehicle {

    private final String licensePlate;
    private final String brand;
    private final String model;
    private final String color;
    private final boolean isElectric;

    public Vehicle(String licensePlate, String brand, String model, String color, boolean isElectric) {
        if (licensePlate == null) {
            throw new IllegalArgumentException("License plate cannot be null");
        }
        
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.isElectric = isElectric;
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

    public boolean isElectric() {
        return isElectric;
    }
}