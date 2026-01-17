package com.krlsystem.controller;

import com.krlsystem.App;
import com.krlsystem.dao.UserDAO;
import com.krlsystem.model.Admin;
import com.krlsystem.model.Passenger;
import com.krlsystem.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Harap isi semua field!");
            return;
        }

        User user = userDAO.login(username, password);
        if (user != null) {
            App.setUser(user);
            if (user instanceof Admin) {
                App.setRoot("admin_main");
            } else {
                App.setRoot("passenger_main");
            }
        } else {
            statusLabel.setText("Username atau password salah!");
        }
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Harap isi username dan password!");
            return;
        }

        if (userDAO.register(username, password)) {
            statusLabel.setText("Registrasi berhasil! Silakan login.");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel.setText("Username sudah digunakan!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
