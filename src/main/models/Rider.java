package main.models;

import java.util.UUID;

public class Rider {
    private UUID riderId;
    private String name;
    private Location location;

    public Rider(String name, Location location) {
        this.riderId = UUID.randomUUID();
        this.name = name;
        this.location = location;
    }

    public UUID getRiderId() {
        return riderId;
    }

    public void setRiderId(UUID riderId) {
        this.riderId = riderId;
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
}
