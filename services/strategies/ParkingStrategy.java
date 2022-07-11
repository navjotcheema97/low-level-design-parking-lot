package com.lld.parkinglot.services.strategies;

import com.lld.parkinglot.models.ParkingSpot;
import com.lld.parkinglot.models.Ticket;
import com.lld.parkinglot.models.Vehicle;

import java.util.Optional;

public interface ParkingStrategy {

    public Ticket bookSlot(String parkingLotId, Vehicle vehicle);

}
