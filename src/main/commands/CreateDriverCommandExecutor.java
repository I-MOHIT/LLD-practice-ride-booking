package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidCabTypeException;
import main.models.*;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.Random;

public class CreateDriverCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_driver";

    public CreateDriverCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
        super(driverService, riderService, tripService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || command.getParams().size() != 5) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        Random random = new Random();
        double latitude = random.nextDouble(-90, 90);
        double longitude = random.nextDouble(-180, 180);
        Location location = new Location(latitude, longitude);
        boolean isAvailable = false;
        if (command.getParams().get(1).equals("yes")) {
            isAvailable = true;
        }
        int cabTypeInteger = Integer.parseInt(command.getParams().get(4));
        if (cabTypeInteger < 1 || cabTypeInteger > 4) {
            throw new InvalidCabTypeException();
        }
        CabType cabType = CabType.values()[cabTypeInteger - 1];
        Cab cab = new Cab(command.getParams().get(2), command.getParams().get(3), cabType);
        Driver driver = new Driver(command.getParams().getFirst(), location, isAvailable, cab);
        this.driverService.addDriverToHashMap(driver);
        outputPrinter.createDriver(driver);
    }
}
