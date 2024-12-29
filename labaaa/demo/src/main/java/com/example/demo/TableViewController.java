package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;

public class TableViewController {

    @FXML
    private TableView<Lakorn> tableView;

    @FXML
    private TableColumn<Lakorn, String> nameColumn;

    @FXML
    private TableColumn<Lakorn, Integer> yearColumn;

    @FXML
    private TableColumn<Lakorn, String> genreColumn;

    @FXML
    private TableColumn<Lakorn, String> ratingColumn;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        loadData();
    }

    private void loadData() {
        ObservableList<Lakorn> lakorn = FXCollections.observableArrayList();
        DBAdapter adapter = new DBAdapter();
        adapter.create_or_connection();

        try (ResultSet rs = adapter.SelectData()) {


            while (rs.next()) {
                lakorn.add(new Lakorn(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("genre"),
                        rs.getString("rating")
                ));
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to load data: " + e.getMessage());
        }

        tableView.setItems(lakorn);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void deleteSelectedRow(ActionEvent event) {
        Lakorn selectedLakorn = tableView.getSelectionModel().getSelectedItem();

        if (selectedLakorn != null) {
            try {
                DBAdapter adapter = new DBAdapter();
                adapter.create_or_connection();
                adapter.delete_data(selectedLakorn.getId(), "lakorn1"); // Удаляем из базы данных

                tableView.getItems().remove(selectedLakorn); // Удаляем из таблицы
            } catch (SQLException e) {
                showAlert("Error", "Failed to delete: " + e.getMessage());
            }
        } else {
            showAlert("Error", "No item selected for deletion!");
        }
    }

    @FXML
    private void copyToSecondTable(ActionEvent event) {
        Lakorn selectedLakorn = tableView.getSelectionModel().getSelectedItem();

        if (selectedLakorn != null) {
            try {
                DBAdapter adapter = new DBAdapter();
                adapter.create_or_connection();
                adapter.copy_to_lakorn2(selectedLakorn.getId()); // Копируем в lakorn2
                showAlert("Success", "Data copied to Table 2!");
            } catch (SQLException e) {
                showAlert("Error", "Failed to copy: " + e.getMessage());
            }
        } else {
            showAlert("Error", "No item selected for copying!");
        }
    }

    @FXML
    private void showSecondTable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SecondTableView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            stage.setTitle("Second Table");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            showAlert("Error", "Failed to open second table view: " + e.getMessage());
        }
    }
}
