package com.jobify;

import com.jobify.dao.UsuarioDAO;
import com.jobify.model.Usuario;
import com.jobify.util.Conexion;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario(
                "Juan Pérez",
                "juan.nuevo@example.com", // Cambiamos el correo para evitar duplicados
                "password123",
                "cliente"
            );

            // Insertar usuario
            try {
                usuarioDAO.crear(nuevoUsuario);
                System.out.println("Usuario insertado correctamente");
                
                // Listar todos los usuarios
                System.out.println("\nLista de usuarios:");
                List<Usuario> usuarios = usuarioDAO.obtenerTodos();
                for (Usuario usuario : usuarios) {
                    System.out.println(usuario);
                }

                // Actualizar un usuario
                if (!usuarios.isEmpty()) {
                    Usuario usuarioActualizar = usuarios.get(0);
                    usuarioActualizar.setNombre("Juan Pérez Actualizado");
                    usuarioActualizar.setCorreo("juan.actualizado@example.com");
                    usuarioActualizar.setContrasena("nuevaPassword456");
                    usuarioActualizar.setTipoUsuario("proveedor");
                    
                    try {
                        usuarioDAO.actualizar(usuarioActualizar);
                        System.out.println("\nUsuario actualizado correctamente");
                        
                        // Buscar usuario por ID
                        if (!usuarios.isEmpty()) {
                            Usuario usuarioEncontrado = usuarioDAO.obtenerPorId(usuarios.get(0).getIdUsuario());
                            if (usuarioEncontrado != null) {
                                System.out.println("\nUsuario encontrado por ID:");
                                System.out.println(usuarioEncontrado);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al actualizar usuario: " + e.getMessage());
                    }
                }

            } catch (Exception e) {
                System.err.println("Error al insertar usuario: " + e.getMessage());
            }

            // Eliminar un usuario
            // if (!usuarios.isEmpty()) {
            //     if (usuarioDAO.eliminar(usuarios.get(0).getIdUsuario())) {
            //         System.out.println("\nUsuario eliminado correctamente");
            //     }
            // }

        } catch (Exception e) {
            System.err.println("Error en la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 