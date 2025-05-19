package com.example.Controller;

import com.example.DAO.FeedbackDAO;
import com.example.models.Feedback;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class FeedbackController {
    @FXML
    private TextField feedbackID;
    @FXML
    private TextField messages;
    @FXML
    private TextField submitDate;
    @FXML
    private TextField userID;

    @FXML
    private void HandleFeedback() {
        String feedbackID = this.feedbackID.getText();
        String messages = this.messages.getText();
        String submitDate = this.submitDate.getText();
        LocalDate parsedate = LocalDate.parse(submitDate);
        String ID=userID.getText();
        int userId= Integer.parseInt(ID);

        Feedback feedback = new Feedback(feedbackID, messages, parsedate, userId);

        boolean feedbackResult = FeedbackDAO.addFeedback(feedback);
        if (feedbackResult) {
            showAlert("Successfully Added Feedback");
        } else {
            showAlert("Something Went Wrong");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
