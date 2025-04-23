package com.example.models;

public class BusTop {
    static int count = 0;
    private int busStopID;
    private String busStopName;
    private String busStopLocation;

    public BusTop(String busStopName, String busStopLocation) {
        this.busStopID = ++count;
        this.busStopName = busStopName;
        this.busStopLocation = busStopLocation;
    }

    public int getBusStopID() {
        return busStopID;
    }

    public String getBusStopLocation() {
        return busStopLocation;
    }

    public String getBusStopName() {
        return busStopName;
    }
}

