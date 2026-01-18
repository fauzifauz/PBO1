package com.krlsystem.controller;

import com.krlsystem.dao.StationDAO;
import com.krlsystem.model.Station;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.layout.HBox;

public class AdminStationController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField distanceField;
    @FXML
    private TableView<Station> stationTable;
    @FXML
    private TableColumn<Station, String> colId;
    @FXML
    private TableColumn<Station, String> colName;
    @FXML
    private TableColumn<Station, Double> colDistance;
    @FXML
    private TableColumn<Station, Void> colAction;
    @FXML
    private Label statusLabel;

    private StationDAO stationDAO = new StationDAO();
    private boolean isEditing = false;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distanceFromStart"));

        addButtonToTable();
        refreshTable();
    }

    private void refreshTable() {
        stationTable.setItems(FXCollections.observableArrayList(stationDAO.getAllStations()));
        idField.setEditable(true);
        idField.clear();
        nameField.clear();
        distanceField.clear();
        isEditing = false;
    }

    @FXML
    private void handleAdd() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            double distance = Double.parseDouble(distanceField.getText());

            if (id.isEmpty() || name.isEmpty())
                return;

            Station s = new Station(id, name, distance);
            boolean success;
            if (isEditing) {
                success = stationDAO.updateStation(s);
            } else {
                success = stationDAO.addStation(s);
            }

            if (success) {
                refreshTable();
                statusLabel.setText(isEditing ? "Stasiun diperbarui!" : "Stasiun berhasil ditambah!");
                statusLabel.setStyle("-fx-text-fill: green;");
            } else {
                statusLabel.setText("Gagal! ID sudah ada?");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Jarak harus angka!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void addButtonToTable() {
        Callback<TableColumn<Station, Void>, TableCell<Station, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Station, Void> call(final TableColumn<Station, Void> param) {
                return new TableCell<>() {
                    private final Button btnEdit = new Button("Edit");
                    private final Button btnDelete = new Button("Hapus");
                    private final HBox pane = new HBox(5, btnEdit, btnDelete);

                    {
                        btnEdit.getStyleClass().add("btn-primary");
                        btnEdit.setOnAction(event -> {
                            Station s = getTableView().getItems().get(getIndex());
                            idField.setText(s.getId());
                            idField.setEditable(false);
                            nameField.setText(s.getName());
                            distanceField.setText(String.valueOf(s.getDistanceFromStart()));
                            isEditing = true;
                        });

                        btnDelete.getStyleClass().add("btn-secondary");
                        btnDelete.setOnAction(event -> {
                            Station s = getTableView().getItems().get(getIndex());
                            if (stationDAO.deleteStation(s.getId())) {
                                refreshTable();
                                statusLabel.setText("Stasiun berhasil dihapus.");
                                statusLabel.setStyle("-fx-text-fill: green;");
                            } else {
                                statusLabel.setText("Gagal hapus! Stasiun mungkin sedang digunakan.");
                                statusLabel.setStyle("-fx-text-fill: red;");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : pane);
                    }
                };
            }
        };
        colAction.setCellFactory(cellFactory);
    }
}
