package main.services;

import main.exceptions.InvalidDriverIdException;
import main.exceptions.InvalidRiderIdException;
import main.models.Driver;
import main.models.Rider;
import main.models.Trip;

import java.util.*;

public class RiderService {
    private Map<UUID, Rider> uuidRiderMap;
    private Map<UUID, List<Trip>> ridersTripHashMap;

    public RiderService() {
        this.uuidRiderMap = new HashMap<>();
        this.ridersTripHashMap = new HashMap<>();
    }

    public Map<UUID, Rider> getUuidRiderMap() {
        return uuidRiderMap;
    }

    public void setUuidRiderMap(Map<UUID, Rider> uuidRiderMap) {
        this.uuidRiderMap = uuidRiderMap;
    }

    public Map<UUID, List<Trip>> getRidersTripHashMap() {
        return ridersTripHashMap;
    }

    public void setRidersTripHashMap(Map<UUID, List<Trip>> ridersTripHashMap) {
        this.ridersTripHashMap = ridersTripHashMap;
    }

    public void addRiderToHashMap(Rider rider) {
        this.uuidRiderMap.put(rider.getRiderId(), rider);
        this.ridersTripHashMap.put(rider.getRiderId(), new ArrayList<>());
    }

    public void addTripToRiderTrips(Rider rider, Trip trip) {
        if (!this.uuidRiderMap.containsKey(rider.getRiderId())) {
            throw new InvalidRiderIdException();
        }
        List<Trip> tripList = this.ridersTripHashMap.get(rider.getRiderId());
        for (Trip currTrip : tripList) {
            if (currTrip.getTripId().equals(trip.getTripId())) {
                tripList.remove(currTrip);
                break;
            }
        }
        tripList.add(trip);
    }
}
