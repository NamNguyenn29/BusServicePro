package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class signup {
    @FXML
    private Button loginButton;

    @FXML
    private void switchToLoginForm(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.setTitle("Sign In");
        stage.show();
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox showPassword;

    @FXML
    private AnchorPane innerAnchorPane;

    private TextField textField;

    @FXML
    private void togglePasswordVisibility() {
        if (showPassword.isSelected()) {
            textField.setVisible(true);
            textField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        } else {
            textField.setVisible(false);
            textField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
        }
    }

    @FXML
    private Button signupBtn;

    @FXML
    private void getSignedUp(ActionEvent event) throws IOException {
        Stage stage = (Stage) signupBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("User Menu");
        newStage.show();
    }

    @FXML
    private void initialize() {
        loginButton.setOnAction(e -> {
            try {
                switchToLoginForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        textField = new TextField();
        textField.setVisible(false);
        textField.setManaged(false);
        textField.setPrefWidth(passwordField.getPrefWidth());
        textField.setPrefHeight(passwordField.getPrefHeight());
        textField.setPadding(new Insets(10, 15, 10, 15));
        textField.setStyle("-fx-font-family: 'Tahoma'; -fx-font-size: 12px;");

        AnchorPane.setTopAnchor(textField, AnchorPane.getTopAnchor(passwordField));
        AnchorPane.setLeftAnchor(textField, AnchorPane.getLeftAnchor(passwordField));
        AnchorPane.setRightAnchor(textField, AnchorPane.getRightAnchor(passwordField));

        textField.textProperty().bindBidirectional(passwordField.textProperty());
        innerAnchorPane.getChildren().add(textField);
        showPassword.setOnAction(e -> togglePasswordVisibility());

        signupBtn.setOnAction(e -> {
            try {
                getSignedUp(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
