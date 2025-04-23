package com.example.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Trip {
    private int tripID;
    private LocalDate date;
    private LocalTime time;
    private int capacity;
    private int bookedSeats;
    private Route route;


    public Trip(int tripID, LocalDate date, LocalTime time, int capacity, Route route) {
        this.tripID = tripID;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.bookedSeats = 0;
        this.route = route;
    }

    public boolean isAvailable() {
        return bookedSeats < capacity;
    }
    public boolean bookSeats (int count) {
        if(count <=0) {
            return false;
        }
        int availableSeats = capacity - bookedSeats;
        if(count <= availableSeats) {
            bookedSeats += count;
            return true;
        } else {
            return false;
        }
    }

    public int getAvailableSeats() {
        return capacity - bookedSeats;
    }

    public Route getRoute() {
        return route;
    }

    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return time;
    }
}
