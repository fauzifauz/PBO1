package com.krlsystem.controller;

import com.krlsystem.dao.StationDAO;
import com.krlsystem.dao.TransactionDAO;
import com.krlsystem.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminDashboardController {
    @FXML
    private Label revenueLabel;
    @FXML
    private Label userCountLabel;
    @FXML
    private Label stationCountLabel;

    private TransactionDAO transactionDAO = new TransactionDAO();
    private UserDAO userDAO = new UserDAO();
    private StationDAO stationDAO = new StationDAO();

    @FXML
    public void initialize() {
        double revenue = transactionDAO.getTotalRevenue();
        revenueLabel.setText(String.format("Rp %.0f", revenue));
        userCountLabel.setText(String.valueOf(userDAO.getAllUsers().size()));
        stationCountLabel.setText(String.valueOf(stationDAO.getAllStations().size()));
    }
}
