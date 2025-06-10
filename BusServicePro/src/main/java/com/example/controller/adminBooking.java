package com.example.controller;

import com.example.models.Booking;
import com.example.models.Stop;
import com.example.models.User;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.example.DAO.BookingDAO;

import java.time.LocalTime;
import java.util.List;


public class adminBooking {
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private TableView adminBookingTable;
    @FXML
    private TableColumn<Booking,Integer>bookingID;
    @FXML
    private TableColumn<Booking,Integer>tripID;
    @FXML
    private TableColumn<Booking, Stop>departureStop;
    @FXML
    private TableColumn<Booking, Stop>arrivalStop;
    @FXML
    private TableColumn<Booking, LocalTime>departureTime;
    @FXML
    private TableColumn<Booking, User>passenger;

    @FXML
    public void setContent(AnchorPane parentAnchorPane) {
        parentAnchorPane.getChildren().setAll(parentAnchorPane.getChildren());
    }
    public void initialize() {
        List<Booking> bookingList = BookingDAO.getAllBookings();
        bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        tripID.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getStripIDFromStrip()));
        departureStop.setCellValueFactory(new PropertyValueFactory<>("fromStop"));
        arrivalStop.setCellValueFactory(new PropertyValueFactory<>("toStop"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        passenger.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getUser()));
        adminBookingTable.setItems(FXCollections.observableArrayList(bookingList));
    }

}
