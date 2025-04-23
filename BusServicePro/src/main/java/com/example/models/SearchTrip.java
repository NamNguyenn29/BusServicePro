package com.example.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SearchTrip {
    private List<Trip> allTrip;

    public SearchTrip(List<Trip> allTrip) {
        this.allTrip = allTrip;
    }

    public List<Trip> search (String start, String end, LocalDate date, LocalTime time) {
        List<Trip> results = new ArrayList<>();
        for(Trip trip : allTrip) {
            Route route = trip.getRoute();
            if(trip.getDate().equals(date) && trip.getTime().equals(time) && route.getStartLocation().equals(start) && route.getEndLocation().equals(end)) {
                results.add(trip);
            }
        }
        return results;
    }
}
