package com.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class signin {

    @FXML
    private Hyperlink registerLink;

    @FXML
    private void initialize() {
        registerLink.setOnAction(e -> {
            try {
                switchtoRegisterForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    private void switchtoRegisterForm(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/signup.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }
}
