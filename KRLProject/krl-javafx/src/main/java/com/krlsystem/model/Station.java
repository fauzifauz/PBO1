package com.krlsystem.model;

public class Station {
    private String id;
    private String name;
    private double distanceFromStart;

    public Station(String id, String name, double distanceFromStart) {
        this.id = id;
        this.name = name;
        this.distanceFromStart = distanceFromStart;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(double distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    @Override
    public String toString() {
        return name + " (" + distanceFromStart + " km)";
    }
}
