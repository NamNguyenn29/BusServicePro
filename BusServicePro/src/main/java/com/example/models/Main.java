package com.example.models;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Stop s1 = new Stop(1, "Stop A");
        Stop s2 = new Stop(2, "Stop B");
        Stop s3 = new Stop(3, "Stop C");

        List<Stop> stops = Arrays.asList(s1, s2, s3);
        Route route = new Route(101, stops);
        Bus bus = new Bus(1, "ABC-123", 2, route);

        List<Stoptime> stoptimes = Arrays.asList(
                new Stoptime(s1, LocalTime.of(8, 0), LocalTime.of(8, 5)),
                new Stoptime(s2, LocalTime.of(21, 30), LocalTime.of(21, 35)),
                new Stoptime(s3, LocalTime.of(22, 0), LocalTime.of(22, 5))
        );

        Trip trip = new Trip(1001, route, stoptimes, bus);
        User c1 = new User(1, "alice");
        User c2 = new User(2, "bob");

//        BookingService bookingService = new BookingService();
//        List<Booking> bookings = new ArrayList<>();
//
//
//
//        bookingService.createBooking(trip, s2, s3, c1); // ✅
//        bookingService.createBooking(trip, s1, s2, c2); // ✅
//        bookingService.createBooking(trip, s1, s3, c2); // ❌ Full
//        bookings = bookingService.getBookings();
//
//
//        for (Booking booking : bookings) {
//            System.out.println(booking.getStartTime());
//        }
        SearchRoute searchRoute = new SearchRoute(Arrays.asList(route));
//        List<Route> result = searchRoute.searchRouteByStop("Stop A");
//        for(Route r : result) {
//            System.out.println(r.getRouteID());
//        }
//        System.out.println(result);
//          List<Route> routes = SearchRoute.showAllRoute();
//          for(Route r : routes) {
//              System.out.println(r.getRouteID());
//          }
        SearchStopByRoute searchStopByRoute = new SearchStopByRoute(Arrays.asList(route));
        List<Stop> stopsByRoute = searchStopByRoute.findStopByRoute(101);
        for(Stop s : stopsByRoute) {
            System.out.println(s.getStopName());
        }

    }
}