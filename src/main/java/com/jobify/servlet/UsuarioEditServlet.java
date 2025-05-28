package com.jobify.servlet;

import com.jobify.dao.UsuarioDAO;
import com.jobify.model.Usuario;
import com.jobify.util.PasswordUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsuarioEditServlet extends HttpServlet {
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
            if (pathInfo == null || pathInfo.equals("/")) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
                return;
            }
            
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length < 2) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
                return;
            }
            
            int idUsuario = Integer.parseInt(pathParts[1]);
            Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);
            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
                return;
            }
            
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/WEB-INF/views/usuario-form.jsp")
                  .forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (Exception e) {
            throw new ServletException("Error al obtener usuario", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
                return;
            }
            
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length < 2) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
                return;
            }
            
            int idUsuario = Integer.parseInt(pathParts[1]);
            Usuario usuarioExistente = usuarioDAO.obtenerPorId(idUsuario);
            if (usuarioExistente == null) {
                response.sendRedirect(request.getContextPath() + "/usuarios");
                return;
            }

            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            String tipoUsuario = request.getParameter("tipoUsuario");

            usuarioExistente.setNombre(nombre);
            usuarioExistente.setCorreo(correo);
            usuarioExistente.setTipoUsuario(tipoUsuario);
            
            // Solo actualizar la contraseña si se proporciona una nueva
            if (contrasena != null && !contrasena.trim().isEmpty()) {
                usuarioExistente.setContrasena(PasswordUtil.encriptar(contrasena));
            }

            usuarioDAO.actualizar(usuarioExistente);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (Exception e) {
            throw new ServletException("Error al actualizar usuario", e);
        }
    }
} 