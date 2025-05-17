package com.example.models;

import java.util.*;
import java.util.stream.Collectors;
public class Bus {
    private int busID;
    private String licensePlate;
    private int capacity;
    private Route route;

    public Bus(int busID, String licensePlate, int capacity, Route route) {
        this.busID = busID;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.route = route;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getCapacity() { return capacity; }
    public Route getRoute() { return route; }

    public int getBusID() {
        return busID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busID=" + busID +
                ", licensePlate='" + licensePlate + '\'' +
                ", capacity=" + capacity +
                ", route=" + route +
                '}';
    }
}
