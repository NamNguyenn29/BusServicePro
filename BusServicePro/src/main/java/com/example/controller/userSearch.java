package com.example.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import com.example.DAO.BusDAO;
import com.example.DAO.StopDAO;
import com.example.DAO.RouteDAO;
import com.example.models.Stop;
import com.example.models.Route;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Button cancelBtn;
    @FXML
    private ComboBox<String> searchBox;
    @FXML
    private TableView<Route> searchTable;
    @FXML
    private TableColumn<Route,Integer>route;
    @FXML
    private TableColumn<Route,Stop>departure;
    @FXML
    private TableColumn<Route,Stop>arrival;

    private void getSignedIn(ActionEvent event) throws IOException {
        Stage stage = (Stage) searchBox.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("User Menu");
        newStage.show();
    }




    @FXML
    private void createComboBoxBasedOnSearch() throws IOException {
        parentHBox.getChildren().clear();

        if ("Search by Trip".equals(searchBox.getValue())) {
            searchTable.getItems().clear();
            ComboBox<Stop> tripComboBox1 = new ComboBox();
            ComboBox<Stop> tripComboBox2 = new ComboBox();
            tripComboBox1.setId("tripBox1");
            tripComboBox1.setPrefWidth(150);
            tripComboBox2.setId("tripBox2");
            tripComboBox2.setPrefWidth(150);
            tripComboBox2.setTranslateX(50);
            parentHBox.getChildren().add(tripComboBox1);
            parentHBox.getChildren().add(tripComboBox2);
            List<Stop>allStops = StopDAO.getAllStops();
            tripComboBox1.setItems(FXCollections.observableArrayList(allStops));
            tripComboBox2.setItems(FXCollections.observableArrayList(allStops));
        } else if ("Search by Route".equals(searchBox.getValue())) {
            searchTable.getItems().clear();
            ComboBox<Route> routeComboBox = new ComboBox();
            routeComboBox.setId("routeBox");
            routeComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(routeComboBox);
            List<Route> routeList = RouteDAO.getAllRoutes();
            routeComboBox.setItems(FXCollections.observableArrayList(routeList));
            routeComboBox.setOnAction(e -> {
                Route selectedStop = routeComboBox.getSelectionModel().getSelectedItem();
                if (selectedStop != null) {
                    List<Stop> stopList = StopDAO.getStopsByRouteID(selectedStop.getRouteID());
                } else {
                    searchTable.getItems().clear();
                }
            });
        } else if ("Search by Stop".equals(searchBox.getValue())) {
           searchTable.getItems().clear();
           ComboBox <Stop>stopComboBox = new ComboBox();
           stopComboBox.setId("stopBox");
           stopComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(stopComboBox);
           List<Stop> stopList = StopDAO.getAllStops();
            stopComboBox.setItems(FXCollections.observableArrayList(stopList));
           route.setCellValueFactory(new PropertyValueFactory<>("routeID"));
            departure.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(cellData.getValue().getFStop()));

            arrival.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(cellData.getValue().getEStop()));

          stopComboBox.setOnAction(e -> {Stop selectedStop = stopComboBox.getSelectionModel().getSelectedItem();
                if (selectedStop != null) {
                    List<Route> routeList = RouteDAO.getRoutesByStopID(selectedStop.getStopID());

                    searchTable.setItems(FXCollections.observableArrayList(routeList));
               } else {
                   searchTable.getItems().clear();
               }
           });
           searchTable.getItems().clear();


        }
    }

    @FXML
    private void initialize() {
        searchBox.getSelectionModel().selectFirst();
        try {
            createComboBoxBasedOnSearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cancelBtn.setOnAction(e -> {
            try {
                getSignedIn(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

//        searchBox.setOnAction(e -> {
//            try {
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });
    }
}
