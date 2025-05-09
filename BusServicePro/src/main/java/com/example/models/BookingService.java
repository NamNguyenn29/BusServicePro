package com.example.models;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
class BookingService {
    private List<Booking> bookings = new ArrayList<>();

    public boolean createBooking(Trip trip, Stop fromStop, Stop toStop, User customer) {
        if (!isValidTrip(trip, fromStop, toStop)) {
            System.out.println("Invalid trip segment.");
            return false;
        }

        if (isBusFull(trip)) {
            System.out.println("Bus is full.");
            return false;
        }
        LocalTime startTime = getArivalTime(trip,fromStop);
        Booking booking = new Booking(bookings.size() + 1, trip, fromStop, toStop,startTime, customer);
        bookings.add(booking);
        System.out.println("Booking successful for " + customer.getUsername());
        return true;
    }
    private LocalTime getArivalTime(Trip trip,Stop fromStop) {
        List<Stoptime> stops = trip.getStoptimes();
        for(int i = 0;i<stops.size();i++) {
            if(stops.get(i).getStop().equals(fromStop)) {
                return stops.get(i).getArrivalTime();
            }
        }
        return null;
    }

    private boolean isValidTrip(Trip trip, Stop from, Stop to) {
        List<Stoptime> stops = trip.getStoptimes();
        int fromIndex = -1, toIndex = -1;
        LocalTime startTime = LocalTime.now();
        for (int i = 0; i < stops.size(); i++) {
            if (stops.get(i).getStop().equals(from)) {
                fromIndex = i;
                startTime = stops.get(i).getArrivalTime();
            };
            if (stops.get(i).getStop().equals(to)) toIndex = i;
        }

        return fromIndex >= 0 && toIndex >= 0 && fromIndex < toIndex && startTime.isAfter(LocalTime.now());
    }

    private boolean isBusFull(Trip trip) {
        int capacity = trip.getBus().getCapacity();
        long currentBookings = bookings.stream()
                .filter(b -> b.getTrip().equals(trip))
                .count();
        return currentBookings >= capacity;
    }

    public List<Booking> getBookingsForCustomer(User customer) {
        return bookings.stream()
                .filter(b -> b.getCustomer().equals(customer))
                .collect(Collectors.toList());
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}