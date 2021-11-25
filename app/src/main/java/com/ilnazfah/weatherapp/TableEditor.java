package com.ilnazfah.weatherapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Statement statement;
    private final String tableName;

    public TableEditor(String tableName) {
        initConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.tableName = tableName;
    }

    private void initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:cities.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String value) throws SQLException {
        String sql = String.format(
                "insert into %s (name) values ('%s');",
                tableName,
                value
        );
        statement.executeUpdate(sql);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
