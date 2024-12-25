package main;

import main.commands.CommandExecutorFactory;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OutputPrinter outputPrinter = new OutputPrinter();
        DriverService driverService = new DriverService();
        RiderService riderService = new RiderService();
        TripService tripService = new TripService();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(outputPrinter, driverService, riderService, tripService);

        new InteractiveMode(commandExecutorFactory, outputPrinter).process();
    }
}