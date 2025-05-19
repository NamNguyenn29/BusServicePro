package com.example.Controller;
import com.example.DAO.RouteDAO;
import com.example.models.Route;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class RouteController {

    @FXML
    private TextField stopIDField;

    @FXML
    private ListView<String> routeListView;

    @FXML
    private void handleShowAllRoutes() {
        try {
            List<Route> routes = RouteDAO.getAllRoute();

            routeListView.getItems().clear();
            if (routes.isEmpty()) {
                routeListView.getItems().add("Không có tuyến đường nào.");
            } else {
                for (Route route : routes) {
                    routeListView.getItems().add(route.getRouteName());
                }
            }
        } catch (Exception e) {
            showAlert("Lỗi khi tải tất cả tuyến đường: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearchByStopID() {
        try {
            String stopIDText = stopIDField.getText();

            if (stopIDText == null || stopIDText.isEmpty()) {
                showAlert("Vui lòng nhập Stop ID.");
                return;
            }

            int stopID = Integer.parseInt(stopIDText);
            List<Route> routes = RouteDAO.getRoutesByStopID(stopID);

            routeListView.getItems().clear();
            if (routes.isEmpty()) {
                routeListView.getItems().add("Không tìm thấy tuyến đường nào.");
            } else {
                for (Route route : routes) {
                    routeListView.getItems().add(route.getRouteName());
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Stop ID phải là số.");
        } catch (Exception e) {
            showAlert("ERROR " );
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
