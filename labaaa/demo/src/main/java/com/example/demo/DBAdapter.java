package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBAdapter {
    Connection con;
    ResultSet  SelectData() throws SQLException {String sql = "SELECT * FROM lakorn1";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    // Метод для создания или подключения к базе данных
    void create_or_connection() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:lakorn.SQLite"); // Подключаемся к lakorn1.db

            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS lakorn1 (\n" +
                    "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\tname TEXT NOT NULL,\n" +
                    "\tyear INTEGER NOT NULL,\n" +
                    "\tgenre TEXT NOT NULL,\n" +
                    "\trating TEXT NOT NULL\n" +
                    ");";
            stmt.execute(sql);
            stmt.close();
            System.out.println("Table 'lakorn1' created or already exists.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Метод для вставки данных в таблицу
    void insert_data(String name, int year, String genre, String rating) throws SQLException {
        String sql = "INSERT INTO lakorn1 (name, year, genre, rating) VALUES (?, ?, ?, ?)"; // Используем параметризированный запрос
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, year);
        pstmt.setString(3, genre);
        pstmt.setString(4, rating);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Inserted data into 'lakorn1'.");
    }

    // Метод для удаления данных из таблицы по ID
    void delete_data(Integer id, String tableName) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Deleted data with ID: " + id + " from table: " + tableName);
    }


    // Копирование данных в lakorn2
    void copy_to_lakorn2(int id) throws SQLException {
        String copySql = "INSERT INTO lakorn2 (name, year, genre, rating) " +
                "SELECT name, year, genre, rating FROM lakorn1 WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(copySql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("Copied data with ID: " + id + " to lakorn2.");
    }

    public ResultSet selectDataFromSecondTable() throws SQLException {
        String sql = "SELECT * FROM lakorn2";
        Statement stmt = con.createStatement();
        return stmt.executeQuery(sql);
    }
}
