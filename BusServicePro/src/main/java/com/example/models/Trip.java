package com.example.models;

import java.util.List;
public class Trip {
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
    public int getTripID() { return tripID; }

    public void setStoptimes(List<Stoptime> stoptimes) {
        this.stoptimes = stoptimes;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
        String result = tripID +" "+route.getRouteID() +" "+tripID+" \n";
        for(Stoptime stoptime : stoptimes) {
            result += stoptime.toString();
        }
        return result;

    }


}