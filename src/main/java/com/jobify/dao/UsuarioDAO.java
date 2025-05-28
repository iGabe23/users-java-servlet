package com.jobify.dao;

import com.jobify.model.Usuario;
import com.jobify.util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conexion;

    public UsuarioDAO() {
        this.conexion = Conexion.getConnection();
    }

    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                Timestamp created = rs.getTimestamp("created_at");
                Timestamp updated = rs.getTimestamp("updated_at");
                if (created != null) usuario.setCreatedAt(created.toLocalDateTime());
                if (updated != null) usuario.setUpdatedAt(updated.toLocalDateTime());
                usuarios.add(usuario);
            }
        }
        
        return usuarios;
    }

    public Usuario obtenerPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                    Timestamp created = rs.getTimestamp("created_at");
                    Timestamp updated = rs.getTimestamp("updated_at");
                    if (created != null) usuario.setCreatedAt(created.toLocalDateTime());
                    if (updated != null) usuario.setUpdatedAt(updated.toLocalDateTime());
                    return usuario;
                }
            }
        }
        
        return null;
    }

    public void crear(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena, tipo_usuario) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasena());
            stmt.setString(4, usuario.getTipoUsuario());
            
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setIdUsuario(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void actualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, tipo_usuario = ?";
        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
            sql += ", contrasena = ?";
        }
        sql += " WHERE id_usuario = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getTipoUsuario());
            
            int paramIndex = 4;
            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                stmt.setString(paramIndex++, usuario.getContrasena());
            }
            stmt.setInt(paramIndex, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }
} 