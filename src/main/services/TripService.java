package main.services;

import main.models.Trip;
import main.strategies.IPaymentStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TripService {
    private Map<UUID, Trip> uuidTripMap;

    public TripService() {
        this.uuidTripMap = new HashMap<>();
    }

    public Map<UUID, Trip> getUuidTripMap() {
        return uuidTripMap;
    }

    public void setUuidTripMap(Map<UUID, Trip> uuidTripMap) {
        this.uuidTripMap = uuidTripMap;
    }

    public void addTripToHashMap(Trip trip) {
        this.uuidTripMap.put(trip.getTripId(), trip);
    }
}
