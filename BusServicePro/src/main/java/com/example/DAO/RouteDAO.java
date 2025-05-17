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


    public static boolean deleteRoute(int routeID) {
        String deleteRouteSQL = "DELETE FROM Route WHERE routeID = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // bắt đầu transaction

            // Bước 1: Xóa các stop liên quan đến route
            if (!RouteStopDAO.deleteStopsFromRoute(routeID)) {
                conn.rollback();
                System.out.println("Xóa Route_Stop thất bại.");
                return false;
            }

            // Bước 2: Xóa route chính
            try (PreparedStatement stmt = conn.prepareStatement(deleteRouteSQL)) {
                stmt.setInt(1, routeID);
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    System.out.println("Không tìm thấy route để xóa.");
                    return false;
                }
            }

            conn.commit();
            System.out.println("Xóa route và stop liên quan thành công.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRoute(Route route) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // bắt đầu transaction

            // Bước 1: Xóa tất cả stop hiện tại của route
            if (!RouteStopDAO.deleteStopsFromRoute(route.getRouteID())) {
                conn.rollback();
                System.out.println("Xóa stop cũ thất bại.");
                return false;
            }

            // Bước 2: Thêm stop mới
            List<Stop> stops = route.getStops();
            for (int i = 0; i < stops.size(); i++) {
                Stop stop = stops.get(i);
                boolean added = RouteStopDAO.addRouteStop(route.getRouteID(), stop.getStopID(), i + 1);
                if (!added) {
                    conn.rollback();
                    System.out.println("Thêm stop mới thất bại.");
                    return false;
                }
            }

            conn.commit();
            System.out.println("Cập nhật route thành công.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}