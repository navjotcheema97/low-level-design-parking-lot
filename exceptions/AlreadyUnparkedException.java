package com.lld.parkinglot.exceptions;

public class AlreadyUnparkedException extends ParkingLotException {
    public AlreadyUnparkedException(String vehicle_already_unparked) {
        super(vehicle_already_unparked);
    }
}
