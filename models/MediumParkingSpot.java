package com.lld.parkinglot.models;

public class MediumParkingSpot extends ParkingSpot {

    public static String slotString = "MEDIUM";

    public MediumParkingSpot(String id, int spotNumber, String floorId) {
        super(id, spotNumber, floorId, "MEDIUM");
    }
}
