package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

public class ExitCommandExecutor extends CommandExecutor{
    public static final String COMMAND_NAME = "exit";

    public ExitCommandExecutor(DriverService driverService, RiderService riderService, TripService tripService, OutputPrinter outputPrinter) {
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
        outputPrinter.exit();
    }
}
