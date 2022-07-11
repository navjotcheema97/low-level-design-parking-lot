package com.lld.parkinglot.services.console;

import com.lld.parkinglot.controller.ParkingLotController;
import com.lld.parkinglot.dtos.FloorSpotCountDTO;
import com.lld.parkinglot.dtos.SlotsPerFloorDTO;
import com.lld.parkinglot.models.ParkingSpot;
import com.lld.parkinglot.models.VehicleType;
import com.lld.parkinglot.services.ParkingSlotFactory;

import java.util.List;

public class DisplayCommand implements ICommand {

    private void displayFreeSpotCountsByFloor(List<FloorSpotCountDTO> dtos, String vehicle){
        for(FloorSpotCountDTO dto: dtos){
            System.out.println(
                    "No. of free slots for " + vehicle + " on Floor "
                            + dto.getFloorsNumber() + ": "+ dto.getSpotPerFloor()
            );
        }
    }

    private void displaySpotsNumberByFloor(List<SlotsPerFloorDTO> dtos, String vehicle, String status){
        for(SlotsPerFloorDTO dto: dtos){
            System.out.print(
                    status + " slots for " + vehicle + " on Floor "
                            + dto.getFloor().getFloorNumber() + ": "
            );
            for(ParkingSpot spot: dto.getSpots()){
                System.out.print(spot.getSpotNumber()+ ",");
            }
            System.out.println();
        }
    }

    @Override
    public void execute(List<String> inputList, ParkingLotController parkingLotController) {
        VehicleType vehicleType = ParkingSlotFactory.getVehicleTypeFromString(inputList.get(2));
        String lotId = LotConfig.getLotId();
        switch (inputList.get(1)) {
            case "free_count":
                displayFreeSpotCountsByFloor(parkingLotController.getFreeSlotsCount(lotId, vehicleType), inputList.get(2));
                break;
            case "free_slots": displaySpotsNumberByFloor(parkingLotController.getAllAvailableSlotsList(lotId, vehicleType), inputList.get(2), "Free");
                break;
            case "occupied_slots": displaySpotsNumberByFloor(parkingLotController.getAllOccupiedSlotsList(lotId, vehicleType), inputList.get(2), "Occupied");
                break;
        }
    }

    @Override
    public boolean matches(List<String> inputList) {
        return inputList.get(0).equals("display") && inputList.size() == 3;
    }
}
