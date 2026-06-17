/*
 * Sistema de Almacén e Inventario - InnoTech
 * Aplicación de escritorio para gestión de inventario, clientes, productos y ventas
 */

package com.empresa.sistema_de_almacen_inventario;

import com.empresa.sistema_de_almacen_inventario.vistas.presentacion.Frm_Login;
import com.empresa.sistema_de_almacen_inventario.database.Conexion;
import javax.swing.UIManager;
import java.sql.Connection;

/**
 * Sistema de Almacén e Inventario - InnoTech
 * Aplicación de escritorio para gestión de inventario, clientes, productos y ventas
 * 
 * @author alu_torre1
 */
public class Sistema_de_almacen_inventario {

    public static void main(String[] args) {
        // Establecer el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Error al establecer el Look and Feel: " + ex.getMessage());
        }
        
        // Probar conexión a la base de datos
        System.out.println("===========================================");
        System.out.println("  SISTEMA DE ALMACÉN E INVENTARIO - InnoTech");
        System.out.println("===========================================");
        System.out.println("Probando conexión a la base de datos...");
        
        Connection conn = Conexion.getConexion();
        if (conn != null) {
            System.out.println("✅ Sistema listo para usar");
            Conexion.cerrarConexion();
        } else {
            System.out.println("⚠️ El sistema funcionará sin base de datos (solo en memoria)");
        }
        System.out.println("===========================================\n");
        
        // Iniciar la aplicación con el formulario de Login
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Login().setVisible(true);
        });
    }
}
