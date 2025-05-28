package com.jobify.util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection connection = null;
    private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_HOST = dotenv.get("DB_HOST", "localhost");
    private static final String DB_PORT = dotenv.get("DB_PORT", "3306");
    private static final String DB_NAME = dotenv.get("DB_NAME", "jobify_db");
    private static final String DB_USER = dotenv.get("DB_USER", "root");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD", "");

    private static final String URL = String.format(
        "jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
        DB_HOST, DB_PORT, DB_NAME
    );

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException("Error al conectar con la base de datos", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión", e);
            }
        }
    }
} 