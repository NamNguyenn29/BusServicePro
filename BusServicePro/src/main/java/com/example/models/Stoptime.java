package com.example.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Stoptime {
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


    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    @Override
    public String toString() {
        return "Stoptime{" +
                "stop=" + stop +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }

}