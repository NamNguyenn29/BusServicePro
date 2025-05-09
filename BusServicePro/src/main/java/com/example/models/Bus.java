package com.example.models;

import java.util.*;
import java.util.stream.Collectors;
class Bus {
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

    public int getCapacity() { return capacity; }
    public Route getRoute() { return route; }
}
