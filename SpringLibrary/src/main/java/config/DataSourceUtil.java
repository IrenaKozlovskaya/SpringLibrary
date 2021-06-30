package config;

import java.sql.*;


public class DataSourceUtil {

    private Connection connection;

    public Connection getConnection() {

        if (connection == null) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "69lazapo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
