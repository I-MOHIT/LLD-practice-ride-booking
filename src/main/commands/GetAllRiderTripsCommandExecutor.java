package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidRiderIdException;
import main.models.Command;
import main.models.Driver;
import main.models.Rider;
import main.models.Trip;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GetAllRiderTripsCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_all_rider_trips";

    public GetAllRiderTripsCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
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
        UUID riderId = UUID.fromString(command.getParams().getFirst());
        if (!this.riderService.getUuidRiderMap().containsKey(riderId)) {
            throw new InvalidRiderIdException();
        }
        Rider rider = this.riderService.getUuidRiderMap().get(riderId);
        List<Trip> tripList = this.riderService.getRidersTripHashMap().get(riderId);
        outputPrinter.getAllRiderTrips(rider, tripList);
    }
}
