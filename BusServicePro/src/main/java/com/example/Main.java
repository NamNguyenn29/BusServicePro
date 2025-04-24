package com.example;

import com.example.models.*;
import com.mysql.cj.xdevapi.Result;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void main(String[] args) throws SQLException {
//        launch();

//        Admin admin = new Admin("nam", "nam22", "nam", "123", "1234567");
//        BusTop busTop1 = new BusTop("a","dinhhoa");
//        BusTop busTop2 = new BusTop("a","thudaumot");
//        Route route = new Route("22","dinhhoa","thudaumot");
//        Trip trip = new Trip(1, LocalDate.of(2025,4,23), LocalTime.of(22,00),20,route);
//
//        SearchRoute searchRoute = new SearchRoute(new ArrayList<>());
//        searchRoute.addRoute(route);
//        List<Route> routeList = searchRoute.search("dinhhoa","thudaumot");
//        for(Route r : routeList) {
//            System.out.println(r.getStartLocation() + " " + r.getEndLocation());
//        }
        Connection conn = DatabaseConnection.getConnection();
        if(conn!=null) {
                String sql = "select * from user";
                Statement stament = conn.createStatement();
                ResultSet rs =  stament.executeQuery(sql);
                while (rs.next()) {
                    int id = rs.getInt("userID");
                    String email = rs.getString("email");
                    String fullName = rs.getString("fullName");
                    String username = rs.getString("username");
                    String password = rs.getString("userpassword");
                    String phone  = rs.getString("phone");
                    Customer cus = new Customer(fullName,email,username,password,phone);
                    System.out.println(cus.getName());
                }
//
        }

    }
}
