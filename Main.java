package com.lld.parkinglot;

import com.lld.parkinglot.services.console.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.register(new CreatingParkingLotCommand());
        commandHandler.register(new DisplayCommand());
        commandHandler.register(new ParkingVehicleCommand());
        commandHandler.register(new UnParkVehicleCommand());
        commandHandler.register(new ExitCommand());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.println("\n > ");
            List<String> input = Arrays.asList(br.readLine().split(" "));
            try{
                commandHandler.execute(input);
            } catch (Exception e){
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
