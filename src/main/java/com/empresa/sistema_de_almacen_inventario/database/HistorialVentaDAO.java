package com.empresa.sistema_de_almacen_inventario.database;

import com.empresa.sistema_de_almacen_inventario.modelos.HistorialVenta;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HistorialVentaDAO {

    public static boolean guardar(HistorialVenta h) {
        String sql = "INSERT INTO historial_ventas (numero_ticket, fecha_hora, vendedor, canal_venta, " +
                     "descripcion_producto, cantidad, precio_unitario, subtotal, descuentos, impuestos, " +
                     "total_neto, metodo_pago, nombre_cliente, correo_cliente) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, h.getNumeroTicket());
            pst.setTimestamp(2, h.getFechaHora() != null ? new Timestamp(h.getFechaHora().getTime()) : null);
            pst.setString(3, h.getVendedor());
            pst.setString(4, h.getCanalVenta());
            pst.setString(5, h.getDescripcionProducto());
            pst.setInt(6, h.getCantidad());
            pst.setDouble(7, h.getPrecioUnitario());
            pst.setDouble(8, h.getSubtotal());
            pst.setDouble(9, h.getDescuentos());
            pst.setDouble(10, h.getImpuestos());
            pst.setDouble(11, h.getTotalNeto());
            pst.setString(12, h.getMetodoPago());
            pst.setString(13, h.getNombreCliente());
            pst.setString(14, h.getCorreoCliente());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar historial:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean eliminar(int id) {
        String sql = "DELETE FROM historial_ventas WHERE id=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static ArrayList<HistorialVenta> obtenerTodos() {
        ArrayList<HistorialVenta> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_ventas ORDER BY fecha_hora DESC";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                HistorialVenta h = new HistorialVenta();
                h.setId(rs.getInt("id"));
                h.setNumeroTicket(rs.getString("numero_ticket"));
                Timestamp ts = rs.getTimestamp("fecha_hora");
                if (ts != null) h.setFechaHora(new java.util.Date(ts.getTime()));
                h.setVendedor(rs.getString("vendedor"));
                h.setCanalVenta(rs.getString("canal_venta"));
                h.setDescripcionProducto(rs.getString("descripcion_producto"));
                h.setCantidad(rs.getInt("cantidad"));
                h.setPrecioUnitario(rs.getDouble("precio_unitario"));
                h.setSubtotal(rs.getDouble("subtotal"));
                h.setDescuentos(rs.getDouble("descuentos"));
                h.setImpuestos(rs.getDouble("impuestos"));
                h.setTotalNeto(rs.getDouble("total_neto"));
                h.setMetodoPago(rs.getString("metodo_pago"));
                h.setNombreCliente(rs.getString("nombre_cliente"));
                h.setCorreoCliente(rs.getString("correo_cliente"));
                lista.add(h);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar historial:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
}
