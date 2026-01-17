package com.krlsystem.controller;

import com.krlsystem.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AdminController {
    @FXML
    private StackPane contentPane;
    @FXML
    private Label viewTitle;

    @FXML
    public void initialize() {
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        loadView("admin_dashboard", "Dashboard");
    }

    @FXML
    private void showStations() {
        loadView("admin_stations", "Kelola Stasiun");
    }

    @FXML
    private void showTariffs() {
        loadView("admin_tariffs", "Kelola Tarif");
    }

    @FXML
    private void showUsers() {
        loadView("admin_users", "Data User");
    }

    @FXML
    private void showReports() {
        loadView("admin_reports", "Laporan Transaksi");
    }

    private void loadView(String fxml, String title) {
        try {
            viewTitle.setText(title);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
            Parent view = loader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        App.setUser(null);
        App.setRoot("login");
    }
}
