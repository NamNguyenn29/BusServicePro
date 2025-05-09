package com.example.models;

import com.example.models.Stoptime;

import java.util.ArrayList;
import java.util.List;

public class SearchTrip {

    private List<Trip> trips;

    public SearchTrip(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> searchTrips(Stop fromStop, Stop toStop) {
        List<Trip> result = new ArrayList<>();

        for (Trip trip : trips) {
            List<Stoptime> stoptimes = trip.getStoptimes();
            int fromIndex = -1, toIndex = -1;

            for (int i = 0; i < stoptimes.size(); i++) {
                if (stoptimes.get(i).getStop().equals(fromStop)) {
                    fromIndex = i;
                }
                if (stoptimes.get(i).getStop().equals(toStop)) {
                    toIndex = i;
                }
            }

            if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
                result.add(trip);
            }
        }

        return result;
    }

}
