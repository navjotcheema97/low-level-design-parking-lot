package com.lld.parkinglot.dtos;

import com.lld.parkinglot.models.Floor;
import com.lld.parkinglot.models.ParkingSpot;
import javafx.scene.effect.Light;

public class SpotFloorDTO {
    private Floor floor;
    private ParkingSpot spot;

    public SpotFloorDTO(Floor floor, ParkingSpot spot) {
        this.floor = floor;
        this.spot = spot;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }
}
