package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class adminMenu {
    @FXML
    private Button signoutBtn;

    @FXML
    private void getSignedOut(ActionEvent e) throws IOException {
        Stage stage = (Stage) signoutBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signin.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Sign In");
        newStage.show();
    }

    @FXML
    private void initialize() {
        signoutBtn.setOnAction(e -> {
            try {
                getSignedOut(e);
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        });
    }
}
