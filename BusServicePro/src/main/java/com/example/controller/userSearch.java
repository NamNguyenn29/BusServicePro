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
            ComboBox tripComboBox = new ComboBox();
            tripComboBox.setId("tripBox");
            tripComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(tripComboBox);
        } else if ("Search by Route".equals(searchBox.getValue())) {
            ComboBox routeComboBox = new ComboBox();
            routeComboBox.setId("routeBox");
            routeComboBox.setPrefWidth(150);
            parentHBox.getChildren().add(routeComboBox);
        } else if ("Search by Stop".equals(searchBox.getValue())) {
            ComboBox stopComboBox1 = new ComboBox();
            ComboBox stopComboBox2 = new ComboBox();
            stopComboBox1.setId("stopBox1");
            stopComboBox1.setPrefWidth(150);
            stopComboBox2.setId("stopBox2");
            stopComboBox2.setPrefWidth(150);
            stopComboBox2.setTranslateX(50);
            parentHBox.getChildren().add(stopComboBox1);
            parentHBox.getChildren().add(stopComboBox2);
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
