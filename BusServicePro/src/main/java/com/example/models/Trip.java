package com.example.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Trip {
    private int tripID;
    private LocalDate date;
    private LocalTime time;
    private int capacity;
    private int bookedSeats;


    public boolean isAvailable() {
        return false;
    }
    public boolean bookSeats () {
        return false;
    }
}
