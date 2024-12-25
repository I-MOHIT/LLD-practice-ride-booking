package main.models;

import java.util.UUID;

public class Cab {
    private UUID cabId;
    private String name;
    private String colour;
    private CabType cabType;

    public Cab(String name, String colour, CabType cabType) {
        this.cabId = UUID.randomUUID();
        this.name = name;
        this.colour = colour;
        this.cabType = cabType;
    }

    public UUID getCabId() {
        return cabId;
    }

    public void setCabId(UUID cabId) {
        this.cabId = cabId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public CabType getCabType() {
        return cabType;
    }

    public void setCabType(CabType cabType) {
        this.cabType = cabType;
    }
}
