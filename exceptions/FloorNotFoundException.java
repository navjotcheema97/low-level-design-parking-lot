package com.lld.parkinglot.exceptions;

public class FloorNotFoundException extends ParkingLotException {
    FloorNotFoundException(String mssg) {
        super(mssg);
    }
}
