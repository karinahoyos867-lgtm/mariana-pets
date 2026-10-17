package com.marianapets.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/mariana_pets";

    private static final String USUARIO = "root";

    public static Connection conectar() throws SQLException {
                try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "No se encontró el controlador JDBC de MySQL.", e
            );
        }
        String password = System.getenv("MARIANA_DB_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new SQLException(
                    "Falta configurar la variable MARIANA_DB_PASSWORD."
            );
        }

        return DriverManager.getConnection(URL, USUARIO, password);
    }
}