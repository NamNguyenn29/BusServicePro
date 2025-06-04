package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class menu {
    @FXML
    private Button signoutBtn;

    @FXML
    private void getSignedOut(ActionEvent event) throws IOException {
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
    private Button bookingBtn;

    @FXML
    private void switchToBookingForm(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/menu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent bookingRoot = loader.load();

        menu menuController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        menuController.setContent(newAnchorPane);

        StackPane bookingRootPane = (StackPane) bookingRoot;
        AnchorPane anchorPane = (AnchorPane) bookingRootPane.lookup("#parentAnchorPane");
        parentAnchorPane.getChildren().setAll(anchorPane.getChildren());
    }

    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }

    @FXML
    private Button sendFeedbackBtn;

    @FXML
    private void switchToSendFeedbackForm(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/userFeedback.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent sendFeedbackRoot = loader.load();

        userFeedback userFeedbackController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        userFeedbackController.setContent(newAnchorPane);

        parentAnchorPane.getChildren().setAll(((AnchorPane) sendFeedbackRoot).getChildren());
    }

    @FXML
    private Button profileBtn;

    @FXML
    private void switchToProfile(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/userProfile.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent profileRoot = loader.load();

        userProfile userProfileController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        userProfileController.setContent(newAnchorPane);

        parentAnchorPane.getChildren().setAll(((AnchorPane) profileRoot).getChildren());
    }

    @FXML
    private Button searchBtn;

    @FXML
    private void switchToSearchForm(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/userSearch.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent searchRoot = loader.load();

        userSearch userSearchController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        userSearchController.setContent(newAnchorPane);

        parentAnchorPane.getChildren().setAll(((AnchorPane) searchRoot).getChildren());
    }

    @FXML
    private void initialize() {
        signoutBtn.setOnAction(e -> {
            try {
                getSignedOut(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bookingBtn.setOnAction(e -> {
            try {
                switchToBookingForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        sendFeedbackBtn.setOnAction(e -> {
            try {
                switchToSendFeedbackForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        profileBtn.setOnAction(e -> {
            try {
                switchToProfile(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        searchBtn.setOnAction(e -> {
            try {
                switchToSearchForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
