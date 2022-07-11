package com.lld.parkinglot.repository;

import com.lld.parkinglot.exceptions.AlreadyUnparkedException;
import com.lld.parkinglot.exceptions.FloorNotFoundException;
import com.lld.parkinglot.exceptions.InvalidTicketException;
import com.lld.parkinglot.models.*;
import javafx.scene.effect.Light;

import java.util.*;
import java.util.stream.Collectors;

//TODO: Implement common interface for repositories, Think of updating model class and add proxy here
public class ParkingLotRepository {

    private HashMap<String, ParkingLot> parkingLotMap = new HashMap<>();
    private HashMap<String, Floor> floorsMap = new HashMap<>();
    private HashMap<String, ParkingSpot> slotsMap = new HashMap<>();
    private HashMap<String, Ticket> ticketsMap = new HashMap<>();
    private HashMap<String, Vehicle> vehiclesMap = new HashMap<>();

    public void createParkingLot(ParkingLot parkingLot){
        parkingLotMap.put(parkingLot.getParkingLotId(), parkingLot);
    }

    public ParkingLot getParkingLotById(String parkingLotId){
        return this.parkingLotMap.get(parkingLotId);
    }

    public void addFloor(Floor floor){
        floorsMap.put(floor.getFloorId(), floor);
    }

    public void addSlot(ParkingSpot spot){
        slotsMap.put(spot.getId(), spot);
    }

    public void addVehicle(Vehicle vehicle){
        vehiclesMap.put(vehicle.getVehicleId(), vehicle);
    }

    public void addTicket(Ticket ticket){
        ticketsMap.put(ticket.getTicketId(), ticket);
    }

    public void removeTicketBytId(String ticketId) {
        ticketsMap.remove(ticketId);
    }

    public void removeVehicleById(String vehicleId) {
        vehiclesMap.remove(vehicleId);
    }

    public boolean updateSpotAvailabilityStatus(String spotId, Boolean newStatus){
        //can implement lock here
        if(newStatus == !slotsMap.get(spotId).isOccupied()) {
            synchronized (this) {
                if (newStatus == !slotsMap.get(spotId).isOccupied()) {
                    slotsMap.get(spotId).setOccupied(newStatus);
                    return true;
                }else return false;
            }
        }else {
            return false;
        }
    }

    public List<Floor> getAllFloors(){
        return new ArrayList<>(this.floorsMap.values());
    }

    public List<Floor> getAllFloorsByLotId(String lotId){
        return  this.floorsMap.values().stream()
                .filter(floor -> floor.getParkingLotId().equals(lotId)).collect(Collectors.toList());
    }

    public List<ParkingSpot> getAllSlotsByFloorId(String floorId){
        return this.slotsMap.values().stream().filter((slot) -> slot.getFloorId().equals(floorId)).collect(Collectors.toList());
    }

    private List<ParkingSpot> getSlotsByFloorAndSlotTypeAndBookingStatus(String spotType, String floorId, Boolean bookingStatus){
        return this.slotsMap.values().stream().filter((slot) ->
                slot.getFloorId().equals(floorId) && slot.getSpotType().equals(spotType) && slot.isOccupied() == bookingStatus )
                .collect(Collectors.toList());
    }

    public List<ParkingSpot> getAvailableSlotsByFloorAndSlotType(String spotType, String floorId){
        return this.getSlotsByFloorAndSlotTypeAndBookingStatus(spotType, floorId, false);
    }

    public List<ParkingSpot> getOccupiedSlotsByFloorAndSlotType(String spotType, String floorId){
        return this.getSlotsByFloorAndSlotTypeAndBookingStatus(spotType, floorId, true);
    }

    public boolean unParkVehicle(Ticket ticket){
        return  this.updateSpotAvailabilityStatus(ticket.getSpotId(), false);
    }

    public Ticket getTicketById(String ticketId){
        return this.ticketsMap.get(ticketId);
    }

    public Vehicle getVehicleById(String vehicleId){
        return this.vehiclesMap.get(vehicleId);
    }

}
