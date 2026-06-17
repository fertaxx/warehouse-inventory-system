package com.empresa.sistema_de_almacen_inventario.database;

import com.empresa.sistema_de_almacen_inventario.modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ProductoDAO {
    
    /**
     * Guarda un nuevo producto en la base de datos
     */
    public static boolean guardar(Producto producto) {
        String sql = "INSERT INTO productos (no_serie, nombre, marca, descripcion, precio, cantidad, fecha_compra, fecha_caducidad, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, producto.getNoSerie());
            pst.setString(2, producto.getNombre());
            pst.setString(3, producto.getMarca());
            pst.setString(4, producto.getDescripcion());
            pst.setDouble(5, producto.getPrecio());
            pst.setInt(6, producto.getCantidad());
            
            // Convertir java.util.Date a java.sql.Date
            if (producto.getFechaCompra() != null) {
                pst.setDate(7, new java.sql.Date(producto.getFechaCompra().getTime()));
            } else {
                pst.setDate(7, null);
            }
            
            if (producto.getFechaCaducidad() != null) {
                pst.setDate(8, new java.sql.Date(producto.getFechaCaducidad().getTime()));
            } else {
                pst.setDate(8, null);
            }
            
            pst.setInt(9, producto.getStock());
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al guardar producto en la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Actualiza un producto existente en la base de datos
     */
    public static boolean actualizar(Producto producto, String noSerieOriginal) {
        String sql = "UPDATE productos SET no_serie=?, nombre=?, marca=?, descripcion=?, precio=?, cantidad=?, fecha_compra=?, fecha_caducidad=?, stock=? WHERE no_serie=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, producto.getNoSerie());
            pst.setString(2, producto.getNombre());
            pst.setString(3, producto.getMarca());
            pst.setString(4, producto.getDescripcion());
            pst.setDouble(5, producto.getPrecio());
            pst.setInt(6, producto.getCantidad());
            
            if (producto.getFechaCompra() != null) {
                pst.setDate(7, new java.sql.Date(producto.getFechaCompra().getTime()));
            } else {
                pst.setDate(7, null);
            }
            
            if (producto.getFechaCaducidad() != null) {
                pst.setDate(8, new java.sql.Date(producto.getFechaCaducidad().getTime()));
            } else {
                pst.setDate(8, null);
            }
            
            pst.setInt(9, producto.getStock());
            pst.setString(10, noSerieOriginal);
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar producto en la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Elimina un producto de la base de datos
     */
    public static boolean eliminar(String noSerie) {
        String sql = "DELETE FROM productos WHERE no_serie=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, noSerie);
            
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar producto de la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Obtiene todos los productos de la base de datos
     */
    public static ArrayList<Producto> obtenerTodos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY fecha_registro DESC";
        
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getString("no_serie"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad"),
                    rs.getDate("fecha_compra"),
                    rs.getDate("fecha_caducidad"),
                    rs.getInt("stock")
                );
                listaProductos.add(producto);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al cargar productos desde la base de datos:\n" + e.getMessage(), 
                "Error de Base de Datos", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        return listaProductos;
    }
    
    /**
     * Busca un producto por su número de serie
     */
    public static Producto buscarPorNoSerie(String noSerie) {
        String sql = "SELECT * FROM productos WHERE no_serie=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, noSerie);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return new Producto(
                    rs.getString("no_serie"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad"),
                    rs.getDate("fecha_compra"),
                    rs.getDate("fecha_caducidad"),
                    rs.getInt("stock")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Verifica si existe un producto con el número de serie dado
     */
    public static boolean existe(String noSerie) {
        String sql = "SELECT COUNT(*) FROM productos WHERE no_serie=?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, noSerie);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de producto: " + e.getMessage());
        }
        
        return false;
    }
}
