package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class signup {
    @FXML
    private Hyperlink loginLink;

    @FXML
    private void initialize() {
        loginLink.setOnAction(e -> {
            try {
                switchToLoginForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @FXML
    private void switchToLoginForm(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }
}
