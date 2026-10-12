
package com.marianapets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/mariana_pets";
    private static final String USUARIO = "root";
   private static final String CONTRASENA = System.getenv("MARIANA_DB_PASSWORD");

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public static void main(String[] args) {
        try (Connection conexion = conectar()) {
            System.out.println("Conexion a Mariana Pets exitosa.");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
