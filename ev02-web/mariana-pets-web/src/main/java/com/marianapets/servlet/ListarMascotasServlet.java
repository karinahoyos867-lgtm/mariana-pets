
package com.marianapets.servlet;

import com.marianapets.util.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listar-mascotas")
public class ListarMascotasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        List<Map<String, Object>> mascotas = new ArrayList<>();

        String sql = "SELECT id, nombre, especie, raza, edad, propietario "
                   + "FROM mascotas ORDER BY id";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql);
             ResultSet resultados = consulta.executeQuery()) {

            while (resultados.next()) {
                Map<String, Object> mascota = new LinkedHashMap<>();

                mascota.put("id", resultados.getInt("id"));
                mascota.put("nombre", resultados.getString("nombre"));
                mascota.put("especie", resultados.getString("especie"));
                mascota.put("raza", resultados.getString("raza"));
                mascota.put("edad", resultados.getInt("edad"));
                mascota.put("propietario",
                        resultados.getString("propietario"));

                mascotas.add(mascota);
            }

            request.setAttribute("mascotas", mascotas);
            request.getRequestDispatcher("/mascotas.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            getServletContext().log(
                    "Error al consultar las mascotas.", e);

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "No fue posible consultar las mascotas.");
        }
    }
}
