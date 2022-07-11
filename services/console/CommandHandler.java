package com.lld.parkinglot.services.console;


import com.lld.parkinglot.controller.ParkingLotController;
import com.lld.parkinglot.exceptions.CommandNotFound;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private List<ICommand> commands = new ArrayList<>();
    private final ParkingLotController parkingLotController;

    public CommandHandler() {
        this.parkingLotController = new ParkingLotController();
    }

    public void register(ICommand command){
        commands.add(command);
    }

    private ICommand getCommand(List<String> input){
        for(ICommand command: commands){
            if(command.matches(input)){
                return command;
            }
        }
        throw new CommandNotFound("Invalid input");
    }

    public void execute(List<String> input){
        this.getCommand(input).execute(input, parkingLotController);
    }
}
