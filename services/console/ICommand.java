package com.lld.parkinglot.services.console;

import com.lld.parkinglot.controller.ParkingLotController;

import java.util.List;

public interface ICommand {

    void execute(List<String> inputList, ParkingLotController parkingLotController);

    boolean matches(List<String> inputList);

}
