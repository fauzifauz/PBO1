package com.krlsystem.controller;

import com.krlsystem.App;
import com.krlsystem.model.Passenger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class PassengerController {
    @FXML
    private StackPane contentPane;
    @FXML
    private Label viewTitle;
    @FXML
    private Text usernameText;
    @FXML
    private Label sidebarBalanceLabel;

    @FXML
    public void initialize() {
        Passenger p = (Passenger) App.getUser();
        usernameText.setText(p.getUsername().toUpperCase());
        updateBalanceDisplay();
        showDashboard();
    }

    public void updateBalanceDisplay() {
        Passenger p = (Passenger) App.getUser();
        sidebarBalanceLabel.setText(String.format("Saldo: Rp %.0f", p.getBalance()));
    }

    @FXML
    private void showDashboard() {
        loadView("passenger_booking", "Booking Tiket");
    }

    @FXML
    private void showHistory() {
        loadView("passenger_history", "Riwayat Perjalanan");
    }

    @FXML
    private void showTopUp() {
        loadView("passenger_topup", "Top Up Saldo");
    }

    private void loadView(String fxml, String title) {
        try {
            viewTitle.setText(title);
            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
            Parent view = loader.load();

            // If the loaded view is Booking, we might need to pass this controller or a way
            // to update balance
            Object controller = loader.getController();
            if (controller instanceof PassengerBookingController) {
                ((PassengerBookingController) controller).setParentController(this);
            } else if (controller instanceof PassengerTopUpController) {
                ((PassengerTopUpController) controller).setParentController(this);
            }

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
