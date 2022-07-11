package com.lld.parkinglot.models;

public class Vehicle {
    private String color;
    private String registrationNumber;
    private VehicleType vehicleType;
    private String vehicleId;

    public Vehicle(String color, String registrationNumber, VehicleType vehicleType, String vehicleId) {
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
