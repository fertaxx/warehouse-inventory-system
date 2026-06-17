package com.empresa.sistema_de_almacen_inventario.database;

import com.empresa.sistema_de_almacen_inventario.modelos.Venta;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VentaDAO {

    public static boolean guardar(Venta venta) {
        String sql = "INSERT INTO ventas (cliente_identificador, cliente_nombre, producto_no_serie, " +
                     "producto_nombre, cantidad, precio_unitario, subtotal, igv, total, fecha_venta) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, venta.getClienteIdentificador());
            pst.setString(2, venta.getClienteNombre());
            pst.setString(3, venta.getProductoNoSerie());
            pst.setString(4, venta.getProductoNombre());
            pst.setInt(5, venta.getCantidad());
            pst.setDouble(6, venta.getPrecioUnitario());
            pst.setDouble(7, venta.getSubtotal());
            pst.setDouble(8, venta.getIgv());
            pst.setDouble(9, venta.getTotal());
            pst.setDate(10, new java.sql.Date(venta.getFechaVenta().getTime()));

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al guardar venta:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean eliminar(int id) {
        String sql = "DELETE FROM ventas WHERE id=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al eliminar venta:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static ArrayList<Venta> obtenerTodos() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas ORDER BY fecha_venta DESC";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setClienteIdentificador(rs.getString("cliente_identificador"));
                v.setClienteNombre(rs.getString("cliente_nombre"));
                v.setProductoNoSerie(rs.getString("producto_no_serie"));
                v.setProductoNombre(rs.getString("producto_nombre"));
                v.setCantidad(rs.getInt("cantidad"));
                v.setPrecioUnitario(rs.getDouble("precio_unitario"));
                v.setSubtotal(rs.getDouble("subtotal"));
                v.setIgv(rs.getDouble("igv"));
                v.setTotal(rs.getDouble("total"));
                v.setFechaVenta(rs.getDate("fecha_venta"));
                lista.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error al cargar ventas:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}
