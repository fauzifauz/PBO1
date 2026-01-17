package com.krlsystem.controller;

import com.krlsystem.dao.UserDAO;
import com.krlsystem.model.Passenger;
import com.krlsystem.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.layout.HBox;

public class AdminUserController {
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colRole;
    @FXML
    private TableColumn<User, String> colBalance;
    @FXML
    private TableColumn<User, Void> colAction;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private ComboBox<String> roleCombo;
    @FXML
    private TextField balanceField;
    @FXML
    private Label statusLabel;

    private UserDAO userDAO = new UserDAO();
    private User selectedUser = null;

    @FXML
    public void initialize() {
        roleCombo.setItems(FXCollections.observableArrayList("ADMIN", "USER"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        colBalance.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Passenger) {
                return new SimpleStringProperty(
                        String.format("Rp %.0f", ((Passenger) cellData.getValue()).getBalance()));
            }
            return new SimpleStringProperty("-");
        });

        addButtonToTable();
        refreshTable();
    }

    private void refreshTable() {
        userTable.setItems(FXCollections.observableArrayList(userDAO.getAllUsers()));
        handleCancel();
    }

    @FXML
    private void handleSave() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleCombo.getValue();
            double balance = balanceField.getText().isEmpty() ? 0 : Double.parseDouble(balanceField.getText());

            if (username.isEmpty() || password.isEmpty() || role == null) {
                statusLabel.setText("Semua field harus diisi!");
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            boolean success;
            if (selectedUser == null) {
                success = userDAO.addUser(username, password, role, balance);
            } else {
                success = userDAO.updateUser(selectedUser.getId(), username, password, role, balance);
            }

            if (success) {
                statusLabel.setText("User berhasil disimpan!");
                statusLabel.setStyle("-fx-text-fill: green;");
                refreshTable();
            } else {
                statusLabel.setText("Gagal menyimpan user.");
                statusLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Saldo harus angka!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleCancel() {
        selectedUser = null;
        usernameField.clear();
        passwordField.clear();
        roleCombo.setValue(null);
        balanceField.clear();
    }

    private void addButtonToTable() {
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                return new TableCell<>() {
                    private final Button btnEdit = new Button("Edit");
                    private final Button btnDelete = new Button("Hapus");
                    private final HBox pane = new HBox(5, btnEdit, btnDelete);

                    {
                        btnEdit.getStyleClass().add("btn-primary");
                        btnEdit.setOnAction(event -> {
                            selectedUser = getTableView().getItems().get(getIndex());
                            usernameField.setText(selectedUser.getUsername());
                            passwordField.setText(selectedUser.getPassword());
                            roleCombo.setValue(selectedUser.getRole());
                            if (selectedUser instanceof Passenger) {
                                balanceField.setText(String.valueOf(((Passenger) selectedUser).getBalance()));
                            } else {
                                balanceField.clear();
                            }
                        });

                        btnDelete.getStyleClass().add("btn-secondary");
                        btnDelete.setOnAction(event -> {
                            User u = getTableView().getItems().get(getIndex());
                            if (userDAO.deleteUser(u.getId()))
                                refreshTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            User u = getTableView().getItems().get(getIndex());
                            btnDelete.setVisible(!"ADMIN".equals(u.getRole()));
                            setGraphic(pane);
                        }
                    }
                };
            }
        };
        colAction.setCellFactory(cellFactory);
    }
}
