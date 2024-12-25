package main.models;

import main.strategies.IPaymentStrategy;

import java.util.UUID;

public class Trip {
    private UUID tripId;
    private Driver driver;
    private CabType cabType;
    private Rider rider;
    private Location startLocation;
    private Location endLocation;
    private double tripAmount;
    private double tripRating;
    private TripStatus tripStatus;
    private IPaymentStrategy paymentStrategy;

    public Trip(Driver driver, CabType cabType, Rider rider, Location startLocation, Location endLocation, IPaymentStrategy paymentStrategy) {
        this.tripId = UUID.randomUUID();
        this.driver = driver;
        this.cabType = cabType;
        this.rider = rider;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.tripAmount = 0;
        this.tripRating = 0;
        this.tripStatus = TripStatus.ARRIVING;
        this.paymentStrategy = paymentStrategy;
    }

    public UUID getTripId() {
        return tripId;
    }

    public void setTripId(UUID tripId) {
        this.tripId = tripId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public double getTripAmount() {
        return tripAmount;
    }

    public void setTripAmount(double tripAmount) {
        this.tripAmount = tripAmount;
    }

    public double getTripRating() {
        return tripRating;
    }

    public void setTripRating(double tripRating) {
        this.tripRating = tripRating;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public IPaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}
