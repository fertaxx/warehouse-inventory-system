package com.empresa.sistema_de_almacen_inventario.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";  // Cambia esto por tu contraseña de MySQL
    
    private static Connection conexion = null;
    
    /**
     * Obtiene una conexión a la base de datos
     * @return Connection objeto de conexión
     */
    public static Connection getConexion() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos");
            return conexion;
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver de MySQL no encontrado");
            JOptionPane.showMessageDialog(null, 
                "Error: Driver de MySQL no encontrado\n" + e.getMessage(), 
                "Error de Driver", 
                JOptionPane.ERROR_MESSAGE);
            return null;
            
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos");
            JOptionPane.showMessageDialog(null, 
                "Error al conectar con la base de datos\n" + 
                "Verifica que MySQL esté ejecutándose y que la base de datos exista\n\n" + 
                e.getMessage(), 
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Cierra la conexión a la base de datos
     */
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("✅ Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si hay conexión activa
     * @return true si hay conexión, false si no
     */
    public static boolean hayConexion() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
