package com.lld.parkinglot.models;


import java.util.List;

public class Floor {
    private String floorId;
    private int floorNumber;
    private String parkingLotId;


    public Floor(String floorId, int floorNumber, String parkingLotId) {
        this.floorId = floorId;
        this.floorNumber = floorNumber;
        this.parkingLotId = parkingLotId;
    }

    public String getFloorId() {
        return floorId;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }
}
