package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.exceptions.InvalidDriverIdException;
import main.models.Command;
import main.models.Driver;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GetAllAvailableDriversCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "get_all_available_drivers";

    public GetAllAvailableDriversCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
        super(driverService, riderService, tripService, outputPrinter);
    }

    @Override
    public boolean validateCommand(Command command) {
        if (!command.getCommandName().equals(COMMAND_NAME) || !command.getParams().isEmpty()) {
            throw new CommandNotFoundException();
        }
        return true;
    }

    @Override
    public void executeCommand(Command command) {
        List<Driver> driverList = this.driverService.getAllAvailableDrivers();
        outputPrinter.getAllAvailableDrivers(driverList);
    }
}
