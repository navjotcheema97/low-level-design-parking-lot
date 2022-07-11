package com.lld.parkinglot.models;

import java.util.List;

public class ParkingLot {
    private String parkingLotId;


    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }
}
