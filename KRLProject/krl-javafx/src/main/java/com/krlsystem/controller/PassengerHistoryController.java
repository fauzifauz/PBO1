package com.krlsystem.controller;

import com.krlsystem.App;
import com.krlsystem.dao.StationDAO;
import com.krlsystem.dao.TransactionDAO;
import com.krlsystem.model.Passenger;
import com.krlsystem.model.Transaction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;
import javafx.util.Callback;

public class PassengerHistoryController {
        @FXML
        private TableView<Transaction> historyTable;
        @FXML
        private TableColumn<Transaction, String> colRoute;
        @FXML
        private TableColumn<Transaction, String> colFare;
        @FXML
        private TableColumn<Transaction, String> colTime;
        @FXML
        private TableColumn<Transaction, Void> colAction;

        private TransactionDAO transactionDAO = new TransactionDAO();
        private StationDAO stationDAO = new StationDAO();

        @FXML
        public void initialize() {
                Passenger p = (Passenger) App.getUser();

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

                addButtonToTable();

                historyTable.setItems(FXCollections.observableArrayList(
                                transactionDAO.getHistoryByUserId(p.getId(), stationDAO.getAllStations())));
        }

        private void addButtonToTable() {
                Callback<TableColumn<Transaction, Void>, TableCell<Transaction, Void>> cellFactory = new Callback<>() {
                        @Override
                        public TableCell<Transaction, Void> call(final TableColumn<Transaction, Void> param) {
                                return new TableCell<>() {
                                        private final Button btn = new Button("Detail");

                                        {
                                                btn.getStyleClass().add("btn-primary");
                                                btn.setOnAction(event -> {
                                                        Transaction t = getTableView().getItems().get(getIndex());
                                                        showTicketDetail(t);
                                                });
                                        }

                                        @Override
                                        public void updateItem(Void item, boolean empty) {
                                                super.updateItem(item, empty);
                                                setGraphic(empty ? null : btn);
                                        }
                                };
                        }
                };
                colAction.setCellFactory(cellFactory);
        }

        private void showTicketDetail(Transaction t) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Detail Tiket KRL");
                alert.setHeaderText("Informasi Tiket Terpesan");
                alert.setContentText(String.format(
                                "ID TRANSAKSI: #%d\n" +
                                                "WAKTU: %s\n" +
                                                "---------------------------\n" +
                                                "Asal: %s (%s)\n" +
                                                "Tujuan: %s (%s)\n" +
                                                "Tarif: Rp %.0f\n" +
                                                "Status: LUNAS\n" +
                                                "---------------------------\n" +
                                                "Tiket ini sudah dalam riwayat perjalanan Anda.",
                                t.getId(),
                                t.getTimestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                t.getOrigin().getName(), t.getOrigin().getId(),
                                t.getDestination().getName(), t.getDestination().getId(),
                                t.getAmount()));
                alert.showAndWait();
        }
}
