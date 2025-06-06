package com.example.controller;

import com.example.DAO.BookingDAO;
import com.example.DAO.TripDAO;
import com.example.models.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.DAO.RouteDAO;
import com.example.controller.signin;
import com.example.DAO.UserDAO;


import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class menu {
    @FXML
    private Button signoutBtn;
    @FXML
    private ComboBox<Trip> tripList;
    @FXML
    private ComboBox<Stop> startStopList;
    @FXML
    private ComboBox<Stop> endStopList;
    @FXML
    private Text username;


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
    private Button bookBtn;
    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Integer> order;
    @FXML
    private TableColumn<Booking, Trip> tripID;
    @FXML
    private TableColumn<Booking, Stop> departure;
    @FXML
    private TableColumn<Booking, Stop> arrival;




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
        List<Booking> bookings1= BookingDAO.getBookingsByUser(signin.getIDFromSignin());
        order.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(bookingTable.getItems().indexOf(cellData.getValue()) + 1)
        );
        System.out.println("Number of bookings: " + bookings1.size());
        System.out.println("User ID: " + signin.getIDFromSignin());
        order.setSortable(false);
        tripID.setCellValueFactory(new PropertyValueFactory<>("trip"));
        arrival.setCellValueFactory(new PropertyValueFactory<>("toStop"));
        departure.setCellValueFactory(new PropertyValueFactory<>("fromStop"));
        ObservableList<Booking> observableBookings1 = FXCollections.observableArrayList(bookings1);
        bookingTable.setItems(observableBookings1);
        User user1= UserDAO.getUserById(signin.getIDFromSignin());
        username.setText(user1.getName());
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
        List<Trip> avaiableTrip = TripDAO.getAllTrips();
        tripList.setItems(FXCollections.observableArrayList(avaiableTrip));
        tripList.getSelectionModel().selectedItemProperty().addListener((obs, oldTrip, selectedTrip) -> {
            if (selectedTrip != null) {
                Route selectedRoute = selectedTrip.getRoute();
                if (selectedRoute != null) {
                    List<Stop> stopsList = RouteDAO.getStopsForRoute(selectedRoute.getRouteID());
                    if (stopsList != null) {
                        ObservableList<Stop> observableStops = FXCollections.observableArrayList(stopsList);
                        startStopList.setItems(observableStops);
                        endStopList.setItems(observableStops);
                    }
                }
            }
        });

        bookBtn.setOnAction(e -> {
            Trip selectedTrip = tripList.getSelectionModel().getSelectedItem();
            Stop selectedStartStop = startStopList.getSelectionModel().getSelectedItem();
            Stop selectedEndStop = endStopList.getSelectionModel().getSelectedItem();
            int id = signin.getIDFromSignin();
            if (selectedTrip != null && selectedStartStop != null && selectedEndStop != null) {
                boolean success = BookingDAO.createBooking(selectedTrip, selectedStartStop, selectedEndStop, UserDAO.getUserById(id));
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Info", "Booking successful");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Info", "Booking failed");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "Please select trip and stops before booking");
            }
            bookingTable.getItems().clear();
            List<Booking> bookings2= BookingDAO.getBookingsByUser(signin.getIDFromSignin());
            order.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(bookingTable.getItems().indexOf(cellData.getValue()) + 1)
            );
            System.out.println("Number of bookings: " + bookings1.size());
            System.out.println("User ID: " + signin.getIDFromSignin());
            order.setSortable(false);
            tripID.setCellValueFactory(new PropertyValueFactory<>("trip"));
            arrival.setCellValueFactory(new PropertyValueFactory<>("toStop"));
            departure.setCellValueFactory(new PropertyValueFactory<>("fromStop"));
            ObservableList<Booking> observableBookings2 = FXCollections.observableArrayList(bookings2);
            bookingTable.setItems(observableBookings2);
            User user2= UserDAO.getUserById(signin.getIDFromSignin());
            username.setText(user2.getName());
        });

    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
