package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidRiderIdException;
import main.exceptions.InvalidTripIdException;
import main.models.Command;
import main.models.Rider;
import main.models.Trip;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.List;
import java.util.UUID;

public class GetTripByTripIdCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_trip_by_trip_id";

    public GetTripByTripIdCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
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
        UUID tripId = UUID.fromString(command.getParams().getFirst());
        if (!this.tripService.getUuidTripMap().containsKey(tripId)) {
            throw new InvalidTripIdException();
        }
        Trip trip = this.tripService.getUuidTripMap().get(tripId);
        outputPrinter.getTripByTripId(trip);
    }
}
