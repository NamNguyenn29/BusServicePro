package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;

public class userSearch {
    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
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
            ComboBox tripComboBox1 = new ComboBox();
            ComboBox tripComboBox2 = new ComboBox();
            tripComboBox1.setId("tripBox1");
            tripComboBox1.setPrefWidth(150);
            tripComboBox2.setId("tripBox2");
            tripComboBox2.setPrefWidth(150);
            tripComboBox2.setTranslateX(50);
            parentHBox.getChildren().add(tripComboBox1);
            parentHBox.getChildren().add(tripComboBox2);
            TableView<String> searchTripTable = new TableView<>();
            TableColumn<String, String> column0 = new TableColumn<>("Order");
            column0.setId("order");
            TableColumn<String, String> column1 = new TableColumn<>("Trip ID");
            column1.setId("tripID");
            TableColumn<String, String> column2 = new TableColumn<>("Departure\n Stop");
            column2.setId("departureStop");
            TableColumn<String, String> column3 = new TableColumn<>("Arrival\n Stop");
            column3.setId("arrivalStop");
            TableColumn<String, String> column4 = new TableColumn<>("Departure\n Start Location");
            column4.setId("departureStartLocation");
            TableColumn<String, String> column5 = new TableColumn<>("Arrival\n Start Location");
            column5.setId("arrivalStartLocation");
            TableColumn<String, String> column6 = new TableColumn<>("Departure\n End Location");
            column6.setId("departureEndLocation");
            TableColumn<String, String> column7 = new TableColumn<>("Arrival\n End Location");
            column7.setId("arrivalEndLocation");
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
            tableAnchorPane.getChildren().add(searchTripTable);
            AnchorPane.setTopAnchor(searchTripTable, 0.0);
            AnchorPane.setRightAnchor(searchTripTable, 0.0);
            AnchorPane.setBottomAnchor(searchTripTable, 0.0);
            AnchorPane.setLeftAnchor(searchTripTable, 0.0);
        } else if ("Search by Route".equals(searchBox.getValue())) {
            ComboBox routeComboBox = new ComboBox();
            routeComboBox.setId("routeBox");
            routeComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(routeComboBox);
            TableView<String> searchRouteTable = new TableView<>();
            TableColumn<String, String> column0 = new TableColumn<>("Order");
            column0.setId("order");
            TableColumn<String, String> column1 = new TableColumn<>("Route ID");
            column1.setId("routeID");
            column0.setPrefWidth(334);
            column1.setPrefWidth(334);
            searchRouteTable.getColumns().addAll(column0, column1);
            tableAnchorPane.getChildren().add(searchRouteTable);
            AnchorPane.setTopAnchor(searchRouteTable, 0.0);
            AnchorPane.setRightAnchor(searchRouteTable, 0.0);
            AnchorPane.setBottomAnchor(searchRouteTable, 0.0);
            AnchorPane.setLeftAnchor(searchRouteTable, 0.0);
        } else if ("Search by Stop".equals(searchBox.getValue())) {
            ComboBox stopComboBox = new ComboBox();
            stopComboBox.setId("stopBox");
            stopComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(stopComboBox);
            TableView<String> searchStopTable = new TableView<>();
            TableColumn<String, String> column0 = new TableColumn<>("Order");
            column0.setId("order");
            TableColumn<String, String> column1 = new TableColumn<>("Route ID");
            column1.setId("routeID");
            TableColumn<String, String> column2 = new TableColumn<>("Departure");
            column2.setId("departure");
            TableColumn<String, String> column3 = new TableColumn<>("Arrival");
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
        }
    }

    @FXML
    private void initialize() {
        searchBox.getSelectionModel().selectFirst();

        searchBox.setOnAction(e -> {
            try {
                createComboBoxBasedOnSearch();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
