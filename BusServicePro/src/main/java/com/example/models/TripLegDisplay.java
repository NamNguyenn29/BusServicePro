package com.example.models;

import java.time.LocalTime;

public class TripLegDisplay {
    private  int stt;
    private  int tripId;
    private  Stop departureStop;
    private  Stop arrivalStop;
    private LocalTime departureTime1;
    private  LocalTime departureTime2;
    private  LocalTime arrivalTime1;
    private  LocalTime arrivalTime2;

    public TripLegDisplay(int stt, int tripId, Stop departureStop, Stop arrivalStop, LocalTime departureTime1, LocalTime arrivalTime1,LocalTime departureTime2, LocalTime arrivalTime2) {
        this.stt = stt;
        this.tripId = tripId;
        this.departureStop = departureStop;
        this.arrivalStop = arrivalStop;
        this.departureTime1 = departureTime1;
        this.arrivalTime1 = arrivalTime1;
        this.departureTime2 = departureTime2;
        this.arrivalTime2 = arrivalTime2;
    }

    public int getStt() { return stt; }
    public int getTripId() { return tripId; }
    public Stop getDepartureStop() { return departureStop; }
    public Stop getArrivalStop() { return arrivalStop; }
    public LocalTime getDepartureTime1() { return departureTime1; }
    public LocalTime getDepartureTime2() { return departureTime2; }
    public LocalTime getArrivalTime1() { return arrivalTime1; }
    public LocalTime getArrivalTime2() { return arrivalTime2; }
}

