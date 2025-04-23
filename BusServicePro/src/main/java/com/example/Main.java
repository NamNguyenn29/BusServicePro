package com.example;

import com.example.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        BusTop busTop1 = new BusTop("a","dinhhoa");
        BusTop busTop2 = new BusTop("a","thudaumot");
        Route route = new Route("22","dinhhoa","thudaumot");
        route.addBusStop(busTop1);
        route.addBusStop(busTop2);
        Trip trip = new Trip(1, LocalDate.of(2025,4,23), LocalTime.of(22,00),20,route);

        SearchRoute searchRoute = new SearchRoute(new ArrayList<>());
        searchRoute.addRoute(route);
        List<Route> routeList = searchRoute.search("dinhhoa","thudaumot");
        for(Route r : routeList) {
            System.out.println(r.getStartLocation() + " " + r.getEndLocation());
        }
    }
}
