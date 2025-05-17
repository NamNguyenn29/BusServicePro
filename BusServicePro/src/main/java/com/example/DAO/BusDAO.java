package com.example.DAO;


import com.example.models.Bus;
import com.example.models.Route;
import com.example.models.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // Lấy thông tin xe buýt theo ID
    public static Bus getBusById(int busID) {
        String sql = "SELECT * FROM Bus WHERE busID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, busID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Route route = RouteDAO.getRouteById(rs.getInt("routeID"));
                return new Bus(
                        rs.getInt("busID"),
                        rs.getString("licensePlate"),
                        rs.getInt("capacity"),
                        route
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy tất cả xe buýt
    public static List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM Bus";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Route route = RouteDAO.getRouteById(rs.getInt("routeID"));
                buses.add(new Bus(
                        rs.getInt("busID"),
                        rs.getString("licensePlate"),
                        rs.getInt("capacity"),
                        route
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    // Thêm xe buýt mới
    public static boolean addBus(Bus bus) {
        String sql = "INSERT INTO Bus (busID, licensePlate, capacity, routeID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bus.getBusID());
            stmt.setString(2, bus.getLicensePlate());
            stmt.setInt(3, bus.getCapacity());
            stmt.setInt(4, bus.getRoute().getRouteID());

            stmt.executeUpdate();
            System.out.println("them bus thanh cong");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin xe buýt
    public static boolean updateBus(Bus bus) {
        String sql = "UPDATE Bus SET licensePlate = ?, capacity = ?, routeID = ? WHERE busID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bus.getLicensePlate());
            stmt.setInt(2, bus.getCapacity());
            stmt.setInt(3, bus.getRoute().getRouteID());
            stmt.setInt(4, bus.getBusID());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa xe buýt
    public static boolean deleteBus(int busID) {
        String sql = "DELETE FROM Bus WHERE busID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, busID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách xe buýt theo tuyến đường
    public static List<Bus> getBusesByRoute(int routeID) {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM Bus WHERE routeID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, routeID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Route route = RouteDAO.getRouteById(routeID);
                buses.add(new Bus(
                        rs.getInt("busID"),
                        rs.getString("licensePlate"),
                        rs.getInt("capacity"),
                        route
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    // Kiểm tra biển số xe đã tồn tại chưa
    public static boolean isLicensePlateExists(String licensePlate) {
        String sql = "SELECT 1 FROM Bus WHERE licensePlate = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, licensePlate);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}