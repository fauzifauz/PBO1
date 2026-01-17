package com.krlsystem.controller;

import com.krlsystem.App;
import com.krlsystem.dao.UserDAO;
import com.krlsystem.model.Passenger;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PassengerTopUpController {
    @FXML
    private TextField amountField;
    @FXML
    private Label statusLabel;

    private UserDAO userDAO = new UserDAO();
    private PassengerController parentController;

    public void setParentController(PassengerController parent) {
        this.parentController = parent;
    }

    @FXML
    private void handleTopUp() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                statusLabel.setText("Jumlah harus lebih dari 0!");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            Passenger p = (Passenger) App.getUser();
            double newBalance = p.getBalance() + amount;

            if (userDAO.updateBalance(p.getId(), newBalance)) {
                p.setBalance(newBalance);
                statusLabel.setText("Top Up berhasil!");
                statusLabel.setStyle("-fx-text-fill: green;");
                amountField.clear();
                parentController.updateBalanceDisplay();
            } else {
                statusLabel.setText("Gagal melakukan top up.");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Input harus angka!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
