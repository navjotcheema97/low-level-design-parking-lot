package com.lld.parkinglot.services;

import com.lld.parkinglot.exceptions.VehicleTypeNotSupported;
import com.lld.parkinglot.models.*;

public class ParkingSlotFactory {

    public static String getParkingSlotTypeByVehicleType(VehicleType vehicleType){
        String slotType=LargeParkingSpot.slotString;
        switch (vehicleType){
            case CAR: slotType = MediumParkingSpot.slotString;
                break;
            case BIKE:slotType = SmallParkingSpot.slotString;
                break;
            case TRUCK: slotType =  LargeParkingSpot.slotString;
                break;
        }
        return slotType;
    }

    public static VehicleType getVehicleTypeFromString(String vehicleType){
        switch (vehicleType){
            case "CAR": return VehicleType.CAR;
            case "BIKE":  return  VehicleType.BIKE;
            case "TRUCK":  return  VehicleType.TRUCK;
        }
        throw new VehicleTypeNotSupported("Invalid vehicle type");
    }

}
