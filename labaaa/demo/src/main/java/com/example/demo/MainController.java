package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField ratingField;

    @FXML
    private Button addButton;

    @FXML
    private Button showTableButton;

    @FXML
    private void addLakorn(ActionEvent event) {
        String name = nameField.getText();
        String yearText = yearField.getText();
        String genre = genreField.getText();
        String rating = ratingField.getText();

        if (name.isEmpty() || yearText.isEmpty() || genre.isEmpty() || rating.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        try {
            int year = Integer.parseInt(yearText);  // Преобразуем год в целое число
            DBAdapter adapter = new DBAdapter();
            adapter.create_or_connection();
            adapter.insert_data(name, year, genre, rating );

        } catch (NumberFormatException e) {
            showAlert("Error", "Age must be a valid number!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void showTable(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TableView.fxml"));
        Scene scene = new Scene(loader.load(), 350, 400);
        Stage stage = new Stage();

        stage.setTitle("Результат");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        try {
            /*Parent tableRoot = FXMLLoader.load(getClass().getResource("TableView.fxml"));
            Scene tableScene = new Scene(tableRoot);

            Stage tableStage = new Stage();
            tableStage.setScene(tableScene);
            tableStage.setTitle("Animal List");
            tableStage.show();*/
            /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TableView.fxml"));
            Scene scene = new Scene(loader.load(), 230, 230);
            Stage stage = new Stage();

            stage.setTitle("Результат");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();*/
        } catch (Exception e) {
            showAlert("Error", "Failed to open table view: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.clear();
        yearField.clear();
        genreField.clear();
        ratingField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}