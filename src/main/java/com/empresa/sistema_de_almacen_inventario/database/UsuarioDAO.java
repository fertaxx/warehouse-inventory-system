package com.empresa.sistema_de_almacen_inventario.database;

import com.empresa.sistema_de_almacen_inventario.modelos.Usuario;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    /**
     * Valida las credenciales de un usuario
     * @param email Email del usuario
     * @param contrasena Contraseña del usuario
     * @return true si las credenciales son válidas, false en caso contrario
     */
    public static boolean validarCredenciales(String email, String contrasena) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email=? AND contrasena=? AND activo=1";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            pst.setString(2, contrasena);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al conectar con la base de datos:\n" + e.getMessage(), 
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return false;
    }
    
    /**
     * Obtiene el tipo de usuario
     * @param email Email del usuario
     * @return Tipo de usuario (Administrador o Usuario)
     */
    public static String obtenerTipoUsuario(String email) {
        String sql = "SELECT tipo_usuario FROM usuarios WHERE email=? AND activo=1";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getString("tipo_usuario");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener tipo de usuario: " + e.getMessage());
        }
        
        return "Usuario";
    }
    
    /**
     * Verifica si un usuario existe
     * @param email Email del usuario
     * @return true si existe, false en caso contrario
     */
    public static boolean existe(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de usuario: " + e.getMessage());
        }
        
        return false;
    }
    
    public static boolean registrar(Usuario u) {
        String sql = "INSERT INTO usuarios (email, nombre, contrasena, tipo_usuario, activo) VALUES (?,?,?,?,1)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, u.getIdentificacion());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getTipoUsuario());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean actualizar(Usuario u, String idOriginal) {
        String sql = "UPDATE usuarios SET email=?, nombre=?, contrasena=?, tipo_usuario=? WHERE email=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, u.getIdentificacion());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getTipoUsuario());
            pst.setString(5, idOriginal);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean eliminar(String identificacion) {
        String sql = "DELETE FROM usuarios WHERE email=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, identificacion);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, email, nombre, contrasena, tipo_usuario, activo FROM usuarios ORDER BY id";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setIdentificacion(rs.getString("email"));
                String nombre = rs.getString("nombre");
                u.setNombre(nombre != null && !nombre.isEmpty() ? nombre : rs.getString("email"));
                u.setPassword(rs.getString("contrasena"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                u.setActivo(rs.getInt("activo"));
                lista.add(u);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuarios:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}
