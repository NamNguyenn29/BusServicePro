package com.example.DAO;

import com.example.models.*;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {


    static private List<Booking> CurrentTripBookings = new ArrayList<>();
    static List<Booking> bookings = getAllBookings();




    public static boolean createBooking(Trip trip, Stop fromStop, Stop toStop, User customer) {
        CurrentTripBookings = getBookingsByTripID(trip);
        if (!isValidTrip(trip, fromStop, toStop)) {
            System.out.println("Invalid trip segment.");
            return false;
        }

        if (isBusFull(trip)) {
            System.out.println("Bus is full.");
            return false;
        }
        LocalTime startTime = getArivalTime(trip,fromStop);
        Booking booking = new Booking( trip, fromStop, toStop,startTime, customer);
        saveBookingToDatabase(booking);
        System.out.println("Booking successful for " + customer.getUsername());
        return true;
    }
    private static LocalTime getArivalTime(Trip trip, Stop fromStop) {
        List<Stoptime> stops = trip.getStoptimes();
        for(int i = 0;i<stops.size();i++) {
            if(stops.get(i).getStop().equals(fromStop)) {
                return stops.get(i).getArrivalTime();
            }
        }
        return null;
    }

    private static boolean isValidTrip(Trip trip, Stop from, Stop to) {
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

    private static boolean isBusFull(Trip trip) {
        int capacity = trip.getBus().getCapacity();
        long currentBookings = CurrentTripBookings.stream()
                .filter(b -> b.getTrip().equals(trip))
                .count();
        return currentBookings >= capacity;
    }

    private static boolean saveBookingToDatabase(Booking booking) {
        String sql = "INSERT INTO Booking (id, tripID, fromStopID, toStopID, userID, startTime) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getBookingID());
            stmt.setInt(2, booking.getTrip().getTripID());
            stmt.setInt(3, booking.getFromStop().getStopID());
            stmt.setInt(4, booking.getToStop().getStopID());
            stmt.setInt(5, booking.getCustomer().getUserID());
            stmt.setTime(6, Time.valueOf(booking.getStartTime()));
            stmt.executeUpdate();
            System.out.println("Booking saved to database.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<Booking> getBookingsByUser(int userID) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE userID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Lấy thông tin các đối tượng liên quan từ các DAO khác
                Trip trip = TripDAO.getTripWithStopTimes(rs.getInt("tripID"));
                Stop fromStop = StopDAO.getStopById(rs.getInt("fromStopID"));
                Stop toStop = StopDAO.getStopById(rs.getInt("toStopID"));
                User user = UserDAO.getUserById(rs.getInt("userID"));

                bookings.add(new Booking(
                        rs.getInt("id"),
                        trip,
                        fromStop,
                        toStop,
                        rs.getTime("startTime").toLocalTime(),
                        user
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    public static List<Booking> getBookingsByTripID(Trip trip) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE tripID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, trip.getTripID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int fromStopID = rs.getInt("fromStopID");
                int toStopID = rs.getInt("toStopID");
                int userID = rs.getInt("userID");
                LocalTime startTime = rs.getTime("startTime").toLocalTime();

                // Lấy các đối tượng liên quan

                Stop fromStop = StopDAO.getStopById(fromStopID);
                Stop toStop = StopDAO.getStopById(toStopID);
                User customer = UserDAO.getUserById(userID);

                // Tạo đối tượng Booking
                Booking booking = new Booking(id, trip, fromStop, toStop, startTime, customer);
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    public static List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int tripID = rs.getInt("tripID");
                int fromStopID = rs.getInt("fromStopID");
                int toStopID = rs.getInt("toStopID");
                int userID = rs.getInt("userID");
                LocalTime startTime = rs.getTime("startTime").toLocalTime();

                // Lấy các đối tượng liên quan
                Trip trip = TripDAO.getTripWithStopTimes(tripID);
                Stop fromStop = StopDAO.getStopById(fromStopID);
                Stop toStop = StopDAO.getStopById(toStopID);
                User customer = UserDAO.getUserById(userID);

                // Tạo đối tượng Booking
                Booking booking = new Booking(id, trip, fromStop, toStop, startTime, customer);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // xoa booking
    public static boolean deleteBooking(int bookingID) {
        String sql = "DELETE FROM Booking WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookingID);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking deleted successfully.");
                return true;
            } else {
                System.out.println("No booking found with ID: " + bookingID);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



//    public static List<Booking> getBookingsForCustomer(User customer) {
//        return bookings.stream()
//                .filter(b -> b.getCustomer().equals(customer))
//                .collect(Collectors.toList());
//    }
//
//    public List<Booking> getBookings() {
//        return bookings;
//    }

}
