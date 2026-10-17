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
import java.sql.SQLException;

@WebServlet("/registrar-mascota")
public class RegistrarMascotaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/registro-mascota.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String especie = request.getParameter("especie");
        String raza = request.getParameter("raza");
        String edadTexto = request.getParameter("edad");
        String propietario = request.getParameter("propietario");

        if (nombre == null || nombre.isBlank()
                || especie == null || especie.isBlank()
                || raza == null || raza.isBlank()
                || edadTexto == null || edadTexto.isBlank()
                || propietario == null || propietario.isBlank()) {

            request.setAttribute("mensaje",
                    "Todos los campos son obligatorios.");
            request.getRequestDispatcher("/resultado.jsp")
                   .forward(request, response);
            return;
        }

        try {
            int edad = Integer.parseInt(edadTexto);

            if (edad < 0) {
                throw new NumberFormatException();
            }

            String sql = "INSERT INTO mascotas "
                    + "(nombre, especie, raza, edad, propietario) "
                    + "VALUES (?, ?, ?, ?, ?)";

            try (Connection conexion = Conexion.conectar();
                 PreparedStatement consulta =
                         conexion.prepareStatement(sql)) {

                consulta.setString(1, nombre);
                consulta.setString(2, especie);
                consulta.setString(3, raza);
                consulta.setInt(4, edad);
                consulta.setString(5, propietario);

                consulta.executeUpdate();
            }

            request.setAttribute("mensaje",
                    "La mascota se registró correctamente.");

        } catch (NumberFormatException e) {

            request.setAttribute("mensaje",
                    "La edad debe ser un número entero igual o mayor que cero.");

        } catch (SQLException e) {

            getServletContext().log(
                    "Error al registrar la mascota.", e);

            request.setAttribute("mensaje",
                    "No fue posible registrar la mascota. Verifica la conexión a la base de datos.");
        }

        request.getRequestDispatcher("/resultado.jsp")
               .forward(request, response);
    }
}