package com.lld.parkinglot.services.console;

import com.lld.parkinglot.controller.ParkingLotController;
import com.lld.parkinglot.exceptions.ParkingFullException;
import com.lld.parkinglot.models.Ticket;
import com.lld.parkinglot.models.VehicleType;
import com.lld.parkinglot.services.ParkingSlotFactory;

import java.util.List;

public class ParkingVehicleCommand implements ICommand {

    @Override
    public void execute(List<String> inputList, ParkingLotController parkingLotController) {
        String vehicle = inputList.get(1);
        VehicleType vehicleType = ParkingSlotFactory.getVehicleTypeFromString(vehicle);
        String registrationNumber = inputList.get(2);
        String color = inputList.get(3);
        try {
            Ticket ticket = parkingLotController.parkVehicle(LotConfig.getLotId(), vehicleType,
                    registrationNumber, color);
            System.out.println("Parked vehicle. Ticket ID: " + ticket.getTicketId());
        } catch (ParkingFullException e) {
            System.out.println("Parking Lot Full");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals("park_vehicle") && inputList.size() == 4;
    }

}
