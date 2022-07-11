package com.lld.parkinglot.exceptions;

public class InvalidTicketException extends ParkingLotException {
    public InvalidTicketException(String invalid_ticker) {
        super(invalid_ticker);
    }
}
