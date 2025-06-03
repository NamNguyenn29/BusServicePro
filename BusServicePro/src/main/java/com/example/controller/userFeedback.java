package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class userFeedback {
    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }
}
