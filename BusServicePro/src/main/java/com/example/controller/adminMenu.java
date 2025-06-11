package com.example.controller;

import com.example.DAO.AdminDAO;
import com.example.models.Admin;
import com.example.models.Bus;
import com.example.models.Route;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import com.example.DAO.RouteDAO;
import com.example.DAO.BusDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class adminMenu {
    @FXML
    private Button signoutBtn;
    @FXML
    private ComboBox<Route>route;
    @FXML
    private ComboBox<Bus>bus;

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
    private Text adminInfor;

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
    private Button viewBookingBtn;

    @FXML
    private void switchToViewBooking(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/adminBooking.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent adminBookingRoot = loader.load();

        adminBooking adminBookingController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        adminBookingController.setContent(newAnchorPane);

        parentAnchorPane.getChildren().setAll(((AnchorPane) adminBookingRoot).getChildren());
    }

    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }

    @FXML
    private Button tripManagement;

    @FXML
    private void switchToTripManagement(ActionEvent event) throws IOException {
        URL fxmlLocation = getClass().getResource("/view/adminMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent tripManagementRoot = loader.load();

        adminMenu tripManagementController = loader.getController();

        AnchorPane newAnchorPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(parentAnchorPane.getChildren());
        parentAnchorPane.getChildren().clear();
        newAnchorPane.getChildren().addAll(childrenCopy);

        tripManagementController.setContent(newAnchorPane);

        AnchorPane tripManagementRootPane = (AnchorPane) tripManagementRoot;
        AnchorPane anchorPane = (AnchorPane) tripManagementRootPane.lookup("#parentAnchorPane");
        parentAnchorPane.getChildren().setAll(anchorPane.getChildren());
    }

    @FXML
    private void initialize() {
        Admin admin= AdminDAO.getAdminByID(signin.getIDFromSignin());
        adminInfor.setText(admin.getUsername());
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

        viewBookingBtn.setOnAction(e -> {
            try {
                switchToViewBooking(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        tripManagement.setOnAction(e -> {
            try {
                switchToTripManagement(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        List<Route>routes= RouteDAO.getAllRoutes();
        route.setItems(FXCollections.observableArrayList(routes));
        List<Bus>buses= BusDAO.getAllBuses();
        bus.setItems(FXCollections.observableArrayList(buses));
    }
}