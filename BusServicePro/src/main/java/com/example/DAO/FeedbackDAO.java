package com.example.DAO;


import com.example.models.Feedback;
import com.example.models.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public static boolean addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback ( message, submitDate, userID) VALUES ( ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedback.getMessage());
            stmt.setDate(2, Date.valueOf(feedback.getSubmitDate()));
            stmt.setInt(3, feedback.getUserID());
            stmt.executeUpdate();
            System.out.println("them feedback thanh cong");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Feedback> getFeedbacksByUser(int userID) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE userID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                feedbacks.add(new Feedback(
                        rs.getInt("feedbackID"),
                        rs.getString("message"),
                        rs.getDate("submitDate").toLocalDate(),
                        rs.getInt("userID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public static List<Feedback> getAllFeedback() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                feedbacks.add(new Feedback(
                        rs.getInt("feedbackID"),
                        rs.getString("message"),
                        rs.getDate("submitDate").toLocalDate(),
                        rs.getInt("userID")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbacks;
    }

}
