package com.example.DAO;

import com.example.models.DatabaseConnection;
import com.example.models.User;
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
            stmt.setInt(1,user.getId());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getName());
            stmt.setString(4,user.getUsername());
            stmt.setString(5,user.getPassword());
            stmt.setString(6,user.getPhone());

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
