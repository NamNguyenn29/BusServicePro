package com.example.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

class Route {
    private int routeID;
    private List<Stop> stops;


    public Route(int routeID, List<Stop> stops) {
        this.routeID = routeID;
        this.stops = stops;
    }

    public List<Stop> getStops() { return stops; }
    public int getRouteID() { return routeID; }
}