package com.hostel.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initialize() {

        try {

            Connection conn = DatabaseConnection.connect();

            if (conn == null) {
                System.out.println("Database connection failed");
                return;
            }

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "room INTEGER," +
                "feePaid BOOLEAN)"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS complaints (" +
"id INTEGER AUTO_INCREMENT PRIMARY KEY," +
"studentId INTEGER," +
"issue VARCHAR(255)," +
"priority VARCHAR(50)," +
"status VARCHAR(50))"
            );

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS visitors (" +
                "name TEXT," +
                "studentId INTEGER," +
                "time TEXT)"
            );

            System.out.println("Database setup completed");

            stmt.close();
            conn.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}