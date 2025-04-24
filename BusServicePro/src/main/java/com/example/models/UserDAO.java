package com.example.models;

import com.example.utilities.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean login(String username, String password) {
        String hashPassword = PasswordUtil.hashPassword(password);
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean register(User user) {
        String sql = "INSERT INTO user (userID,email,fullName, username, userpassword, phone) VALUES (?,?,?,?,?,?)";
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,user.id);
            stmt.setString(2,user.email);
            stmt.setString(3,user.name);
            stmt.setString(4,user.username);
            stmt.setString(5,user.password);
            stmt.setString(6,user.phone);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Đăng kí không thành công");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }
}
