package com.jobify.servlet;

import com.jobify.dao.UsuarioDAO;
import com.jobify.model.Usuario;
import com.jobify.util.PasswordUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuarioCreateServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/usuario-form.jsp")
              .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            String tipoUsuario = request.getParameter("tipoUsuario");

            if (contrasena == null || contrasena.trim().isEmpty()) {
                throw new ServletException("La contraseña es requerida");
            }

            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setContrasena(PasswordUtil.encriptar(contrasena));
            usuario.setTipoUsuario(tipoUsuario);

            usuarioDAO.crear(usuario);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (Exception e) {
            throw new ServletException("Error al crear usuario", e);
        }
    }
} 