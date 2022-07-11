package com.lld.parkinglot.models;

public class ParkingSpot {
    private String id;
    private int spotNumber;
    private String floorId;
    private boolean isOccupied = false;
    private String spotType;


    public ParkingSpot(String id, int spotNumber, String floorId, String spotType) {
        this.id = id;
        this.spotNumber = spotNumber;
        this.floorId = floorId;
        this.spotType = spotType;
    }

    public String getId() {
        return id;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public String getFloorId() {
        return floorId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getSpotType() {
        return spotType;
    }
}

