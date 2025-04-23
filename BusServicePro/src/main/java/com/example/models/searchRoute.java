package com.example.models;

import java.util.List;

public class searchRoute {
    private List<Route> allRoutes;

    public searchRoute(List<Route> allRoutes) {
        this.allRoutes = allRoutes;
    }

    public List<Route> search(String start, String end) {
        List<Route> result = new java.util.ArrayList<>();
        for(Route route : allRoutes) {
            if(route.getStartLocation().equals(start) && route.getEndLocation().equals(end)) {
                result.add(route);
            }
        }
        return result;
    }
}
