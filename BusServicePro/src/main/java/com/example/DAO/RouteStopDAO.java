package com.example.DAO;

import com.example.models.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteStopDAO {

    public static boolean addRouteStop(int routeID, int stopID, int stopOrder) {
        String sql = "INSERT INTO Route_Stop (routeID, stopID, stopOrder) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, routeID);
            stmt.setInt(2, stopID);
            stmt.setInt(3, stopOrder);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Integer> getStopsByRouteId(int routeID) {
        List<Integer> stopIDs = new ArrayList<>();
        String sql = "SELECT stopID FROM Route_Stop WHERE routeID = ? ORDER BY stopOrder";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, routeID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                stopIDs.add(rs.getInt("stopID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stopIDs;
    }

    public static boolean deleteStopsFromRoute(int routeID) {
        String sql = "DELETE FROM Route_Stop WHERE routeID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, routeID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

