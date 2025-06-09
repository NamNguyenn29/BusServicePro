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
    private AnchorPane parentAnchorPane;

    @FXML
    private Button profileBtn;

    @FXML
    private void switchToProfile(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/adminProfile.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent adminProfileRoot = loader.load();

        adminProfile adminProfileController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        adminProfileController.setContent(newAnchorPane);

        parentAnchorPane.getChildren().setAll(((AnchorPane) adminProfileRoot).getChildren());
    }

    @FXML
    private Button viewFeedbackBtn;

    @FXML
    private void switchToFeedback(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/adminFeedback.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent adminFeedbackRoot = loader.load();

        adminFeedback adminFeedbackController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        adminFeedbackController.setContent(newAnchorPane);

        parentAnchorPane.getChildren().setAll(((AnchorPane) adminFeedbackRoot).getChildren());
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

        profileBtn.setOnAction(e -> {
            try {
                switchToProfile(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        viewFeedbackBtn.setOnAction(e -> {
            try {
                switchToFeedback(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
