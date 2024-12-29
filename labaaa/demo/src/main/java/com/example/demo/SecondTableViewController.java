package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecondTableViewController {

    @FXML
    private TableView<Lakorn> secondTableView;

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
        ObservableList<Lakorn> lakornList = FXCollections.observableArrayList();
        DBAdapter adapter = new DBAdapter();
        adapter.create_or_connection();

        try (ResultSet rs = adapter.selectDataFromSecondTable()) {
            while (rs.next()) {
                lakornList.add(new Lakorn(
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

        secondTableView.setItems(lakornList);
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
        Lakorn selectedLakorn = secondTableView.getSelectionModel().getSelectedItem();

        if (selectedLakorn != null) {
            try {
                DBAdapter adapter = new DBAdapter();
                adapter.create_or_connection();
                adapter.delete_data(selectedLakorn.getId(), "lakorn2"); // Удаляем из базы данных

                secondTableView.getItems().remove(selectedLakorn); // Удаляем из таблицы
            } catch (SQLException e) {
                showAlert("Error", "Failed to delete: " + e.getMessage());
            }
        } else {
            showAlert("Error", "No item selected for deletion!");
        }
    }

}
