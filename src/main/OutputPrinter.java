package main;

import main.models.Driver;
import main.models.Rider;
import main.models.Trip;

import java.util.List;

public class OutputPrinter {
    public void hello() {
        System.out.println("Hello!");
    }

    public void exit() {
        System.out.println("Bye!");
    }

    public void createRider(Rider rider) {
        System.out.println("Created a rider " + rider.getName() + " having id " + rider.getRiderId());
    }

    public void createDriver(Driver driver) {
        System.out.println("Created a driver " + driver.getName() + " having id " + driver.getDriverId() + " and cab named " + driver.getCab().getName() + " of colour " + driver.getCab().getColour() + " having cab id " + driver.getCab().getCabId());
    }

    public void createTrip(Trip trip) {
        System.out.println("Created a trip having trip id " + trip.getTripId() + ", driver is " + trip.getDriver().getName() + " and the rider is " + trip.getRider().getName());
    }

    public void tripStarted(Trip trip) {
        System.out.println("The trip having id " + trip.getTripId() + " has started");
    }

    public void tripCancelled(Trip trip) {
        System.out.println("The trip having id " + trip.getTripId() + " has been cancelled");
    }

    public void tripCompleted(Trip trip) {
        System.out.println("The trip having id " + trip.getTripId() + " has been completed. The trip amount is " + trip.getTripAmount() + " and the trip is rated " + trip.getTripRating());
    }

    public void setDriverAvailabilityStatus(Driver driver) {
        System.out.println("The driver " + driver.getName() + " availability status has changed to - " + driver.isAvailable());
    }

    public void getAllAvailableDrivers(List<Driver> driverList) {
        System.out.println("The available drivers are -");
        for (Driver driver : driverList) {
            System.out.println(driver.getName() + " " + driver.getDriverId() + " " + driver.getCab().getName());
        }
    }

    public void getAllRiderTrips(Rider rider, List<Trip> tripList) {
        System.out.println("The trips created by the rider " + rider.getName() + " are -");
        for (Trip trip : tripList) {
            System.out.println(trip.getTripId() + " " + trip.getTripAmount() + " " + trip.getTripStatus() + " " + trip.getTripRating() + " " + trip.getDriver().getName());
        }
    }

    public void getAllDriverTrips(Driver driver, List<Trip> tripList) {
        System.out.println("The trips requested for the driver " + driver.getName() + " are -");
        for (Trip trip : tripList) {
            System.out.println(trip.getTripId() + " " + trip.getTripAmount() + " " + trip.getTripStatus() + " " + trip.getTripRating() + " " + trip.getRider().getName());
        }
    }

    public void getTripByTripId(Trip trip) {
        System.out.println("The requested trip details are -");
        System.out.println(trip.getTripId() + " " + trip.getDriver().getName() + " " + trip.getRider().getName() + " " + trip.getTripStatus() + " " + trip.getTripAmount() + " " + trip.getTripRating());
    }
}
