package main.strategies;

import main.models.CabType;
import main.models.Driver;
import main.models.Location;
import main.models.Rider;

import java.util.List;

public interface IDriverSelectionStrategy {
    public abstract Driver selectDriver(List<Driver> availableDriveList, Location startLocation, CabType cabType);
}
