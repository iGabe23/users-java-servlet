package com.jobify.servlet;

import com.jobify.dao.UsuarioDAO;
import com.jobify.model.Usuario;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsuarioListServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodos();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/WEB-INF/views/usuarios.jsp")
                  .forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al obtener usuarios", e);
        }
    }
} 