package com.example.models;

import java.time.LocalTime;

class Booking {
    private int bookingID;
    private Trip trip;
    private Stop fromStop;
    private Stop toStop;
    private LocalTime startTime;
    private LocalTime endTime;
    private User customer;

    public Booking(int bookingID, Trip trip, Stop fromStop, Stop toStop,LocalTime startTime, User customer) {
        this.bookingID = bookingID;
        this.trip = trip;
        this.fromStop = fromStop;
        this.toStop = toStop;
        this.startTime = startTime;
        this.customer = customer;
    }

    public Trip getTrip() { return trip; }
    public User getCustomer() { return customer; }
    public LocalTime getStartTime() { return startTime; }
}