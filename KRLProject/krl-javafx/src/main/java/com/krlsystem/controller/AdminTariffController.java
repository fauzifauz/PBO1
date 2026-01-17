package com.krlsystem.controller;

import com.krlsystem.dao.TariffDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminTariffController {
    @FXML
    private TextField baseFareField;
    @FXML
    private TextField perKmField;
    @FXML
    private Label statusLabel;

    private TariffDAO tariffDAO = new TariffDAO();

    @FXML
    public void initialize() {
        baseFareField.setText(String.valueOf(tariffDAO.getBaseFare()));
        perKmField.setText(String.valueOf(tariffDAO.getFarePerKm()));
    }

    @FXML
    private void handleSave() {
        try {
            double baseFare = Double.parseDouble(baseFareField.getText());
            double perKm = Double.parseDouble(perKmField.getText());

            if (tariffDAO.updateTariffs(baseFare, perKm)) {
                statusLabel.setText("Tarif berhasil diperbarui!");
                statusLabel.setStyle("-fx-text-fill: green;");
            } else {
                statusLabel.setText("Gagal memperbarui tarif.");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Input harus angka!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
