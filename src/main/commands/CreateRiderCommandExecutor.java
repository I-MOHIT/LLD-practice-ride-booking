package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.models.Location;
import main.models.Rider;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.Random;

public class CreateRiderCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "create_rider";

    public CreateRiderCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
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
        Random random = new Random();
        double latitude = random.nextDouble(-90, 90);
        double longitude = random.nextDouble(-180, 180);
        Location location = new Location(latitude, longitude);
        Rider rider = new Rider(command.getParams().getFirst(), location);
        this.riderService.addRiderToHashMap(rider);
        outputPrinter.createRider(rider);
    }
}
