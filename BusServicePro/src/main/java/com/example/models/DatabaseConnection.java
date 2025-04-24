package com.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bussystem";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678"; // nếu dùng XAMPP thường để trống

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối database thành công!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Kết nối thất bại: " + e.getMessage());
            return null;
        }
    }
}

