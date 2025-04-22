package com.example.controll;

import com.example.model.Route;
import com.example.model.Trip;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private ComboBox<Route> routeBox;
    @FXML
    private ListView<Trip> tripList;
    @FXML
    private Button bookBtn;
    @FXML
    private Label statusLabel;

    private List<Trip> allTrips;

    @FXML
    public void initialize() {
        Route r1 = new Route("Hà Nội → Hải Phòng");
        Route r2 = new Route("Hà Nội → Ninh Bình");
        routeBox.setItems(FXCollections.observableArrayList(r1, r2));

        allTrips = new ArrayList<>();
        allTrips.add(new Trip(r1, LocalTime.of(8, 0)));
        allTrips.add(new Trip(r1, LocalTime.of(14, 0)));
        allTrips.add(new Trip(r2, LocalTime.of(9, 0)));

        routeBox.setOnAction(e -> updateTrips());

        bookBtn.setOnAction(e -> {
            Trip selected = tripList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                statusLabel.setText("\u0110\u00e3 \u0111\u1eb7t v\u00e9 chuy\u1ebfn: " + selected);
            } else {
                statusLabel.setText("Ch\u01b0a ch\u1ecdn chuy\u1ebfn xe!");
            }
        });
    }

    private void updateTrips() {
        Route selected = routeBox.getSelectionModel().getSelectedItem();
        if (selected != null) {
            List<Trip> filtered = allTrips.stream()
                    .filter(t -> t.getRoute().equals(selected))
                    .toList();
            tripList.setItems(FXCollections.observableArrayList(filtered));
        }
    }
}
