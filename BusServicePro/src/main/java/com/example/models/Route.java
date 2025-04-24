package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private static int count = 0;
    private int routeID;
    private String routeNumber;
    private String startLocation;
    private String endLocation;

    public Route(String routeNumber, String startLocation, String endLocation) {
        this.routeNumber = routeNumber;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public int getRouteID() {
        return routeID;

    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }
}
