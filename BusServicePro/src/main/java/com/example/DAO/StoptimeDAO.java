package com.example.DAO;

import com.example.models.Stoptime;
import com.example.models.Stop;
import com.example.models.DatabaseConnection;

import java.sql.*;
import java.time.LocalTime;

public class StoptimeDAO {

    // Lấy Stoptime theo stopID
    public static Stoptime getStoptimeByStopID(int stopID) {
        String sql = "SELECT * FROM StopTime WHERE stopID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stopID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                LocalTime arrival = rs.getTime("arrivalTime").toLocalTime();
                LocalTime departure = rs.getTime("departureTime").toLocalTime();

                Stop stop = StopDAO.getStopById(stopID);

                if (stop != null) {
                    return new Stoptime(stop, arrival, departure);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Thêm Stoptime
    public static boolean addStoptime(Stoptime stoptime) {
        String sql = "INSERT INTO StopTime (stopID, arrivalTime, departureTime) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stoptime.getStop().getStopID());
            stmt.setTime(2, Time.valueOf(stoptime.getArrivalTime()));
            stmt.setTime(3, Time.valueOf(stoptime.getDepartureTime()));
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean addStopTimeToTrip(int tripID, int stopTimeID, int stopOrder) {
        String sql = "INSERT INTO Trip_Stoptime (tripID, stopTimeID, stopOrder) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tripID);
            stmt.setInt(2, stopTimeID);
            stmt.setInt(3, stopOrder);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStoptime(Stoptime stoptime) {
        String sql = "UPDATE StopTime SET arrivalTime = ?, departureTime = ? WHERE stopID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTime(1, Time.valueOf(stoptime.getArrivalTime()));
            stmt.setTime(2, Time.valueOf(stoptime.getDepartureTime()));
            stmt.setInt(3, stoptime.getStop().getStopID());

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteStoptimeByStopID(int stopID) {
        String sql = "DELETE FROM StopTime WHERE stopID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stopID);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //    public static int addStoptimeAndReturnID(Stoptime stoptime) {
    //        String sql = "INSERT INTO StopTime (stopID, arrivalTime, departureTime) VALUES (?, ?, ?)";
    //        try (Connection conn = DatabaseConnection.getConnection();
    //             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    //
    //            stmt.setInt(1, stoptime.getStop().getStopID());
    //            stmt.setTime(2, Time.valueOf(stoptime.getArrivalTime()));
    //            stmt.setTime(3, Time.valueOf(stoptime.getDepartureTime()));
    //            stmt.executeUpdate();
    //
    //            ResultSet rs = stmt.getGeneratedKeys();
    //            if (rs.next()) {
    //                return rs.getInt(1); // return stopTimeID
    //            }
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //        return -1;
    //    }

}
