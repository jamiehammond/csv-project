package com.sparta.jh.CSV2SQL.controller;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnectionController {

    private static Connection connection;
    private static Properties properties = new Properties();

    private static void createProperties() {
        try {
            properties.load(new FileReader("src/main/resources/login.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectToDB() {
        createProperties();
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void clearDatabase() {
        connectToDB();
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE `employees`.`employees`");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() {
        connectToDB();
        return connection;
    }
}
