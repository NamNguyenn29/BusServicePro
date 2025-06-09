package com.example.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Route {
    private int routeID;
    private List<Stop> stops;


    public Route(int routeID, List<Stop> stops) {
        this.routeID = routeID;
        this.stops = stops;
    }

    public Route(int routeID) {
        this.routeID = routeID;
        this.stops = new ArrayList<>();
    }


    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public List<Stop> getStops() { return stops; }


    public int getRouteID() { return routeID; }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        String result ="Route ID: "+ routeID ;
        return result;
    }

    public Stop getFStop() {
        if(stops.size() >= 2) {
            return stops.get(0);
        }
        else return null;
    }

    public Stop getEStop() {
        if(stops.size() >= 2) {
            return stops.get(stops.size() - 1);
        }
        else return null;
    }

}
