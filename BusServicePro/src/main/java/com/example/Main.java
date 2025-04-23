package com.example;

import com.example.models.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {
//    @Override
//    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource(""));
//        stage.setTitle("Bus Booking System");
//        stage.setScene(new Scene(root,400,400));
//        stage.show();
//    }

    public static void main(String[] args) {
//        launch();


        Admin admin = new Admin("nam", "nam22", "nam", "123", "1234567");

    }
}
