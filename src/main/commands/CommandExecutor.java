package main.commands;

import main.OutputPrinter;
import main.models.Command;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

public abstract class CommandExecutor {
    DriverService driverService;
    RiderService riderService;
    TripService tripService;
    OutputPrinter outputPrinter;

    public CommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
        this.driverService = driverService;
        this.riderService = riderService;
        this.tripService = tripService;
        this.outputPrinter = outputPrinter;
    }

    public abstract boolean validateCommand(Command command);
    public abstract void executeCommand(Command command);
}
