package com.hostel.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:h2:./hostelDB";

    public static Connection connect() {

        try {

            Connection conn = DriverManager.getConnection(URL, "sa", "");

            System.out.println("Database connected successfully");

            return conn;

        } catch (Exception e) {

            System.out.println("Database connection failed");
            e.printStackTrace();
            return null;

        }
    }
}