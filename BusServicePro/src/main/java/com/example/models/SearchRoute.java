package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class SearchRoute {




    private static List<Route> routes;

    public SearchRoute(List<Route> routes) {
        this.routes = routes;
    }
    // search route by stop name
    public static List<Route> searchRouteByStop(String stopName) {
        List<Route> result = new ArrayList<>();
        for(Route route : routes) {
            for(Stop stop : route.getStops()) {
                if (stop.getStopName().equals(stopName)) {
                    result.add(route);

                }
            }
        }
        return result;
    }

    public static List<Route> showAllRoute() {
        return routes;
    }

    @Override
    public String toString() {
        String result = "";
        for(Route route : routes) {
            result += route.getRouteID() + "\n";
        }
        return result;
    }
}
