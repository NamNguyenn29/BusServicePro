package com.example.controller;

import com.example.models.Feedback;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.List;

import com.example.controller.signin;
import com.example.models.Feedback;
import com.example.DAO.FeedbackDAO;

public class userFeedback {
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private TextArea feedback;
    @FXML
    private TableColumn<Feedback, Integer> feedbackID;
    @FXML
    private TableColumn<Feedback, LocalDate> submitDate;
    @FXML
    private TableColumn<Feedback, Integer> function;
    @FXML
    private TableView<Feedback> feedbackTable;
    @FXML
    private Button submit;


    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }

    public void initialize() {
        int id = signin.getIDFromSignin();
        List<Feedback> feedbacks1 = FeedbackDAO.getFeedbacksByUser(id);
        feedbackID.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
        submitDate.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        function.setCellValueFactory(new PropertyValueFactory<>("message"));
        feedbackTable.setItems(FXCollections.observableArrayList(feedbacks1));
        submit.setOnAction(event -> {
            String content = feedback.getText();
            LocalDate date = LocalDate.now();
            Feedback feedback1 = new Feedback(content, date, id);
            if (FeedbackDAO.addFeedback(feedback1)) {
                showAlert(Alert.AlertType.INFORMATION, "Info", "Send feedback successfully");
            }else{
                showAlert(Alert.AlertType.ERROR, "Error", "Send feedback failed");
            }
            feedbackTable.getItems().clear();
            List<Feedback> feedbacks2 = FeedbackDAO.getFeedbacksByUser(id);
            feedbackID.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
            submitDate.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
            function.setCellValueFactory(new PropertyValueFactory<>("message"));
            feedbackTable.setItems(FXCollections.observableArrayList(feedbacks2));
        });
    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
