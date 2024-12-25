package main.strategies;

import main.exceptions.DriverNotAvailableException;
import main.models.CabType;
import main.models.Driver;
import main.models.Location;

import java.util.List;

public class DefaultDriverSelectionStrategy implements IDriverSelectionStrategy{
    @Override
    public Driver selectDriver(List<Driver> availableDriverList, Location startLocation, CabType cabType) {
        if (availableDriverList.isEmpty()) {
            throw new DriverNotAvailableException();
        }
        Driver selectedDriver = null;
        double leastDistance = Double.MAX_VALUE;

        for (Driver driver : availableDriverList) {
            double currDriverDistance = getDistance(driver.getLocation(), startLocation);
            if (currDriverDistance < leastDistance && driver.getCab().getCabType().equals(cabType)) {
                leastDistance = currDriverDistance;
                selectedDriver = driver;
            }
        }

        if (selectedDriver == null) {
            throw new DriverNotAvailableException();
        }

        return selectedDriver;
    }

    private double getDistance(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location1.getLatitude() - location2.getLatitude(), 2) + Math.pow(location1.getLongitude() - location2.getLongitude(), 2));
    }
}
