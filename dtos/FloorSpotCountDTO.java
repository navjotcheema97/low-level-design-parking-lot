package com.lld.parkinglot.dtos;

public class FloorSpotCountDTO {
    private int floorsNumber;
    private int spotPerFloor;

    public FloorSpotCountDTO(int floorsNumber, int spotPerFloor) {
        this.floorsNumber = floorsNumber;
        this.spotPerFloor = spotPerFloor;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }

    public int getSpotPerFloor() {
        return spotPerFloor;
    }
}
