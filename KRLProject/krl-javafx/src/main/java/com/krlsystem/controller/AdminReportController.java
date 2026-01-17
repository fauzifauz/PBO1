package com.krlsystem.controller;

import com.krlsystem.dao.StationDAO;
import com.krlsystem.dao.TransactionDAO;
import com.krlsystem.model.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;

public class AdminReportController {
        @FXML
        private TableView<Transaction> reportTable;
        @FXML
        private TableColumn<Transaction, Integer> colId;
        @FXML
        private TableColumn<Transaction, Integer> colUser;
        @FXML
        private TableColumn<Transaction, String> colRoute;
        @FXML
        private TableColumn<Transaction, String> colFare;
        @FXML
        private TableColumn<Transaction, String> colTime;

        private TransactionDAO transactionDAO = new TransactionDAO();
        private StationDAO stationDAO = new StationDAO();

        @FXML
        public void initialize() {
                colId.setCellValueFactory(new PropertyValueFactory<>("id"));
                colUser.setCellValueFactory(new PropertyValueFactory<>("userId"));

                colRoute.setCellValueFactory(cellData -> {
                        Transaction t = cellData.getValue();
                        String originName = (t.getOrigin() != null) ? t.getOrigin().getName() : "Unknown";
                        String destName = (t.getDestination() != null) ? t.getDestination().getName() : "Unknown";
                        return new SimpleStringProperty(originName + " -> " + destName);
                });

                colFare.setCellValueFactory(cellData -> new SimpleStringProperty(
                                String.format("Rp %.0f", cellData.getValue().getAmount())));

                colTime.setCellValueFactory(cellData -> new SimpleStringProperty(
                                cellData.getValue().getTimestamp()
                                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

                reportTable.setItems(
                                FXCollections.observableArrayList(
                                                transactionDAO.getAllTransactions(stationDAO.getAllStations())));
        }
}
