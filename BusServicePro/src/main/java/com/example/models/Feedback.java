package com.example.models;

import java.time.LocalDate;
import com.example.DAO.UserDAO;

public class Feedback {
    private int feedbackId;
    private String message;
    private LocalDate submitDate;
    private int userID;
    public Feedback(int feedbackId, String message, LocalDate submitDate, int userID) {
        this.feedbackId = feedbackId;
        this.message = message;
        this.submitDate = submitDate;
        this.userID = userID;
    }  public Feedback( String message, LocalDate submitDate, int userID) {
        this.feedbackId = feedbackId;
        this.message = message;
        this.submitDate = submitDate;
        this.userID = userID;
    }
    public int getFeedbackId() { return feedbackId; }
    public String getMessage() { return message; }
    public LocalDate getSubmitDate() { return submitDate; }
    public int getUserID() { return userID; }
    public String getUserName() {
        User user= UserDAO.getUserById(userID);
        return user.getUsername();
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", message='" + message + '\'' +
                ", submitDate=" + submitDate +
                ", userID=" + userID +
                '}';
    }
}
