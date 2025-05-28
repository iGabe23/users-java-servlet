package com.jobify.servlet;

import com.jobify.dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuarioDeleteServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            String[] pathParts = pathInfo.split("/");
            int idUsuario = Integer.parseInt(pathParts[1]);

            usuarioDAO.eliminar(idUsuario);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (Exception e) {
            throw new ServletException("Error al eliminar usuario", e);
        }
    }
} 