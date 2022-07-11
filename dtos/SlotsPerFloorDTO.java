package com.lld.parkinglot.dtos;

import com.lld.parkinglot.models.Floor;
import com.lld.parkinglot.models.ParkingSpot;

import java.util.List;

public class SlotsPerFloorDTO {
    private Floor floor;
    private List<ParkingSpot> spots;

    public SlotsPerFloorDTO(Floor floor, List<ParkingSpot> spots) {
        this.floor = floor;
        this.spots = spots;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }
}
