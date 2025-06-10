package com.example.controller;

import com.example.models.Booking;
import com.example.models.Feedback;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.DAO.FeedbackDAO;
import com.example.DAO.BookingDAO;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class adminFeedback {
    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }

    @FXML
    private Button viewBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<Feedback,Integer>userID;
    @FXML
    private TableColumn<Feedback,String>userName;
    @FXML
    private TableColumn<Feedback, LocalTime>submitDate;
    @FXML
    private TableView<Feedback> adminFeedbackTable;
    @FXML
    private static String selectedName;
    @FXML
    private  static String selectedMessage;

    @FXML
    private void viewUserFeedback(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewFeedback.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("User Feedback");
        stage.show();
    }

    @FXML
    private void initialize() {
        List<Feedback> feedbackList = FeedbackDAO.getAllFeedback();
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        userName.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getUserName()));
        submitDate.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        adminFeedbackTable.setItems(FXCollections.observableArrayList(feedbackList));
        viewBtn.setOnAction(e -> {
            Feedback selectedFeedback = adminFeedbackTable.getSelectionModel().getSelectedItem();
            selectedName = selectedFeedback.getUserName();
            selectedMessage = selectedFeedback.getMessage();
            try {
                viewUserFeedback(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static String getSelectedName() {
        return selectedName;
    }
    public static String getSelectedMessage() {
        return selectedMessage;
    }
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }


}
