package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidDriverIdException;
import main.models.Cab;
import main.models.Command;
import main.models.Driver;
import main.models.Location;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.Random;
import java.util.UUID;

public class SetDriverAvailabilityStatusCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "set_driver_availability_status";

    public SetDriverAvailabilityStatusCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
        super(driverService, riderService, tripService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 2) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        UUID driverId = UUID.fromString(command.getParams().getFirst());
        if (!this.driverService.getUuidDriverMap().containsKey(driverId)) {
            throw new InvalidDriverIdException();
        }
        boolean isAvailable = false;
        if (command.getParams().get(1).equals("yes")) {
            isAvailable = true;
        }
        Driver driver = this.driverService.getUuidDriverMap().get(driverId);
        driver.setAvailable(isAvailable);
        this.driverService.getUuidDriverMap().put(driverId, driver);
        outputPrinter.setDriverAvailabilityStatus(driver);
    }
}
