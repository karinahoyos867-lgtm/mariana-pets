package com.marianapets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MascotaCRUD {

    public static void crear(String nombre, String especie, String raza,
                             int edad, String propietario) throws SQLException {
        String sql = "INSERT INTO mascotas (nombre, especie, raza, edad, propietario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setString(1, nombre);
            consulta.setString(2, especie);
            consulta.setString(3, raza);
            consulta.setInt(4, edad);
            consulta.setString(5, propietario);
            consulta.executeUpdate();
            System.out.println("Mascota creada correctamente.");
        }
    }

    public static void consultar() throws SQLException {
        String sql = "SELECT * FROM mascotas";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql);
             ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                System.out.println(
                    "ID: " + resultados.getInt("id")
                    + " | Nombre: " + resultados.getString("nombre")
                    + " | Especie: " + resultados.getString("especie")
                    + " | Raza: " + resultados.getString("raza")
                    + " | Edad: " + resultados.getInt("edad")
                    + " | Propietario: " + resultados.getString("propietario")
                );
            }
        }
    }

    public static void actualizar(int id, String nombre, String especie,
                                  String raza, int edad, String propietario)
                                  throws SQLException {
        String sql = "UPDATE mascotas SET nombre = ?, especie = ?, raza = ?, edad = ?, propietario = ? WHERE id = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setString(1, nombre);
            consulta.setString(2, especie);
            consulta.setString(3, raza);
            consulta.setInt(4, edad);
            consulta.setString(5, propietario);
            consulta.setInt(6, id);

            int filas = consulta.executeUpdate();
            System.out.println(filas > 0
                ? "Mascota actualizada correctamente."
                : "No se encontró la mascota.");
        }
    }

    public static void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM mascotas WHERE id = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setInt(1, id);

            int filas = consulta.executeUpdate();
            System.out.println(filas > 0
                ? "Mascota eliminada correctamente."
                : "No se encontró la mascota.");
        }
    }

    public static void main(String[] args) {
        try {
            crear("Luna", "Perro", "Criolla", 3, "Propietario de prueba");
            consultar();
        } catch (SQLException e) {
            System.out.println("Error en el CRUD: " + e.getMessage());
        }
    }
}
