package main.services;

import main.exceptions.InvalidDriverIdException;
import main.models.Driver;
import main.models.Trip;

import java.util.*;

public class DriverService {
    private Map<UUID, Driver> uuidDriverMap;
    private Map<UUID, List<Trip>> driverTripsHashMap;

    public DriverService() {
        this.uuidDriverMap = new HashMap<>();
        this.driverTripsHashMap = new HashMap<>();
    }

    public Map<UUID, Driver> getUuidDriverMap() {
        return uuidDriverMap;
    }

    public void setUuidDriverMap(Map<UUID, Driver> uuidDriverMap) {
        this.uuidDriverMap = uuidDriverMap;
    }

    public Map<UUID, List<Trip>> getDriverTripsHashMap() {
        return driverTripsHashMap;
    }

    public void setDriverTripsHashMap(Map<UUID, List<Trip>> driverTripsHashMap) {
        this.driverTripsHashMap = driverTripsHashMap;
    }

    public void addDriverToHashMap(Driver driver) {
        this.uuidDriverMap.put(driver.getDriverId(), driver);
        this.driverTripsHashMap.put(driver.getDriverId(), new ArrayList<>());
    }

    public void addTripToDriverTrips(Driver driver, Trip trip) {
        if (!this.uuidDriverMap.containsKey(driver.getDriverId())) {
            throw new InvalidDriverIdException();
        }
        List<Trip> tripList = this.driverTripsHashMap.get(driver.getDriverId());
        for (Trip currTrip : tripList) {
            if (currTrip.getTripId().equals(trip.getTripId())) {
                tripList.remove(currTrip);
                break;
            }
        }
        tripList.add(trip);
    }

    public List<Driver> getAllAvailableDrivers() {
        List<Driver> driverList = new ArrayList<>();
        for(Map.Entry<UUID, Driver> uuidDriverEntry : this.getUuidDriverMap().entrySet()) {
            if (uuidDriverEntry.getValue().isAvailable()) {
                driverList.add(uuidDriverEntry.getValue());
            }
        }
        return driverList;
    }
}
