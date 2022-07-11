package com.lld.parkinglot.services.console;

import com.lld.parkinglot.controller.ParkingLotController;

import java.util.List;

public class CreatingParkingLotCommand implements ICommand {



    @Override
    public void execute(List<String> inputList, ParkingLotController parkingLotController) {
        try {
            parkingLotController.createParkingLot(inputList.get(1),
                    Integer.parseInt(inputList.get(2)),
                    Integer.parseInt(inputList.get(3)));
            LotConfig.setLotId(inputList.get(1));
            System.out.println("Created parking lot with " +
                    inputList.get(2)  +
                    " floors and " + inputList.get(3) +
                    " slots per floor");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals("create_parking_lot") && inputList.size() == 4;
    }
}
