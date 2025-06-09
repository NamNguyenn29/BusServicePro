package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.DAO.UserDAO;
import com.example.models.User;

import java.io.IOException;

public class signup {
    @FXML
    private Button loginButton;
    @FXML
    private Button signupBtn;

    @FXML
    private void switchToLoginForm(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign In");
        stage.setScene(registerScene);
        stage.show();
    }
    public void switchToAnotherUIWithoutEvent() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();  // lấy stage từ node nào đó
        Parent newRoot = FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
        Scene newScene = new Scene(newRoot);
        stage.setScene(newScene);
        stage.show();
    }
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField yourName;


    @FXML
    private CheckBox showPassword;

    @FXML
    private AnchorPane innerAnchorPane;

    private TextField textField;

    @FXML
    private void togglePasswordVisibility() {
        if (showPassword.isSelected()) {
            textField.setVisible(true);
            textField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        } else {
            textField.setVisible(false);
            textField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
        }
    }

    @FXML
    private void getSignedUp(ActionEvent event) throws IOException {
        Stage stage = (Stage) signupBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("User Menu");
        newStage.show();
    }

    @FXML
    private void initialize() {
        loginButton.setOnAction(e -> {
            try {
                switchToLoginForm(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        textField = new TextField();
        textField.setVisible(false);
        textField.setManaged(false);
        textField.setPrefWidth(passwordField.getPrefWidth());
        textField.setPrefHeight(passwordField.getPrefHeight());
        textField.setPadding(new Insets(10, 15, 10, 15));
        textField.setStyle("-fx-font-family: 'Tahoma'; -fx-font-size: 12px;");

        AnchorPane.setTopAnchor(textField, AnchorPane.getTopAnchor(passwordField));
        AnchorPane.setLeftAnchor(textField, AnchorPane.getLeftAnchor(passwordField));
        AnchorPane.setRightAnchor(textField, AnchorPane.getRightAnchor(passwordField));

        textField.textProperty().bindBidirectional(passwordField.textProperty());
        innerAnchorPane.getChildren().add(textField);
        showPassword.setOnAction(e -> togglePasswordVisibility());

        signupBtn.setOnAction(e -> handleSignup());

    }
    private void handleSignup() {
        String username = usernameField .getText();
        String password = passwordField .getText();
        String email = emailField .getText();
        String phone = phoneField .getText();
        String fullname = yourName.getText();
        User user = new User(username, password,fullname,email, phone);
        if(username.isEmpty()||password.isEmpty()||email.isEmpty()||phone.isEmpty()||fullname.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu.");
        }else if(UserDAO.register(user)) {
            showAlert(Alert.AlertType.INFORMATION, "Thành công", "Đăng kí  thành công!");
            try {
                switchToAnotherUIWithoutEvent();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể chuyển giao diện.");
            }
        }else{
            showAlert(Alert.AlertType.INFORMATION, "Lỗi", "Thông tin không hợp lệ !");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

        signupBtn.setOnAction(e -> {
            try {
                getSignedUp(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
