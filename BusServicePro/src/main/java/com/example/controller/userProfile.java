package com.example.controller;

import com.example.DAO.UserDAO;
import com.example.models.User;
import com.example.utilities.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class userProfile {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button updateBtn;
    @FXML
    private Button refreshBtn;


    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }

    public void initialize() {
        int id=signin.getIDFromSignin();
        User user=UserDAO.getUserById(id);
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        updateBtn.setOnAction(event -> {
            String username=usernameField.getText();
            String password=passwordField.getText();
            String name=nameField.getText();
            String email=emailField.getText();
            String phone=phoneField.getText();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            if(!password.equals(user.getPassword())) {
                user.setPassword(PasswordUtil.hashPassword(password));
            }
            if(UserDAO.updateUser(user)) {
                showAlert(Alert.AlertType.INFORMATION, "Info", "Update user successfully");
            }else{
                showAlert(Alert.AlertType.ERROR, "Error", "Something went wrong");
            }

        });
        refreshBtn.setOnAction(event -> {
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
            phoneField.setText(user.getPhone());
        });
    }
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
}
