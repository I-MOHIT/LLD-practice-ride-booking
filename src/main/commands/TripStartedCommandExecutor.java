package main.commands;

import main.OutputPrinter;
import main.exceptions.*;
import main.models.*;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.UUID;

public class TripStartedCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "trip_started";

    public TripStartedCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
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
        if (!trip.getTripStatus().equals(TripStatus.ARRIVING)) {
            throw new InvalidTripStatusException();
        }
        trip.setTripStatus(TripStatus.IN_PROGRESS);
        this.tripService.addTripToHashMap(trip);
        this.driverService.addTripToDriverTrips(trip.getDriver(), trip);
        this.riderService.addTripToRiderTrips(trip.getRider(), trip);
        outputPrinter.tripStarted(trip);
    }
}
