package com.example.DAO;


import com.example.models.Stop;
import com.example.models.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StopDAO {

    // Lấy thông tin điểm dừng theo ID
    public static Stop getStopById(int stopID) {
        String sql = "SELECT * FROM Stop WHERE stopID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stopID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Stop(
                        rs.getInt("stopID"),
                        rs.getString("stopName")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy tất cả điểm dừng
    public static List<Stop> getAllStops() {
        List<Stop> stops = new ArrayList<>();
        String sql = "SELECT * FROM Stop ORDER BY stopName";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                stops.add(new Stop(
                        rs.getInt("stopID"),
                        rs.getString("stopName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stops;
    }

    // Thêm điểm dừng mới
    public static boolean addStop(Stop stop) {
        String sql = "INSERT INTO Stop (stopID, stopName) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stop.getStopID());
            stmt.setString(2, stop.getStopName());
            System.out.println("them stop thanh cong");
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Điểm dừng đã tồn tại");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    // Cập nhật thông tin điểm dừng
    public static boolean updateStop(Stop stop) {
        String sql = "UPDATE Stop SET stopName = ? WHERE stopID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, stop.getStopName());
            stmt.setInt(2, stop.getStopID());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa điểm dừng
    public static boolean deleteStop(int stopID) {
        String sql = "DELETE FROM Stop WHERE stopID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stopID);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Không thể xóa điểm dừng đang được sử dụng");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    // Tìm kiếm điểm dừng theo tên
    public static List<Stop> searchStopsByName(String name) {
        List<Stop> stops = new ArrayList<>();
        String sql = "SELECT * FROM Stop WHERE stopName LIKE ? ORDER BY stopName";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stops.add(new Stop(
                        rs.getInt("stopID"),
                        rs.getString("stopName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stops;
    }

    // Lấy danh sách các tuyến đường đi qua điểm dừng
    public static List<Integer> getRoutesForStop(int stopID) {
        List<Integer> routeIDs = new ArrayList<>();
        String sql = "SELECT routeID FROM Route_Stop WHERE stopID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, stopID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                routeIDs.add(rs.getInt("routeID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routeIDs;
    }

    // Lấy danh sách điểm dừng theo routeID
    public static List<Stop> getStopsByRouteID(int routeID) {
        List<Stop> stops = new ArrayList<>();
        String sql = "SELECT s.stopID, s.stopName " +
                "FROM Stop s " +
                "JOIN Route_Stop rs ON s.stopID = rs.stopID " +
                "WHERE rs.routeID = ? " +
                "ORDER BY s.stopName";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, routeID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stops.add(new Stop(
                        rs.getInt("stopID"),
                        rs.getString("stopName")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stops;
    }

}
