package com.example.models;
import java.util.ArrayList;
import java.util.List;

public class SearchStopByRoute {
    private List<Route> routeList;

    public SearchStopByRoute(List<Route> routeList) {
        this.routeList = routeList;
    }

    public List<Stop> findStopByRoute(int routeID) {
        for(Route route : routeList) {
            if(route.getRouteID() == routeID) {
                return route.getStops();
            }
        }
        return new ArrayList<>();
    }
}
