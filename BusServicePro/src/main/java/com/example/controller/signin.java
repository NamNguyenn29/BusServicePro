package com.example.controller;

import com.example.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.DAO.UserDAO;
import com.example.models.User;

public class signin {

    @FXML
    private Button registerButton;
    @FXML
    private Button signinBtn;
    @FXML
    private static int userID;

    @FXML
    private void switchtoRegisterForm(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("/view/signup.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up");
        stage.setScene(registerScene);
        stage.show();
    }


    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox showPassword;

    @FXML
    private AnchorPane innerAnchorPane;
    @FXML
    private RadioButton userRd;
    @FXML
    private TextField usernameLogin;

    @FXML
    private RadioButton adminRd;

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
    private Hyperlink forgotPassword;

    @FXML
    private void switchToForgetPassword(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/forgotPassword.fxml"));
//        Parent forgotPasswordRoot = loader.load();
        URL fxmlLocation = getClass().getResource("/view/forgotPassword.fxml");

        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent forgotPasswordRoot = loader.load();

        forgotPassword forgotPasswordController = loader.getController();
//        forgotPasswordController.setContent(innerAnchorPane);

        AnchorPane newPane = new AnchorPane();
        List<Node> childrenCopy = new ArrayList<>(innerAnchorPane.getChildren());
        innerAnchorPane.getChildren().clear();
        newPane.getChildren().addAll(childrenCopy);

        forgotPasswordController.setContent(newPane);

        innerAnchorPane.getChildren().setAll(((AnchorPane) forgotPasswordRoot).getChildren());
    }


    @FXML
    private void getSignedIn(ActionEvent event) throws IOException {
        Stage stage = (Stage) signinBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("User Menu");
        newStage.show();
    }

    @FXML
    private void getSignedInAsAdmin(ActionEvent event) throws IOException {
        Stage stage = (Stage) signinBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminMenu.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Admin Menu");
        newStage.show();
    }

    @FXML
    private RadioButton userRadioBtn;
    @FXML
    private RadioButton adminRadioBtn;

    @FXML
    private void initialize() {


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

        registerButton.setOnAction(actionEvent -> {
            try {
                switchtoRegisterForm(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        forgotPassword.setOnAction(e -> {
            try {
                switchToForgetPassword(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        userRd.setSelected(true);
        signinBtn.setOnAction(e -> {
            String username = usernameLogin.getText();
            String password = passwordField.getText();
            if (userRd.isSelected()) {
                if (UserDAO.login(username, password) == null) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Tên người dùng hoặc mật mã không đúng");
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Info", "Đăng nhập thành công");
                    this.userID = UserDAO.login(username,password).getUserID();
                    try {
                        getSignedIn(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else if (adminRd.isSelected()) {
                if (UserDAO.login(username, password) == null) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Tên người dùng hoặc mật mã không đúng");
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Info", "Đăng nhập thành công");
                    try {
                        getSignedInAsAdmin(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
//        userRadioBtn.setSelected(true);
//        signinBtn.setOnAction(e -> {
//            try {
//                if (userRadioBtn.isSelected()) {
//                    getSignedIn(e);
//                } else if (adminRadioBtn.isSelected()) {
//                    getSignedInAsAdmin(e);
//                }
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }
    public static int getIDFromSignin(){
        return userID;
    }
}