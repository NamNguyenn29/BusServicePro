package com.example.DAO;

import com.example.models.Admin;
import com.example.models.DatabaseConnection;
import com.example.utilities.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private static int currentAdminID;

    public static int getCurrentAdminID() {
        return currentAdminID;
    }

    public static boolean login(String username, String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        String sql = "SELECT * FROM Admin WHERE username=? AND adminPassword=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                currentAdminID = rs.getInt("adminID");
                System.out.println("Đăng nhập Admin thành công. ID: " + currentAdminID);
                return true;
            }

            System.out.println("Đăng nhập Admin thất bại.");
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean register(Admin admin) {
        String sql = "INSERT INTO Admin (adminID, username, adminPassword) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, admin.getAdminID());
            stmt.setString(2, admin.getUsername());
            stmt.setString(3, PasswordUtil.hashPassword(admin.getPassword()));

            stmt.executeUpdate();
            System.out.println("Đăng ký Admin thành công.");
            return true;

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Đăng ký Admin không thành công: tài khoản đã tồn tại.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static Admin getAdminByID(int adminID) {
        String sql = "SELECT * FROM Admin WHERE adminID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, adminID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("adminID"),
                        rs.getString("username"),
                        rs.getString("adminPassword")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
