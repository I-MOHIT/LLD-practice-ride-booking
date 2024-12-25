package main.strategies;

import main.exceptions.DriverNotAvailableException;
import main.models.CabType;
import main.models.Driver;
import main.models.Location;

import java.util.List;
import java.util.Random;

public class AlternativeDriverSelectionStrategy implements IDriverSelectionStrategy{
    @Override
    public Driver selectDriver(List<Driver> availableDriveList, Location startLocation, CabType cabType) {
        if (availableDriveList.isEmpty()) {
            throw new DriverNotAvailableException();
        }
        Random random = new Random();
        int selectedDriverIndex = random.nextInt(0, availableDriveList.size());
        return availableDriveList.get(selectedDriverIndex);
    }
}
