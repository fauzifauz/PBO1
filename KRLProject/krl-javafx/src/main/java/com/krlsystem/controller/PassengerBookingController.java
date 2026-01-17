package com.krlsystem.controller;

import com.krlsystem.App;
import com.krlsystem.dao.StationDAO;
import com.krlsystem.dao.TariffDAO;
import com.krlsystem.dao.TransactionDAO;
import com.krlsystem.dao.UserDAO;
import com.krlsystem.model.Passenger;
import com.krlsystem.model.Station;
import com.krlsystem.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PassengerBookingController {
    @FXML
    private ComboBox<Station> originCombo;
    @FXML
    private ComboBox<Station> destCombo;
    @FXML
    private Label fareLabel;
    @FXML
    private Label statusLabel;

    private StationDAO stationDAO = new StationDAO();
    private TariffDAO tariffDAO = new TariffDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private UserDAO userDAO = new UserDAO();
    private PassengerController parentController;

    private double currentFare = 0;

    public void setParentController(PassengerController parent) {
        this.parentController = parent;
    }

    @FXML
    public void initialize() {
        originCombo.getItems().addAll(stationDAO.getAllStations());
        destCombo.getItems().addAll(stationDAO.getAllStations());

        originCombo.setOnAction(e -> calculateFare());
        destCombo.setOnAction(e -> calculateFare());
    }

    private void calculateFare() {
        Station start = originCombo.getValue();
        Station end = destCombo.getValue();

        if (start != null && end != null) {
            if (start.getId().equals(end.getId())) {
                fareLabel.setText("Rp 0");
                currentFare = 0;
                statusLabel.setText("Stasiun asal dan tujuan sama.");
                statusLabel.setStyle("-fx-text-fill: orange;");
                return;
            }
            double dist = Math.abs(start.getDistanceFromStart() - end.getDistanceFromStart());
            double base = tariffDAO.getBaseFare(); // Asumsi 3000

            if (dist <= 25) {
                currentFare = base;
            } else {
                double extraDist = dist - 25;
                // Tambah Rp 1.000 setiap kelipatan 10 km berikutnya
                currentFare = base + (Math.ceil(extraDist / 10) * 1000);
            }

            fareLabel.setText(String.format("Rp %.0f", currentFare));
            statusLabel.setText("");
        } else {
            fareLabel.setText("Rp 0");
            currentFare = 0;
        }
    }

    @FXML
    private void handlePay() {
        Passenger p = (Passenger) App.getUser();
        if (currentFare <= 0) {
            statusLabel.setText("Pilih rute yang valid!");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (p.getBalance() >= currentFare) {
            double newBalance = p.getBalance() - currentFare;
            if (userDAO.updateBalance(p.getId(), newBalance)) {
                p.setBalance(newBalance);
                Transaction trx = new Transaction(0, p.getId(), originCombo.getValue(), destCombo.getValue(),
                        currentFare, null);
                transactionDAO.addTransaction(trx);

                showTicketReceipt(originCombo.getValue(), destCombo.getValue(), currentFare);
                parentController.updateBalanceDisplay();
            }
        } else {
            statusLabel.setText("Saldo tidak mencukupi!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void showTicketReceipt(Station origin, Station dest, double amount) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tiket KRL Berhasil");
        alert.setHeaderText("Terima kasih telah melakukan pembelian!");
        alert.setContentText(String.format(
                "DETAIL TIKET:\n" +
                        "---------------------------\n" +
                        "Asal: %s (%s)\n" +
                        "Tujuan: %s (%s)\n" +
                        "Tarif: Rp %.0f\n" +
                        "Status: SETUJU / LUNAS\n" +
                        "---------------------------\n" +
                        "Silakan gunakan tiket ini di gate masuk.",
                origin.getName(), origin.getId(),
                dest.getName(), dest.getId(),
                amount));
        alert.showAndWait();
    }
}
