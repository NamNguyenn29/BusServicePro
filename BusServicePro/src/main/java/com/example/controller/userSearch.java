package com.example.controller;

import com.example.DAO.RouteDAO;
import com.example.DAO.StopDAO;
import com.example.models.Route;
import com.example.models.Stop;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import com.example.models.SearchTrip;
import com.example.models.TripLegDisplay;
import com.example.DAO.TripDAO;
import com.example.models.Trip;
import com.example.models.Stoptime;
import com.example.DAO.StoptimeDAO;
import javafx.scene.text.TextAlignment;


import java.io.IOException;

public class userSearch {
    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }
    public ObservableList<TripLegDisplay> convertTripsToLegs(List<Trip> trips) {
        ObservableList<TripLegDisplay> list = FXCollections.observableArrayList();
        int globalSTT = 1;

        for (Trip trip : trips) {
            List<Stoptime> times = trip.getStoptimes();
            for (int i = 0; i < times.size() - 1; i++) {
                Stoptime from = times.get(i);
                Stoptime to = times.get(i + 1);
                if (from.getStop().equals(to.getStop()) &&
                        from.getDepartureTime().equals(to.getArrivalTime())) {
                    continue;
                }
                list.add(new TripLegDisplay(
                        globalSTT++,
                        trip.getTripID(),
                        from.getStop(),
                        to.getStop(),
                        from.getArrivalTime(),
                        from.getDepartureTime(),
                        to.getArrivalTime(),
                        to.getDepartureTime()

                ));
            }
            globalSTT=1;
        }

        return list;
    }


    @FXML
    private HBox parentHBox;

    @FXML
    private ComboBox searchBox;

    @FXML
    private AnchorPane tableAnchorPane;

    @FXML
    private void createComboBoxBasedOnSearch() throws IOException {
        parentHBox.getChildren().clear();

        if ("Search by Trip".equals(searchBox.getValue())) {
            ComboBox<Stop> tripComboBox1 = new ComboBox();
            ComboBox<Stop> tripComboBox2 = new ComboBox();
            tripComboBox1.setId("tripBox1");
            tripComboBox1.setPrefWidth(150);
            tripComboBox2.setId("tripBox2");
            tripComboBox2.setPrefWidth(150);
            tripComboBox2.setTranslateX(50);
            parentHBox.getChildren().add(tripComboBox1);
            parentHBox.getChildren().add(tripComboBox2);
            TableView<TripLegDisplay> searchTripTable = new TableView<>();
            TableColumn<TripLegDisplay, Integer> column0 = new TableColumn<>("Order");
            column0.setId("order");
            TableColumn<TripLegDisplay, Integer> column1 = new TableColumn<>("Trip ID");
            column1.setId("tripID");
            TableColumn<TripLegDisplay, Stop> column2 = new TableColumn<>("Departure\n Stop");
            column2.setId("departureStop");
            TableColumn<TripLegDisplay, Stop> column3 = new TableColumn<>("Arrival\n Stop");
            column3.setId("arrivalStop");
            TableColumn<TripLegDisplay, LocalTime> column4 = new TableColumn<>("Arrival\n Start Time");
            column4.setId("arrivalStartLocation");
            TableColumn<TripLegDisplay, LocalTime> column5 = new TableColumn<>("Departure\n Start Time");
            column5.setId("departureStartLocation");
            TableColumn<TripLegDisplay, LocalTime> column6 = new TableColumn<>("Arrival\n End Time");
            column6.setId("arrivalEndLocation");
            TableColumn<TripLegDisplay, LocalTime> column7 = new TableColumn<>("Departure\n End Time");
            column7.setId("departureEndLocation");
            column0.setPrefWidth(71);
            column1.setPrefWidth(71);
            column2.setPrefWidth(87);
            column3.setPrefWidth(87);
            column4.setPrefWidth(87);
            column5.setPrefWidth(87);
            column6.setPrefWidth(87);
            column7.setPrefWidth(87);
            column0.setStyle("-fx-alignment: CENTER;");
            column2.setStyle("-fx-alignment: CENTER;");
            column3.setStyle("-fx-alignment: CENTER;");
            column4.setStyle("-fx-alignment: CENTER;");
            column5.setStyle("-fx-alignment: CENTER;");
            column6.setStyle("-fx-alignment: CENTER;");
            column7.setStyle("-fx-alignment: CENTER;");
            searchTripTable.getColumns().addAll(column0, column1, column2, column3, column4, column5, column6, column7);
            centerTableHeaders(searchTripTable);
            tableAnchorPane.getChildren().add(searchTripTable);
            AnchorPane.setTopAnchor(searchTripTable, 0.0);
            AnchorPane.setRightAnchor(searchTripTable, 0.0);
            AnchorPane.setBottomAnchor(searchTripTable, 0.0);
            AnchorPane.setLeftAnchor(searchTripTable, 0.0);
            List<Stop>allStops = StopDAO.getAllStops();
            tripComboBox1.setItems(FXCollections.observableArrayList(allStops));
            tripComboBox2.setItems(FXCollections.observableArrayList(allStops));
            tripComboBox2.setOnAction(event -> {
            Stop selectedStop1 = tripComboBox1.getValue();
            Stop selectedStop2 = tripComboBox2.getValue();
            List<Trip> tripList=TripDAO.searchTrips(selectedStop1,selectedStop2);
            List<Trip> realTripList = new ArrayList<>();
            for(Trip trip : tripList) {
                Trip realTrip=TripDAO.getTripWithStopTimes(trip.getTripID());
                realTripList.add(realTrip);
            }
            column0.setCellValueFactory(new PropertyValueFactory<>("stt"));
            column1.setCellValueFactory(new PropertyValueFactory<>("tripId"));
            column2.setCellValueFactory(new PropertyValueFactory<>("departureStop"));
            column3.setCellValueFactory(new PropertyValueFactory<>("arrivalStop"));
            column4.setCellValueFactory(new PropertyValueFactory<>("departureTime1"));
            column5.setCellValueFactory(new PropertyValueFactory<>("arrivalTime1"));
            column6.setCellValueFactory(new PropertyValueFactory<>("departureTime2"));
            column7.setCellValueFactory(new PropertyValueFactory<>("arrivalTime2"));
            ObservableList<TripLegDisplay> displayList = convertTripsToLegs(realTripList);
            searchTripTable.setItems(displayList);
            });

        } else if ("Search by Route".equals(searchBox.getValue())) {
            ComboBox <Route>routeComboBox = new ComboBox();
            routeComboBox.setId("routeBox");
            routeComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(routeComboBox);
            TableView<Stop> searchRouteTable = new TableView<>();
            TableColumn<Stop, Integer> column0 = new TableColumn<>("Order");
            column0.setId("order");
            TableColumn<Stop, Stop> column1 = new TableColumn<>("Route ID");
            column1.setId("StopID");
            column0.setPrefWidth(334);
            column1.setPrefWidth(334);
            searchRouteTable.getColumns().addAll(column0, column1);
            tableAnchorPane.getChildren().add(searchRouteTable);
            AnchorPane.setTopAnchor(searchRouteTable, 0.0);
            AnchorPane.setRightAnchor(searchRouteTable, 0.0);
            AnchorPane.setBottomAnchor(searchRouteTable, 0.0);
            AnchorPane.setLeftAnchor(searchRouteTable, 0.0);
            List<Route> routeList = RouteDAO.getAllRoutes();
            routeComboBox.setItems(FXCollections.observableArrayList(routeList));
            routeComboBox.setOnAction(e -> {
                Route selectedStop = routeComboBox.getSelectionModel().getSelectedItem();
                if (selectedStop != null) {
                    List<Stop> stopList = StopDAO.getStopsByRouteID(selectedStop.getRouteID());
                    column0.setCellValueFactory(column ->
                            new ReadOnlyObjectWrapper<>(searchRouteTable.getItems().indexOf(column.getValue()) + 1)
                    );
                    column1.setCellValueFactory(cellData ->
                            new ReadOnlyObjectWrapper<>(cellData.getValue().getStop()));
                    searchRouteTable.setItems(FXCollections.observableArrayList(stopList));
                } else {
                    searchRouteTable.getItems().clear();
                }
            });
        } else if ("Search by Stop".equals(searchBox.getValue())) {
            ComboBox<Stop> stopComboBox = new ComboBox();
            stopComboBox.setId("stopBox");
            stopComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(stopComboBox);
            TableView<Route> searchStopTable = new TableView<>();
            TableColumn<Route, Integer> column0 = new TableColumn<>("Order");
            column0.setId("order");
            TableColumn<Route, Integer> column1 = new TableColumn<>("Route ID");
            column1.setId("routeID");
            TableColumn<Route, Stop> column2 = new TableColumn<>("Departure");
            column2.setId("departure");
            TableColumn<Route, Stop> column3 = new TableColumn<>("Arrival");
            column3.setId("arrival");
            column0.setPrefWidth(167.5);
            column1.setPrefWidth(167.5);
            column2.setPrefWidth(167.5);
            column3.setPrefWidth(167.5);
            searchStopTable.getColumns().addAll(column0, column1, column2, column3);
            tableAnchorPane.getChildren().add(searchStopTable);
            AnchorPane.setTopAnchor(searchStopTable, 0.0);
            AnchorPane.setRightAnchor(searchStopTable, 0.0);
            AnchorPane.setBottomAnchor(searchStopTable, 0.0);
            AnchorPane.setLeftAnchor(searchStopTable, 0.0);
            List<Stop> stopList = StopDAO.getAllStops();
            stopComboBox.setItems(FXCollections.observableArrayList(stopList));
            column0.setCellValueFactory(column ->
                    new ReadOnlyObjectWrapper<>(searchStopTable.getItems().indexOf(column.getValue()) + 1)
            );
            column1.setCellValueFactory(new PropertyValueFactory<>("routeID"));
            column2.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(cellData.getValue().getFStop()));

            column3.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(cellData.getValue().getEStop()));
            stopComboBox.setOnAction(e -> {Stop selectedStop = stopComboBox.getSelectionModel().getSelectedItem();
                if (selectedStop != null) {
                    List<Route> routeList = RouteDAO.getRoutesByStopID(selectedStop.getStopID());
                    searchStopTable.setItems(FXCollections.observableArrayList(routeList));
                } else {
                    searchStopTable.getItems().clear();
                }
            });
            searchStopTable.getItems().clear();
        }
    }

    //    This is the function for center the column headers in the Search by Trip table,
//    if you ask me how the hell does this work then I have no idea.
    private void centerTableHeaders(TableView<?> table) {
        for (TableColumn<?, ?> column : table.getColumns()) {
            String headerText = column.getText();

            Label headerLabel = new Label(headerText);
            headerLabel.setMaxWidth(Double.MAX_VALUE);
            headerLabel.setAlignment(Pos.CENTER);
            headerLabel.setTextAlignment(TextAlignment.CENTER);
            headerLabel.setStyle("-fx-alignment: center; -fx-text-alignment: center;");

            column.setGraphic(headerLabel);
            column.setText("");
        }
    }

    @FXML
    private void initialize() {
        searchBox.getSelectionModel().selectFirst();
        try {
                createComboBoxBasedOnSearch();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }
}