package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidTripIdException;
import main.exceptions.InvalidTripStatusException;
import main.models.Command;
import main.models.Driver;
import main.models.Trip;
import main.models.TripStatus;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.UUID;

public class TripCancelledCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "trip_cancelled";

    public TripCancelledCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
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
        trip.setTripStatus(TripStatus.CANCELLED);
        Driver driver = trip.getDriver();
        // Mark driver as available again
        driver.setAvailable(true);
        this.tripService.addTripToHashMap(trip);
        this.driverService.getUuidDriverMap().put(driver.getDriverId(), driver);
        this.driverService.addTripToDriverTrips(driver, trip);
        this.riderService.addTripToRiderTrips(trip.getRider(), trip);
        outputPrinter.tripCancelled(trip);
    }
}
