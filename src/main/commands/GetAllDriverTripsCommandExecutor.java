package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidDriverIdException;
import main.exceptions.InvalidRiderIdException;
import main.models.Command;
import main.models.Driver;
import main.models.Rider;
import main.models.Trip;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.List;
import java.util.UUID;

public class GetAllDriverTripsCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_all_driver_trips";

    public GetAllDriverTripsCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
        super(driverService, riderService, tripService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 1) {
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
        Driver driver = this.driverService.getUuidDriverMap().get(driverId);
        List<Trip> tripList = this.driverService.getDriverTripsHashMap().get(driverId);
        outputPrinter.getAllDriverTrips(driver, tripList);
    }
}
