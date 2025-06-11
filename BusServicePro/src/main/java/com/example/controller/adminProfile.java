package com.example.controller;

import com.example.utilities.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.example.controller.signin;
import com.example.models.Admin;
import com.example.DAO.AdminDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class adminProfile {
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button updateBtn;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());

    }
    public void initialize() {
        Admin admin=AdminDAO.getAdminByID(signin.getIDFromSignin());
        usernameField.setText(admin.getUsername());
        passwordField.setText(admin.getPassword());
        refreshBtn.setOnAction(e -> {
            usernameField.setText(admin.getUsername());
            passwordField.setText(admin.getPassword());
        });
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Không hỗ trợ SHA-256", e);
        }
    }
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}