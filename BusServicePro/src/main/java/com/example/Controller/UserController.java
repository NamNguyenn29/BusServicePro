package com.example.Controller;

import com.example.DAO.UserDAO;
import com.example.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UserController {

    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField fullName;

    @FXML
    private void handleButtonClick() {
        String userName = this.userName.getText();
        String password = this.password.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        String fullName = this.fullName.getText();

        User user = new User(userName, password, fullName, email, phone);
//        boolean signIn = UserDAO.register(user);
        boolean signIn = UserDAO.re
        if (signIn) {
            showAlert(Alert.AlertType.INFORMATION, "User registered successfully");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/views/LoginView.fxml"));
                Parent loginRoot = loader.load();
                Scene loginScene = new Scene(loginRoot);

                Stage stage = (Stage) this.userName.getScene().getWindow();
                stage.setScene(loginScene);
                stage.setTitle("Login");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Cannot load login screen.");
            }
            boolean login = UserDAO.login(user.getUsername(), user.getPassword());
            if (login) {
                showAlert(Alert.AlertType.INFORMATION, "Login successful");
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid username or password");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Username or password already exists");
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
