package main.models;

import java.util.UUID;

public class Driver {
    private UUID driverId;
    private String name;
    private Location location;
    private boolean isAvailable;
    private Cab cab;

    public Driver(String name, Location location, boolean isAvailable, Cab cab) {
        this.driverId = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.isAvailable = isAvailable;
        this.cab = cab;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }
}
