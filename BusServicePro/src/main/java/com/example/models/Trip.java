package com.example.models;

import java.util.List;
class Trip {
    private int tripID;
    private Route route;
    private List<Stoptime> stoptimes;
    private Bus bus;

    public Trip(int scheduleID, Route route, List<Stoptime> stoptimes, Bus bus) {
        this.tripID = scheduleID;
        this.route = route;
        this.stoptimes = stoptimes;
        this.bus = bus;
    }

    public List<Stoptime> getStoptimes() { return stoptimes; }
    public Bus getBus() { return bus; }
}