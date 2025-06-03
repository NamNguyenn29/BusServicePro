package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class userProfile {
    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }
}
