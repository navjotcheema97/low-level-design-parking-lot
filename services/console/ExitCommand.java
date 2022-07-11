package com.lld.parkinglot.services.console;

import com.lld.parkinglot.controller.ParkingLotController;

import java.util.List;

public class ExitCommand implements ICommand {
    @Override
    public void execute(List<String> inputList, ParkingLotController parkingLotController) {
        System.exit(0);
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).toLowerCase().startsWith("exit");
    }
}
