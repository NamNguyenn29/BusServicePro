package com.example.controller;

import com.example.DAO.*;
import com.example.models.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class adminMenu {
    @FXML
    private Button signoutBtn;
    @FXML
    private ComboBox<Route> route;

    @FXML
    private TextField stopID;
    @FXML
    private TextField stopName;
    @FXML
    private Button addBusStopBtn;
    @FXML
    private ComboBox<Stop> scheduleBusStop;
    @FXML
    private TextField departureH;
    @FXML
    private TextField departureM;
    @FXML
    private TextField arrivalH;
    @FXML
    private TextField arrivalM;
    @FXML
    private Button addScheduleBtn;
    @FXML
    private TextField routeID;
    @FXML
    private ComboBox<Stop> routeBusStop;
    @FXML
    private Button addRouteBtn;
    @FXML
    private TextField busID;
    @FXML
    private TextField licensePlate;
    @FXML
    private TextField capacity;
    @FXML
    private ComboBox<Route> busRoute;
    @FXML
    private Button addBusBtn;
    @FXML
    private TextField tripID;
    @FXML
    private ComboBox<Stop> departureTime;
    @FXML
    private ComboBox<Stop> arrivalTime;
    @FXML
    private ComboBox<Bus> bus;
    @FXML
    private Button createTripBtn;

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
        Admin admin = AdminDAO.getAdminByID(signin.getIDFromSignin());
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
        List<Route> routes = RouteDAO.getAllRoutes();
        List<Bus> buses = BusDAO.getAllBuses();
        List<Stop> stops = StopDAO.getAllStops();

        addBusStopBtn.setOnAction(e -> {
            try {
                Stop stop = new Stop(Integer.valueOf(stopID.getText()), stopName.getText());
                if (StopDAO.addStop(stop)) {
                    showAlert(Alert.AlertType.INFORMATION, "Infor", "Stop Added");
                    List<Stop> updatedStops = StopDAO.getAllStops();
                    scheduleBusStop.setItems(FXCollections.observableArrayList(updatedStops));
                    routeBusStop.setItems(FXCollections.observableArrayList(updatedStops));
                    departureTime.setItems(FXCollections.observableArrayList(updatedStops));
                    arrivalTime.setItems(FXCollections.observableArrayList(updatedStops));
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Stop is existed");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields");
            }

        });
        routeBusStop.setItems(FXCollections.observableArrayList(stops));
        scheduleBusStop.setItems(FXCollections.observableArrayList(stops));
        addScheduleBtn.setOnAction(e -> {
            int departurehour = Integer.parseInt(departureH.getText());
            int departureminute = Integer.parseInt(departureM.getText());
            int arrivalhour = Integer.parseInt(arrivalH.getText());
            int arrivalminute = Integer.parseInt(arrivalM.getText());
            try {
                Stop selectedStop = scheduleBusStop.getSelectionModel().getSelectedItem();
                LocalTime departureTime = LocalTime.of(departurehour, departureminute);
                LocalTime arrivalTime = LocalTime.of(arrivalhour, arrivalminute);
                Stoptime stoptime = new Stoptime(selectedStop, departureTime, arrivalTime);
                if (StoptimeDAO.addStoptime(stoptime)) {
                    showAlert(Alert.AlertType.INFORMATION, "Infor", "StopTime Added");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "StopTime is invalid");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields");
            }
        });
        addRouteBtn.setOnAction(e -> {
            try {
                List<Stop> stopList = new ArrayList<>();
                stopList.add(routeBusStop.getSelectionModel().getSelectedItem());
                Route route1 = new Route(Integer.valueOf(routeID.getText()), stopList);
                List<Route> routeList = RouteDAO.getAllRoutes();
                boolean found = false;
                for(Route route : routeList) {
                    if (route.getRouteID() == route1.getRouteID()) {
                        found = true;
                    }
                }
                if (!found) {
                    RouteDAO.addRoute(route1);
                    showAlert(Alert.AlertType.INFORMATION, "Infor", "Route Added");
                    List<Route> updatedRoutes = RouteDAO.getAllRoutes();
                    busRoute.setItems(FXCollections.observableArrayList(updatedRoutes));
                    route.setItems(FXCollections.observableArrayList(updatedRoutes));
                }else{
                    Route route2 = RouteDAO.getRouteById(Integer.valueOf(routeID.getText()));
                    List<Integer> stopsID = RouteStopDAO.getStopsByRouteId(route2.getRouteID());
                    List<Stop> stop2s = new ArrayList<>();
                    for(Integer stopID : stopsID) {
                        stop2s.add(StopDAO.getStopById(stopID));
                    }

                    if(RouteStopDAO.addRouteStop(Integer.valueOf(routeID.getText()),routeBusStop.getSelectionModel().getSelectedItem().getStopID(),stop2s.size()+1)){
                        showAlert(Alert.AlertType.INFORMATION, "Infor", "Route Updated Successfully");
                    }else{
                        showAlert(Alert.AlertType.ERROR, "Error", "Route Update Failed");
                    }
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields");
            }

        });

        busRoute.setItems(FXCollections.observableArrayList(routes));
        addBusBtn.setOnAction(e -> {
            try {
                Route route1 = busRoute.getSelectionModel().getSelectedItem();
                Bus bus1 = new Bus(Integer.valueOf(busID.getText()), licensePlate.getText(), Integer.valueOf(capacity.getText()), route1);
                if (BusDAO.addBus(bus1)) {
                    showAlert(Alert.AlertType.INFORMATION, "Infor", "Bus Added");
                    List<Bus> updatedBuses = BusDAO.getAllBuses();
                    bus.setItems(FXCollections.observableArrayList(updatedBuses));
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Bus is existed");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields");
            }
        });
        route.setItems(FXCollections.observableArrayList(routes));
        bus.setItems(FXCollections.observableArrayList(buses));
        departureTime.setItems(FXCollections.observableArrayList(stops));
        arrivalTime.setItems(FXCollections.observableArrayList(stops));
        createTripBtn.setOnAction(e -> {
                Route route1 = route.getSelectionModel().getSelectedItem();
                Stop top1 = departureTime.getSelectionModel().getSelectedItem();
                Stop top2 = arrivalTime.getSelectionModel().getSelectedItem();
                Stoptime stoptime1 = StoptimeDAO.getStoptimeByStopID(top1.getStopID());
                Stoptime stoptime2 = StoptimeDAO.getStoptimeByStopID(top2.getStopID());
                List<Stoptime> stoptimeList = new ArrayList<>();
                stoptimeList.add(stoptime1);
                stoptimeList.add(stoptime2);
                Bus bus1 = bus.getSelectionModel().getSelectedItem();
                Trip trip = new Trip(Integer.valueOf(tripID.getText()), route1, stoptimeList, bus1);
                if (TripDAO.addTrip(trip)) {
                    showAlert(Alert.AlertType.INFORMATION, "Infor", "Trip Added");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Trip is existed");
                }
        });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
}