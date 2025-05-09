package com.example.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

class Stoptime {
    private Stop stop;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public Stoptime(Stop stop, LocalTime arrival, LocalTime departure) {
        this.stop = stop;
        this.arrivalTime = arrival;
        this.departureTime = departure;
    }

    public Stop getStop() { return stop; }
    public LocalTime getArrivalTime() { return arrivalTime; }
    public LocalTime getDepartureTime() { return departureTime; }

}