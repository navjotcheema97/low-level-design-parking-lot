package com.lld.parkinglot.services.strategies;

import com.lld.parkinglot.dtos.SpotFloorDTO;
import com.lld.parkinglot.exceptions.BookingErrorException;
import com.lld.parkinglot.exceptions.ParkingFullException;
import com.lld.parkinglot.models.*;
import com.lld.parkinglot.repository.ParkingLotRepository;
import com.lld.parkinglot.services.ParkingSlotFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FirstAvailableBookingStrategy implements ParkingStrategy{

    ParkingLotRepository parkingLotRepository;

    public FirstAvailableBookingStrategy(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    private Optional<SpotFloorDTO> getFirstAvailableSlot(Vehicle vehicle) {
        String slotType = ParkingSlotFactory.getParkingSlotTypeByVehicleType(vehicle.getVehicleType());
        List<Floor> floors = this.parkingLotRepository.getAllFloors();
        List<Floor> sortedFloors = floors.stream().sorted(Comparator.comparingInt(Floor::getFloorNumber))
                .collect(Collectors.toList());
        for(Floor floor : sortedFloors){
            List<ParkingSpot> availableSlots = this.parkingLotRepository.getAvailableSlotsByFloorAndSlotType(slotType, floor.getFloorId());
            if(availableSlots.size()>0){
                return availableSlots.stream().min(Comparator.comparingInt(ParkingSpot::getSpotNumber))
                        .map(x -> new SpotFloorDTO(floor, x));
            }
        }
        return Optional.empty();
    }

    @Override
    public Ticket bookSlot(String parkingLotId , Vehicle vehicle) {
        Optional<SpotFloorDTO> availableSlot = this.getFirstAvailableSlot(vehicle);
        if(availableSlot.isPresent()){
            SpotFloorDTO spot = availableSlot.get();
            boolean bookingStatus = this.parkingLotRepository
                    .updateSpotAvailabilityStatus(spot.getSpot().getId(), true);
            if(bookingStatus){
                String ticketId = Ticket.generateTicketId(parkingLotId,
                        spot.getFloor().getFloorNumber(),
                        spot.getSpot().getSpotNumber());
                return new Ticket(ticketId, spot.getSpot().getId(), vehicle.getVehicleId());
            }
            else{
                throw new BookingErrorException("Something went wrong please try again");
            }
        }else{
            throw new ParkingFullException("Parking Lot Full");
        }
    }

}
