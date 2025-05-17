package com.example.models;

import java.time.LocalTime;

public class Booking {
    static  int count = 1;
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
    public Booking( Trip trip, Stop fromStop, Stop toStop,LocalTime startTime, User customer) {
        this.bookingID = count++;
        this.trip = trip;
        this.fromStop = fromStop;
        this.toStop = toStop;
        this.startTime = startTime;
        this.customer = customer;
    }

    public Trip getTrip() { return trip; }
    public User getCustomer() { return customer; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public int getBookingID() { return bookingID; }
    public Stop getFromStop() { return fromStop; }
    public Stop getToStop() { return toStop; }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", trip=" + trip +
                ", fromStop=" + fromStop +
                ", toStop=" + toStop +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", customer=" + customer +
                '}';
    }

    // deleteBooking

}