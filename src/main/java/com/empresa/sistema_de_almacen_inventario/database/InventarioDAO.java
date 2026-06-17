package com.empresa.sistema_de_almacen_inventario.database;

import com.empresa.sistema_de_almacen_inventario.modelos.Inventario;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InventarioDAO {

    public static boolean guardar(Inventario inv) {
        String sql = "INSERT INTO inventario (codigo_sku, nombre_producto, categoria, proveedor, " +
                     "stock_inicial, entradas, salidas, devoluciones, stock_final, " +
                     "costo_unitario, precio_venta, valor_inventario, punto_reorden, estado) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            setParams(pst, inv);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar inventario:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean actualizar(Inventario inv, String codigoOriginal) {
        String sql = "UPDATE inventario SET codigo_sku=?, nombre_producto=?, categoria=?, proveedor=?, " +
                     "stock_inicial=?, entradas=?, salidas=?, devoluciones=?, stock_final=?, " +
                     "costo_unitario=?, precio_venta=?, valor_inventario=?, punto_reorden=?, estado=? " +
                     "WHERE codigo_sku=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            setParams(pst, inv);
            pst.setString(15, codigoOriginal);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar inventario:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean eliminar(String codigoSku) {
        String sql = "DELETE FROM inventario WHERE codigo_sku=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, codigoSku);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static ArrayList<Inventario> obtenerTodos() {
        ArrayList<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario ORDER BY fecha_registro DESC";
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar inventario:\n" + e.getMessage(),
                "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public static Inventario buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM inventario WHERE codigo_sku=?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) {
            System.err.println("Error al buscar inventario: " + e.getMessage());
        }
        return null;
    }

    private static void setParams(PreparedStatement pst, Inventario inv) throws SQLException {
        inv.recalcular();
        pst.setString(1, inv.getCodigoSku());
        pst.setString(2, inv.getNombreProducto());
        pst.setString(3, inv.getCategoria());
        pst.setString(4, inv.getProveedor());
        pst.setInt(5, inv.getStockInicial());
        pst.setInt(6, inv.getEntradas());
        pst.setInt(7, inv.getSalidas());
        pst.setInt(8, inv.getDevoluciones());
        pst.setInt(9, inv.getStockFinal());
        pst.setDouble(10, inv.getCostoUnitario());
        pst.setDouble(11, inv.getPrecioVenta());
        pst.setDouble(12, inv.getValorInventario());
        pst.setInt(13, inv.getPuntoReorden());
        pst.setString(14, inv.getEstado());
    }

    private static Inventario mapear(ResultSet rs) throws SQLException {
        Inventario inv = new Inventario();
        inv.setId(rs.getInt("id"));
        inv.setCodigoSku(rs.getString("codigo_sku"));
        inv.setNombreProducto(rs.getString("nombre_producto"));
        inv.setCategoria(rs.getString("categoria"));
        inv.setProveedor(rs.getString("proveedor"));
        inv.setStockInicial(rs.getInt("stock_inicial"));
        inv.setEntradas(rs.getInt("entradas"));
        inv.setSalidas(rs.getInt("salidas"));
        inv.setDevoluciones(rs.getInt("devoluciones"));
        inv.setStockFinal(rs.getInt("stock_final"));
        inv.setCostoUnitario(rs.getDouble("costo_unitario"));
        inv.setPrecioVenta(rs.getDouble("precio_venta"));
        inv.setValorInventario(rs.getDouble("valor_inventario"));
        inv.setPuntoReorden(rs.getInt("punto_reorden"));
        inv.setEstado(rs.getString("estado"));
        return inv;
    }
}
