//package com.example.models;
//
//import com.example.DAO.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//public class Main {
////    public static void main(String[] args) {
//////        Stop s1 = new Stop(1, "Stop A");
//////        Stop s2 = new Stop(2, "Stop B");
//////        Stop s3 = new Stop(3, "Stop C");
//////        StopDAO.addStop(s1);
//////        StopDAO.addStop(s2);
//////        StopDAO.addStop(s3);  ( tạo stop và add vào db)
////
////
//////        Stop s1 = StopDAO.getStopById(1);
//////        System.out.println(s1); ( lấy thử stop 1)
//////        Stop s2 = StopDAO.getStopById(2);
//////        Stop s3 = StopDAO.getStopById(3);
////
//////
////
////
////        List<Stop> stops = StopDAO.getAllStops(); // lấy tất cả các stop hiệnt tại
//////        for(Stop stop : stops) {
//////            System.out.println(stop.getStopName());
//////        }
////
////
//////        Route route = new Route(101, stops);
//////        RouteDAO.addRoute(route);
////
////        Route route = RouteDAO.getRouteById(101);
//////        for (int i = 0; i < stops.size(); i++) {
//////            RouteStopDAO.addRouteStop(route.getRouteID(), stops.get(i).getStopID(), i + 1);
//////        }
////        route.setStops(RouteDAO.getStopsForRoute(route.getRouteID()));
//////        System.out.println(route);
////
////
//////        Bus bus = new Bus(1, "ABC-123", 2, route);
//////        BusDAO.addBus(bus);
////
////        Bus bus = BusDAO.getBusById(1);
//////        System.out.println(bus);
////
////
//////        Stoptime stoptime1 = new Stoptime(StopDAO.getStopById(1), LocalTime.of(11, 0), LocalTime.of(11, 5));
//////        Stoptime stoptime2 = new Stoptime(StopDAO.getStopById(2), LocalTime.of(12, 30), LocalTime.of(12, 35));
//////        Stoptime stoptime3 = new Stoptime(StopDAO.getStopById(3), LocalTime.of(13, 0), LocalTime.of(13, 5));
//////        StoptimeDAO.addStoptime(stoptime1);
//////        StoptimeDAO.addStoptime(stoptime2);
//////        StoptimeDAO.addStoptime(stoptime3);
//////
////
////        Stoptime stoptime1 = StoptimeDAO.getStoptimeByStopID(1);
////        Stoptime stoptime2 = StoptimeDAO.getStoptimeByStopID(2);
////        Stoptime stoptime3 = StoptimeDAO.getStoptimeByStopID(3);
////
////
//////        System.out.println(stoptime1.getStop().getStopName());
////        List<Stoptime> stoptimes = Arrays.asList(stoptime1, stoptime2, stoptime3);
//////        for (Stoptime stoptime : stoptimes) {
//////            System.out.println(stoptime.getStop().getStopName());
//////        }
//////
//////
//////        Trip trip = new Trip(1001, route, stoptimes, bus);
//////        TripDAO.addTrip(trip);
////
////
////        Trip trip = TripDAO.getTripWithStopTimes(1001);
//////        System.out.println(trip);
////
//////        User user = new User(2,"nam22","123","nam","123","012424");
//////        UserDAO.register(user);
////        UserDAO.login("nam22", "123");
////        User user = UserDAO.getUserById(UserDAO.getCurrentID());
//////            System.out.println(user);
////
////
//////            BookingDAO.createBooking(trip,StopDAO.getStopById(1),StopDAO.getStopById(2),user);
//////            BookingDAO.createBooking(trip,StopDAO.getStopById(2),StopDAO.getStopById(3),user);
//////            BookingDAO.createBooking(trip,StopDAO.getStopById(3),StopDAO.getStopById(1),user);
////
////
//////        for(Booking booking : BookingDAO.getBookingsByUser(user.getUserID())) {
//////            System.out.println(booking);
//////        }
//////        for (Booking booking : BookingDAO.getAllBookings() ) {
//////            System.out.println(booking.getStartTime());
//////        }
////
////
//////        List<Stop> stops1 = StopDAO.getStopsByRouteID(101);
//////        for(Stop stop : stops1) {
//////            System.out.println(stop.getStopName());
//////        }
////
//////        List<Route> routes = RouteDAO.getRoutesByStopID(1);
//////        for(Route route1 : routes) {
//////            System.out.println(route1);
//////        }
////
////
//////         List<Trip> trips =   TripDAO.searchTrips(StopDAO.getStopById(1),StopDAO.getStopById(2));
//////         for(Trip trip1 : trips) {
//////             System.out.println(trip1);
//////         }
////
////
//////         String mess = " can cai thien dich vu";
//////         Feedback feedback = new Feedback(1,mess, LocalDate.now(),user.getUserID());
//////         FeedbackDAO.addFeedback(feedback);
////
//////            List<Feedback> feedbacks = FeedbackDAO.getFeedbacksByUser(user.getUserID());
//////            for(Feedback feedback : feedbacks) {
//////                System.out.println(feedback);
//////            }
//////        List<Feedback> feedbacks = FeedbackDAO.getAllFeedback();
//////        for(Feedback feedback : feedbacks) {
//////            System.out.println(feedback);
//////
////    Stoptime s4 = StoptimeDAO.getStoptimeByStopID(4);
////    StoptimeDAO.deleteStoptimeByStopID(4);
////    }
//}
package com.example.models;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign In");
        primaryStage.show();
    }
}
