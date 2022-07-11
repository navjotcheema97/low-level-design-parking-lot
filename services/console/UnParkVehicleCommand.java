package com.lld.parkinglot.services.console;

import com.lld.parkinglot.controller.ParkingLotController;
import com.lld.parkinglot.exceptions.InvalidTicketException;
import com.lld.parkinglot.models.Vehicle;

import java.util.List;

public class UnParkVehicleCommand implements ICommand {

    @Override
    public void execute(List<String> inputList, ParkingLotController parkingLotController) {
        String ticketId = inputList.get(1);
        try{
            Vehicle vehicle = parkingLotController.unParkVehicle(ticketId);
            System.out.println(
                    "Unparked vehicle with Registration Number: "+
                            vehicle.getRegistrationNumber() +
                            " and Color: " +vehicle.getColor()

            );
        } catch (InvalidTicketException e){
            System.out.println("Invalid Ticket");
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals("unpark_vehicle") && inputList.size() == 2;
    }
}
