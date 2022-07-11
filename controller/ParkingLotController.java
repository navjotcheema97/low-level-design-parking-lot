package com.lld.parkinglot.controller;

import com.lld.parkinglot.dtos.FloorSpotCountDTO;
import com.lld.parkinglot.dtos.SlotsPerFloorDTO;
import com.lld.parkinglot.exceptions.BookingErrorException;
import com.lld.parkinglot.exceptions.InvalidTicketException;
import com.lld.parkinglot.exceptions.ParkingFullException;
import com.lld.parkinglot.models.*;
import com.lld.parkinglot.repository.ParkingLotRepository;
import com.lld.parkinglot.services.ParkingSlotFactory;
import com.lld.parkinglot.services.strategies.FirstAvailableBookingStrategy;
import com.lld.parkinglot.services.strategies.ParkingStrategy;
import com.sun.jdi.InternalException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//Move this to service because at controller we do input sanitation and validations
public class ParkingLotController {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotController() {
        this.parkingLotRepository = new ParkingLotRepository();
    }

    //Extract logic to a strategy
    public FloorSpotCountDTO createParkingLot(String lotId, int floorCount, int slotCountPerFloor){
        this.parkingLotRepository.createParkingLot(new ParkingLot(lotId));
        for(int i=1; i<= floorCount; i++){
            String floorId = Double.toString(Math.random());
            this.parkingLotRepository.addFloor(new Floor(floorId, i, lotId));
            for(int j=1; j<= slotCountPerFloor; j++){
                String slotId = Double.toString(Math.random());
                if(j==1){
                    this.parkingLotRepository.addSlot(new LargeParkingSpot(slotId, j, floorId));
                }else if(j>1 && j<4){
                    this.parkingLotRepository.addSlot(new SmallParkingSpot(slotId, j, floorId));
                }else{
                    this.parkingLotRepository.addSlot(new MediumParkingSpot(slotId, j, floorId));
                }
            }
        }
        return new FloorSpotCountDTO(floorCount, slotCountPerFloor);
    }

    public List<SlotsPerFloorDTO> getAllAvailableSlotsList(String lotId, VehicleType vehicleType){
        String slotType = ParkingSlotFactory.getParkingSlotTypeByVehicleType(vehicleType);
        List<SlotsPerFloorDTO> x = this.parkingLotRepository.getAllFloorsByLotId(lotId)
                .stream()
                .map(floor -> {
                    List<ParkingSpot> spots =  this.parkingLotRepository
                            .getAvailableSlotsByFloorAndSlotType(slotType, floor.getFloorId());
                    return new SlotsPerFloorDTO(floor, spots);
                }).collect(Collectors.toList());
        return x;
    }

    public List<SlotsPerFloorDTO> getAllOccupiedSlotsList(String lotId, VehicleType vehicleType){
        String slotType = ParkingSlotFactory.getParkingSlotTypeByVehicleType(vehicleType);
        return this.parkingLotRepository.getAllFloorsByLotId(lotId)
                .stream()
                .map(floor -> {
                    List<ParkingSpot> spots =  this.parkingLotRepository
                            .getOccupiedSlotsByFloorAndSlotType(slotType, floor.getFloorId());
                    return new SlotsPerFloorDTO(floor, spots);
                }).collect(Collectors.toList());
    }

    public List<FloorSpotCountDTO> getFreeSlotsCount(String lotId, VehicleType vehicleType){
        return this.getAllAvailableSlotsList(lotId, vehicleType)
                .stream()
                .map(dto -> new FloorSpotCountDTO(dto.getFloor().getFloorNumber(), dto.getSpots().size()))
                .sorted(Comparator.comparingInt(FloorSpotCountDTO::getFloorsNumber)).collect(Collectors.toList());
    }

    public Ticket parkVehicle(String parkingLotId,
                              VehicleType vehicleType,
                              String registrationNumber,
                              String color ) throws ParkingFullException, BookingErrorException {
        ParkingStrategy parkingStrategy = new FirstAvailableBookingStrategy(this.parkingLotRepository);
        try {
            Vehicle vehicle = new Vehicle(color, registrationNumber, vehicleType, Double.toString(Math.random()));
            //how can we do this transactionaly
            Ticket ticket = parkingStrategy.bookSlot(parkingLotId, vehicle);
            this.parkingLotRepository.addVehicle(vehicle);
            this.parkingLotRepository.addTicket(ticket);
            return ticket;
        }catch (Exception e){
            throw e;
        }
    }


    public Vehicle unParkVehicle(String ticketId){
        Ticket ticket = this.parkingLotRepository.getTicketById(ticketId);
        if(ticket == null){
            throw new InvalidTicketException("Invalid ticket");
        }
        Vehicle vehicle = this.parkingLotRepository.getVehicleById(ticket.getVehicleId());
        if(this.parkingLotRepository.unParkVehicle(ticket)){
            this.parkingLotRepository.removeVehicleById(ticket.getVehicleId());
            this.parkingLotRepository.removeTicketBytId(ticketId);
        }else {
            throw new InternalException("Something went wrong");
        }
        return vehicle;
    }

}
