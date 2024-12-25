package main.commands;

import main.OutputPrinter;
import main.exceptions.CommandNotFoundException;
import main.models.Command;
import main.services.DriverService;
import main.services.RiderService;
import main.services.TripService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    OutputPrinter outputPrinter;
    DriverService driverService;
    RiderService riderService;
    TripService tripService;
    Map<String, CommandExecutor> commandExecutorMap;

    public CommandExecutorFactory(OutputPrinter outputPrinter, DriverService driverService, RiderService riderService, TripService tripService) {
        this.outputPrinter = outputPrinter;
        this.driverService = driverService;
        this.riderService = riderService;
        this.tripService = tripService;
        this.commandExecutorMap = new HashMap<>();
        this.commandExecutorMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(CreateRiderCommandExecutor.COMMAND_NAME, new CreateRiderCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(CreateDriverCommandExecutor.COMMAND_NAME, new CreateDriverCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(CreateTripCommandExecutor.COMMAND_NAME, new CreateTripCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(GetAllAvailableDriversCommandExecutor.COMMAND_NAME, new GetAllAvailableDriversCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(GetAllDriverTripsCommandExecutor.COMMAND_NAME, new GetAllDriverTripsCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(GetAllRiderTripsCommandExecutor.COMMAND_NAME, new GetAllRiderTripsCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(SetDriverAvailabilityStatusCommandExecutor.COMMAND_NAME, new SetDriverAvailabilityStatusCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(TripCancelledCommandExecutor.COMMAND_NAME, new TripCancelledCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(TripCompletedCommandExecutor.COMMAND_NAME, new TripCompletedCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(TripStartedCommandExecutor.COMMAND_NAME, new TripStartedCommandExecutor(driverService, riderService, tripService, outputPrinter));
        this.commandExecutorMap.put(GetTripByTripIdCommandExecutor.COMMAND_NAME, new GetTripByTripIdCommandExecutor(driverService, riderService, tripService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(Command command) {
        if (!this.commandExecutorMap.containsKey(command.getCommandName())) {
            throw new CommandNotFoundException();
        }

        return this.commandExecutorMap.get(command.getCommandName());
    }

    public Map<String, CommandExecutor> getCommandExecutorMap() {
        return commandExecutorMap;
    }

    public void setCommandExecutorMap(Map<String, CommandExecutor> commandExecutorMap) {
        this.commandExecutorMap = commandExecutorMap;
    }
}
