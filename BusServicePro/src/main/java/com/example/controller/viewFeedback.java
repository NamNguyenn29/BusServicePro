package com.example.controller;

import com.example.DAO.FeedbackDAO;
import com.example.models.Feedback;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;
import com.example.controller.adminFeedback;

public class viewFeedback {

    @FXML
    private TextField user;
    @FXML
    private TextField feedback;
    public void initialize() {
          user.setText(adminFeedback.getSelectedName());
          feedback.setText(adminFeedback.getSelectedMessage());
    }
}
