package com.example.DAO;


import com.example.models.DatabaseConnection;
import com.example.models.Route;
import com.example.models.Stop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {
    static private List<Route> routes = getAllRoutes();

    public static List<Route> getRoutes() {
        return routes;
    }

    public static List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM Route";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int routeID = rs.getInt("routeID");
                Route route = new Route(routeID);

                // Lấy danh sách Stop tương ứng theo thứ tự
                List<Stop> stops = getStopsForRoute(routeID);
                route.setStops(stops);

                routes.add(route);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public static List<Stop> getStopsForRoute(int routeID) {
        List<Stop> stops = new ArrayList<>();
        String sql = "SELECT s.stopID, s.stopName " +
                "FROM Route_Stop rs " +
                "JOIN Stop s ON rs.stopID = s.stopID " +
                "WHERE rs.routeID = ? " +
                "ORDER BY rs.stopOrder";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, routeID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int stopID = rs.getInt("stopID");
                String stopName = rs.getString("stopName");
                stops.add(new Stop(stopID, stopName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stops;
    }


    public static boolean addRoute(Route route) {
        String sql = "INSERT INTO Route (routeID) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, route.getRouteID());
            stmt.executeUpdate();
            System.out.println("them route thanh cong");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Route getRouteById(int routeID) {
        String sql = "SELECT * FROM Route WHERE routeID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, routeID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Route(rs.getInt("routeID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách các tuyến đường đi qua một điểm dừng
    public static List<Route> getRoutesByStopID(int stopID) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT DISTINCT r.routeID " +
                "FROM Route r " +
                "JOIN Route_Stop rs ON r.routeID = rs.routeID " +
                "WHERE rs.stopID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stopID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int routeID = rs.getInt("routeID");
                Route route = new Route(routeID);

                // Lấy danh sách điểm dừng của tuyến đường
                List<Stop> stops = getStopsForRoute(routeID);
                route.setStops(stops);

                routes.add(route);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }


}