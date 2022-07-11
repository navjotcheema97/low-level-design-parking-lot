package com.lld.parkinglot.models;

public class LargeParkingSpot extends ParkingSpot {

    public static String slotString = "LARGE";

    public LargeParkingSpot(String id, int spotNumber, String floorId) {
        super(id, spotNumber, floorId, "LARGE");
    }
}
