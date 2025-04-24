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
        String sql = "INSERT INTO users (userID,email,fullName, username, password, phone) VALUES (?,?,?,?,?,?)";

    }
}
