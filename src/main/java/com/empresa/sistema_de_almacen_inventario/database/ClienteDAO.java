package com.empresa.sistema_de_almacen_inventario.database;

import com.empresa.sistema_de_almacen_inventario.modelos.Cliente;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    /**
     * Guarda un nuevo cliente en la base de datos
     */
    public static boolean guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (identificador, nombres, dni, ruc, direccion, localidad) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, cliente.getIdentificador());
            pst.setString(2, cliente.getNombres());
            pst.setString(3, cliente.getDni());
            pst.setString(4, cliente.getRuc());
            pst.setString(5, cliente.getDireccion());
            pst.setString(6, cliente.getLocalidad());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al guardar cliente en la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Actualiza un cliente existente en la base de datos
     */
    public static boolean actualizar(Cliente cliente, String identificadorOriginal) {
        String sql = "UPDATE clientes SET identificador=?, nombres=?, dni=?, ruc=?, direccion=?, localidad=? WHERE identificador=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, cliente.getIdentificador());
            pst.setString(2, cliente.getNombres());
            pst.setString(3, cliente.getDni());
            pst.setString(4, cliente.getRuc());
            pst.setString(5, cliente.getDireccion());
            pst.setString(6, cliente.getLocalidad());
            pst.setString(7, identificadorOriginal);
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar cliente en la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Elimina un cliente de la base de datos
     */
    public static boolean eliminar(String identificador) {
        String sql = "DELETE FROM clientes WHERE identificador=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, identificador);
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar cliente de la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Obtiene todos los clientes de la base de datos
     */
    public static ArrayList<Cliente> obtenerTodos() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY fecha_registro DESC";
        
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("identificador"),
                    rs.getString("nombres"),
                    rs.getString("dni"),
                    rs.getString("ruc"),
                    rs.getString("direccion"),
                    rs.getString("localidad")
                );
                listaClientes.add(cliente);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al cargar clientes desde la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return listaClientes;
    }
    
    /**
     * Busca un cliente por su identificador
     */
    public static Cliente buscarPorIdentificador(String identificador) {
        String sql = "SELECT * FROM clientes WHERE identificador=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, identificador);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getString("identificador"),
                    rs.getString("nombres"),
                    rs.getString("dni"),
                    rs.getString("ruc"),
                    rs.getString("direccion"),
                    rs.getString("localidad")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Verifica si existe un cliente con el identificador dado
     */
    public static boolean existe(String identificador) {
        String sql = "SELECT COUNT(*) FROM clientes WHERE identificador=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, identificador);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de cliente: " + e.getMessage());
        }
        
        return false;
    }
}
