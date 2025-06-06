package com.example.DAO;

import com.example.models.*;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TripDAO {

    // Lấy trip theo ID kèm danh sách stop times
    public static Trip getTripWithStopTimes(int tripID) {
        String sql = "SELECT s.stopID, s.arrivalTime, s.departureTime, ts.stopOrder " +
                "FROM trip_stoptime ts " +
                "JOIN StopTime s ON ts.stopID = s.stopID " +
                "WHERE ts.tripID = ? " +
                "ORDER BY ts.stopOrder";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tripID);
            ResultSet rs = stmt.executeQuery();

            List<Stoptime> stopTimes = new ArrayList<>();
            while (rs.next()) {
                int stopID = rs.getInt("stopID");
                LocalTime arrival = rs.getTime("arrivalTime").toLocalTime();
                LocalTime departure = rs.getTime("departureTime").toLocalTime();
                Stop stop = StopDAO.getStopById(stopID);

                if (stop != null) {
                    stopTimes.add(new Stoptime(stop, arrival, departure));
                }
            }

            Trip trip = getTripOnly(tripID);
            if (trip != null) {
                trip.setStoptimes(stopTimes);
                return trip;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy trip không kèm stop times
    public static Trip getTripOnly(int tripID) {
        String sql = "SELECT * FROM Trip WHERE tripID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tripID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Route route = RouteDAO.getRouteById(rs.getInt("routeID"));
                Bus bus = BusDAO.getBusById(rs.getInt("busID"));
                return new Trip(tripID, route, new ArrayList<>(), bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm trip mới
    // Thêm trip mới và các stopTimes tương ứng
    public static boolean addTrip(Trip trip) {
        String insertTripSQL = "INSERT INTO Trip (tripID, routeID, busID) VALUES (?, ?, ?)";
        String insertTripStopTimeSQL = "INSERT INTO trip_stoptime (tripID, stopID, stopOrder) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (
                    PreparedStatement insertTripStmt = conn.prepareStatement(insertTripSQL);
                    PreparedStatement insertTripStopTimeStmt = conn.prepareStatement(insertTripStopTimeSQL)
            ) {
                // Thêm trip vào bảng Trip
                insertTripStmt.setInt(1, trip.getTripID());
                insertTripStmt.setInt(2, trip.getRoute().getRouteID());
                insertTripStmt.setInt(3, trip.getBus().getBusID());
                insertTripStmt.executeUpdate();

                // Thêm danh sách stopTimes vào bảng trip_stoptime
                List<Stoptime> stopTimes = trip.getStoptimes();
                for (int i = 0; i < stopTimes.size(); i++) {
                    Stoptime stopTime = stopTimes.get(i);
                    int stopID = stopTime.getStop().getStopID();
                    int stopOrder = i + 1; // Thứ tự dừng

                    insertTripStopTimeStmt.setInt(1, trip.getTripID());
                    insertTripStopTimeStmt.setInt(2, stopID);
                    insertTripStopTimeStmt.setInt(3, stopOrder);
                    insertTripStopTimeStmt.executeUpdate();
                }

                conn.commit(); // Xác nhận thành công
                System.out.println("them trip thanh cong");
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Có lỗi thì rollback
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Đặt lại chế độ mặc định
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Gán một stoptime vào trip (chèn vào trip_stoptime)
    public static boolean addStopTimeToTrip(int tripID, int stopID, int stopOrder) {
        String sql = "INSERT INTO trip_stoptime (tripID, stopID, stopOrder) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tripID);
            stmt.setInt(2, stopID);
            stmt.setInt(3, stopOrder);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa stoptime khỏi trip
    public static boolean removeStopTimeFromTrip(int tripID, int stopID) {
        String sql = "DELETE FROM trip_stoptime WHERE tripID = ? AND stopID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tripID);
            stmt.setInt(2, stopID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thứ tự dừng
    public static boolean updateStopOrder(int tripID, int stopID, int newOrder) {
        String sql = "UPDATE trip_stoptime SET stopOrder = ? WHERE tripID = ? AND stopID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newOrder);
            stmt.setInt(2, tripID);
            stmt.setInt(3, stopID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Trip> searchTrips(Stop fromStop, Stop toStop) {
        List<Trip> result = new ArrayList<>();

        String sql = "SELECT t.tripID " +
                "FROM Trip t " +
                "JOIN trip_stoptime ts1 ON t.tripID = ts1.tripID " +
                "JOIN trip_stoptime ts2 ON t.tripID = ts2.tripID " +
                "WHERE ts1.stopID = ? AND ts2.stopID = ? AND ts1.stopOrder < ts2.stopOrder";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fromStop.getStopID());
            stmt.setInt(2, toStop.getStopID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int tripID = rs.getInt("tripID");
                Trip trip = getTripWithStopTimes(tripID); // Lấy đầy đủ danh sách Stoptimes
                if (trip != null) {
                    result.add(trip);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



    // deleteTrip
    public static boolean deleteTrip(int tripID) {
        String deleteTripStopTimeSQL = "DELETE FROM trip_stoptime WHERE tripID = ?";
        String deleteTripSQL = "DELETE FROM Trip WHERE tripID = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (
                    PreparedStatement deleteTripStopTimeStmt = conn.prepareStatement(deleteTripStopTimeSQL);
                    PreparedStatement deleteTripStmt = conn.prepareStatement(deleteTripSQL)
            ) {
                // Xoá stop_times trong bảng trip_stoptime
                deleteTripStopTimeStmt.setInt(1, tripID);
                deleteTripStopTimeStmt.executeUpdate();

                // Xoá trip
                deleteTripStmt.setInt(1, tripID);
                int rowsAffected = deleteTripStmt.executeUpdate();

                conn.commit();
                return rowsAffected > 0;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                System.out.println("Delete trip failed!");
                return false;
            } finally {
                conn.setAutoCommit(true);
                System.out.println("Delete trip success!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // update Trip
    public static boolean updateTrip(Trip trip) {
        String updateTripSQL = "UPDATE Trip SET routeID = ?, busID = ? WHERE tripID = ?";
        String deleteTripStopTimesSQL = "DELETE FROM trip_stoptime WHERE tripID = ?";
        String insertTripStopTimeSQL = "INSERT INTO trip_stoptime (tripID, stopID, stopOrder) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (
                    PreparedStatement updateTripStmt = conn.prepareStatement(updateTripSQL);
                    PreparedStatement deleteTripStopTimesStmt = conn.prepareStatement(deleteTripStopTimesSQL);
                    PreparedStatement insertTripStopTimeStmt = conn.prepareStatement(insertTripStopTimeSQL)
            ) {
                // Cập nhật bảng Trip
                updateTripStmt.setInt(1, trip.getRoute().getRouteID());
                updateTripStmt.setInt(2, trip.getBus().getBusID());
                updateTripStmt.setInt(3, trip.getTripID());
                updateTripStmt.executeUpdate();

                // Xóa các stopTimes cũ
                deleteTripStopTimesStmt.setInt(1, trip.getTripID());
                deleteTripStopTimesStmt.executeUpdate();

                // Thêm lại các stopTimes mới
                List<Stoptime> stopTimes = trip.getStoptimes();
                for (int i = 0; i < stopTimes.size(); i++) {
                    Stoptime stopTime = stopTimes.get(i);
                    insertTripStopTimeStmt.setInt(1, trip.getTripID());
                    insertTripStopTimeStmt.setInt(2, stopTime.getStop().getStopID());
                    insertTripStopTimeStmt.setInt(3, i + 1);
                    insertTripStopTimeStmt.executeUpdate();
                }

                conn.commit(); // Hoàn tất giao dịch
                System.out.println("Cập nhật trip thành công");
                return true;
            } catch (SQLException e) {
                conn.rollback(); // Rollback nếu có lỗi
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true); // Trả lại chế độ mặc định
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Lấy toàn bộ danh sách Trip kèm StopTimes
    public static List<Trip> getAllTrips() {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT tripID FROM Trip";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int tripID = rs.getInt("tripID");
                Trip trip = getTripWithStopTimes(tripID);
                if (trip != null) {
                    trips.add(trip);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;
    }



}
