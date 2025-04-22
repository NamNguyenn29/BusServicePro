package com.example.model;

import java.time.LocalTime;

public class Trip {
    private Route route;
    private LocalTime time;

    public Trip(Route route, LocalTime time) {
        this.route = route;
        this.time = time;
    }

    public Route getRoute() {
        return route;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return route.getName() + " - " + time.toString();
    }
}
