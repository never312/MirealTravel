package com.example.mirealtravel;

import java.sql.*;

public class DatabaseConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.0.21:3306/mirea_travel_db";
    static final String USER = "root";
    static final String PASS = "root";

    private static Connection conn = null;

    private static String currentUser = null;

    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean authenticateUser(String username, String password) {
        boolean isValidUser = false;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs.next()) {
                isValidUser = true;
                currentUser = username;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isValidUser;


    }

    public static String getCurrentUser(){
        return currentUser;
    }

    //Method to register user
    public static boolean registerUser(String username, String email, String password) {
        boolean isUserRegistered = false;

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isUserRegistered = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isUserRegistered;
    }

    public static String getEmail(String currentUser) {
        String email = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, DatabaseConnection.getCurrentUser());

            rs = stmt.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return email;
    }

    private void searchTours(String departureCity, String arrivalCountry, String departureDate, int numDays, int numAdults, int numChildren) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();

            // Подготовка SQL-запроса
            String query = "SELECT * FROM Departures " +
                    "INNER JOIN Cities ON Departures.city_id = Cities.id " +
                    "INNER JOIN Countries ON Departures.country_id = Countries.id " +
                    "WHERE Cities.name = ? " +
                    "AND Countries.name = ? " +
                    "AND Departures.departure_date = ? " +
                    "AND Departures.duration = ? " +
                    "AND Departures.num_adults = ? " +
                    "AND Departures.num_children = ?";
            stmt = conn.prepareStatement(query);

            stmt.setString(1, departureCity);
            stmt.setString(2, arrivalCountry);
            stmt.setString(3, departureDate);
            stmt.setInt(4, numDays);
            stmt.setInt(5, numAdults);
            stmt.setInt(6, numChildren);
            
            rs = stmt.executeQuery();

            // Обработка результатов
            while (rs.next()) {
                // Получение данных из результатов запроса и создание объектов Tour
                int tourId = rs.getInt("Departures.tour_id");
                // Дополните код для получения остальных данных тура из результатов запроса
                // и создания объектов Tour
            }

            // Здесь вы можете использовать полученные результаты для отображения данных в вашем интерфейсе

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
