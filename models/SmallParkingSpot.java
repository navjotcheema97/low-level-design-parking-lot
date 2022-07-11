package com.lld.parkinglot.models;

public class SmallParkingSpot extends ParkingSpot {

    public static String slotString = "SMALL";

    public SmallParkingSpot(String id, int spotNumber, String floorId) {
        super(id, spotNumber, floorId, "SMALL");
    }
}
