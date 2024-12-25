package main.commands;

import main.OutputPrinter;
import main.exceptions.*;
import main.models.*;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;
import main.strategies.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CreateTripCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_trip";

    public CreateTripCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
        super(driverService, riderService, tripService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 4) {
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
        Location startLocation = rider.getLocation();
        Random random = new Random();
        double endLatitude = random.nextDouble(-90, 90);
        double endLongitude = random.nextDouble(-180, 180);
        Location endLocation = new Location(endLatitude, endLongitude);
        IPaymentStrategy paymentStrategy = new DefaultPaymentStrategy();
        if (command.getParams().get(1).equals("Alternative")) {
            paymentStrategy = new AlternativePaymentStrategy();
        }
        IDriverSelectionStrategy driverSelectionStrategy = new DefaultDriverSelectionStrategy();
        if (command.getParams().get(2).equals("Alternative")) {
            driverSelectionStrategy = new AlternativeDriverSelectionStrategy();
        }
        int cabTypeInteger = Integer.parseInt(command.getParams().get(3));
        if (cabTypeInteger < 1 || cabTypeInteger > 4) {
            throw new InvalidCabTypeException();
        }
        CabType cabType = CabType.values()[cabTypeInteger - 1];
        List<Driver> availableDriverList = this.driverService.getAllAvailableDrivers();
        Driver driver = driverSelectionStrategy.selectDriver(availableDriverList, startLocation, cabType);
        // Mark driver as unavailable
        driver.setAvailable(false);
        // Set the cab type from the driver since in alternative strategy the driver assigned might not be of the requested cab type
        Trip trip = new Trip(driver, driver.getCab().getCabType(), rider, startLocation, endLocation, paymentStrategy);
        double tripAmount = trip.getPaymentStrategy().generatePayment(trip);
        trip.setTripAmount(tripAmount);
        this.tripService.addTripToHashMap(trip);
        this.driverService.getUuidDriverMap().put(driver.getDriverId(), driver);
        this.driverService.addTripToDriverTrips(driver, trip);
        this.riderService.addTripToRiderTrips(rider, trip);
        outputPrinter.createTrip(trip);
    }
}
