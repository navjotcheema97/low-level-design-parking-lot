package com.lld.parkinglot.models;

public class Ticket {

    private String ticketId;
    private String spotId;
    private String vehicleId;

    public Ticket(String ticketId, String spotId, String vehicleId) {
        this.ticketId = ticketId;
        this.spotId = spotId;
        this.vehicleId = vehicleId;
    }

    public static String generateTicketId(String parkingLotId, int floorNumber, int slotNumber){
        return  parkingLotId + "_" + floorNumber + "_" + slotNumber;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSpotId() {
        return spotId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

}
