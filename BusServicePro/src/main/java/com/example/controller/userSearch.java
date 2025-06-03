package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

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
        } else if ("Search by Route".equals(searchBox.getValue())) {
            ComboBox routeComboBox = new ComboBox();
            routeComboBox.setId("routeBox");
            routeComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(routeComboBox);
        } else if ("Search by Stop".equals(searchBox.getValue())) {
            ComboBox stopComboBox = new ComboBox();
            stopComboBox.setId("stopBox");
            stopComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(stopComboBox);
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
