package com.empresa.sistema_de_almacen_inventario.vistas.presentacion;

import com.empresa.sistema_de_almacen_inventario.vistas.paneles.Frm_Clientes;
import com.empresa.sistema_de_almacen_inventario.vistas.paneles.Frm_Productos;
import com.empresa.sistema_de_almacen_inventario.vistas.paneles.Frm_Ventas;
import com.empresa.sistema_de_almacen_inventario.vistas.paneles.Frm_Inventario;
import com.empresa.sistema_de_almacen_inventario.vistas.paneles.Frm_Usuarios;
import com.empresa.sistema_de_almacen_inventario.vistas.paneles.Frm_HistorialVentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frm_MenuPrincipal extends JFrame {
    
    private JPanel panelIzquierdo;
    private JPanel panelSuperior;
    private JPanel panelContenido; // Panel que muestra el módulo activo
    private JLabel lblFecha;
    private JLabel lblHora;
    private JLabel lblSistema;
    private JButton btnClientes;
    private JButton btnProductos;
    private JButton btnCerrar;
    private Timer timer;
    private String moduloActivo = null; // Controla qué módulo está abierto
    
    public Frm_MenuPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        iniciarReloj();
    }
    
    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setSize(1500, 860);
        setMinimumSize(new Dimension(1300, 750));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        
        // Panel Izquierdo (Menú) - Diseño mejorado
        panelIzquierdo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(13, 71, 161), 0, getHeight(), new Color(25, 118, 210));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelIzquierdo.setPreferredSize(new Dimension(250, 700));
        panelIzquierdo.setLayout(null);
        
        // Logo/Título
        JLabel lblLogo = new JLabel("InnoTech");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblLogo.setForeground(Color.WHITE);  // BLANCO para fondo azul
        lblLogo.setBounds(30, 30, 190, 35);
        panelIzquierdo.add(lblLogo);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(200, 230, 255));
        lblSubtitulo.setBounds(30, 65, 190, 20);
        panelIzquierdo.add(lblSubtitulo);
        
        // Separador
        JSeparator separador = new JSeparator();
        separador.setBounds(20, 100, 210, 2);
        separador.setForeground(new Color(255, 255, 255, 100));
        panelIzquierdo.add(separador);
        
        // Botones del menú
        JButton btnInicio = crearBotonMenuModerno("⌂ Inicio", 130);
        btnClientes = crearBotonMenuModerno("▪ Clientes", 185);
        btnProductos = crearBotonMenuModerno("▪ Productos", 240);
        JButton btnVentas = crearBotonMenuModerno("▪ Ventas", 295);
        JButton btnInventario = crearBotonMenuModerno("▪ Inventario", 350);
        JButton btnUsuarios = crearBotonMenuModerno("▪ Usuarios", 405);
        JButton btnHistorial = crearBotonMenuModerno("▪ Historial Ventas", 460);

        panelIzquierdo.add(btnInicio);
        panelIzquierdo.add(btnClientes);
        panelIzquierdo.add(btnProductos);
        panelIzquierdo.add(btnVentas);
        panelIzquierdo.add(btnInventario);
        panelIzquierdo.add(btnUsuarios);
        panelIzquierdo.add(btnHistorial);
        
        // Información del usuario en la parte inferior
        JLabel lblUsuario = new JLabel("Usuario: Admin");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblUsuario.setForeground(Color.WHITE);  // BLANCO para fondo azul
        lblUsuario.setBounds(30, 620, 190, 25);
        panelIzquierdo.add(lblUsuario);
        
        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBounds(30, 650, 190, 30);
        btnSalir.setBackground(new Color(244, 67, 54));
        btnSalir.setForeground(Color.WHITE);  // BLANCO para botón rojo
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(evt -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                this.dispose();
                new Frm_Login().setVisible(true);
            }
        });
        panelIzquierdo.add(btnSalir);
        
        // Panel Superior con BorderLayout para que sea responsive
        panelSuperior = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRect(0, getHeight() - 3, getWidth(), 3);
            }
        };
        panelSuperior.setPreferredSize(new Dimension(850, 100));
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 10));

        // Sub-panel izquierdo: título y subtítulo
        JPanel subIzq = new JPanel();
        subIzq.setOpaque(false);
        subIzq.setLayout(new BoxLayout(subIzq, BoxLayout.Y_AXIS));
        subIzq.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));

        lblSistema = new JLabel("Panel de Control");
        lblSistema.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblSistema.setForeground(new Color(33, 33, 33));
        subIzq.add(lblSistema);

        JLabel lblBienvenida = new JLabel("Bienvenido al sistema de gestión de inventario");
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblBienvenida.setForeground(new Color(117, 117, 117));
        subIzq.add(lblBienvenida);

        panelSuperior.add(subIzq, BorderLayout.WEST);

        // Sub-panel derecho: fecha, hora y botón cerrar
        JPanel subDer = new JPanel();
        subDer.setOpaque(false);
        subDer.setLayout(new BorderLayout());
        subDer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 5));

        JPanel subDerTexto = new JPanel();
        subDerTexto.setOpaque(false);
        subDerTexto.setLayout(new BoxLayout(subDerTexto, BoxLayout.Y_AXIS));
        subDerTexto.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 10));

        lblFecha = new JLabel();
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblFecha.setForeground(new Color(117, 117, 117));
        lblFecha.setAlignmentX(Component.RIGHT_ALIGNMENT);
        subDerTexto.add(lblFecha);

        lblHora = new JLabel();
        lblHora.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblHora.setForeground(new Color(25, 118, 210));
        lblHora.setAlignmentX(Component.RIGHT_ALIGNMENT);
        subDerTexto.add(lblHora);

        subDer.add(subDerTexto, BorderLayout.CENTER);

        btnCerrar = new JButton("✕");
        btnCerrar.setPreferredSize(new Dimension(45, 45));
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setForeground(new Color(244, 67, 54));
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCerrar.setBackground(new Color(244, 67, 54));
                btnCerrar.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                btnCerrar.setBackground(Color.WHITE);
                btnCerrar.setForeground(new Color(244, 67, 54));
            }
        });
        btnCerrar.addActionListener(evt -> System.exit(0));
        subDer.add(btnCerrar, BorderLayout.EAST);

        panelSuperior.add(subDer, BorderLayout.EAST);
        
        // Panel de contenido central - muestra dashboard o el módulo activo
        panelContenido = new JPanel();
        panelContenido.setBackground(new Color(245, 245, 245));
        panelContenido.setLayout(new GridLayout(2, 3, 40, 40));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        panelContenido.add(crearTarjetaModulo("Clientes", "👥", "Administrar clientes", new Color(33, 150, 243)));
        panelContenido.add(crearTarjetaModulo("Productos", "📦", "Control de inventario", new Color(76, 175, 80)));
        panelContenido.add(crearTarjetaModulo("Ventas", "🛒", "Registro de ventas", new Color(255, 152, 0)));
        panelContenido.add(crearTarjetaModulo("Inventario", "📊", "Control de existencias", new Color(103, 58, 183)));
        panelContenido.add(crearTarjetaModulo("Usuarios", "👤", "Gestión de empleados", new Color(0, 150, 136)));
        panelContenido.add(crearTarjetaModulo("Historial Ventas", "🧾", "Registro histórico", new Color(121, 85, 72)));
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
        // </editor-fold>//GEN-END:initComponents
    }
    
    private JButton crearBotonMenuModerno(String texto, int y) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2d.setColor(new Color(255, 255, 255, 40));
                } else if (getModel().isRollover()) {
                    g2d.setColor(new Color(255, 255, 255, 30));
                } else {
                    g2d.setColor(new Color(255, 255, 255, 10));
                }
                
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        
        btn.setBounds(20, y, 210, 45);
        btn.setForeground(Color.WHITE);  // TEXTO BLANCO para fondo azul
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        
        // CORREGIDO: Quitar el prefijo antes de buscar el módulo
        btn.addActionListener(evt -> {
            String nombreModulo = texto.replaceAll("^[▪⌂] ", "").trim();
            abrirModulo(nombreModulo);
        });
        
        return btn;
    }
    
    private JPanel crearTarjetaModulo(String nombre, String icono, String descripcion, Color color) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Sombra
                g2d.setColor(new Color(0, 0, 0, 30));
                g2d.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 20, 20);
                
                // Fondo blanco
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, 20, 20);
                
                // Barra superior de color
                g2d.setColor(color);
                g2d.fillRoundRect(0, 0, getWidth() - 5, 8, 20, 20);
            }
        };
        
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Panel superior con icono
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20));
        
        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        panelSuperior.add(lblIcono, BorderLayout.CENTER);
        
        // Panel inferior con texto
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setOpaque(false);
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNombre.setForeground(new Color(33, 33, 33));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescripcion.setForeground(new Color(117, 117, 117));
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelInferior.add(lblNombre);
        panelInferior.add(Box.createVerticalStrut(5));
        panelInferior.add(lblDescripcion);
        
        panel.add(panelSuperior, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);
        
        // Efectos hover y click - CORREGIDO PARA QUE FUNCIONE
        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                panel.setBorder(BorderFactory.createLineBorder(color, 2));
            }
            public void mouseExited(MouseEvent evt) {
                panel.setBorder(null);
            }
            public void mouseClicked(MouseEvent evt) {
                abrirModulo(nombre);
            }
        });
        
        return panel;
    }
    
    private void abrirModulo(String modulo) {
        // Inicio siempre vuelve al dashboard aunque esté activo
        if (modulo.equals(moduloActivo) && !modulo.equals("Inicio")) return;

        moduloActivo = modulo;
        remove(panelContenido);

        switch (modulo) {
            case "Clientes":
                panelContenido = new Frm_Clientes();
                lblSistema.setText("Clientes");
                break;
            case "Productos":
                panelContenido = new Frm_Productos();
                lblSistema.setText("Productos");
                break;
            case "Ventas":
                panelContenido = new Frm_Ventas();
                lblSistema.setText("Ventas");
                break;
            case "Inventario":
                panelContenido = new Frm_Inventario();
                lblSistema.setText("Inventario");
                break;
            case "Usuarios":
                panelContenido = new Frm_Usuarios();
                lblSistema.setText("Usuarios");
                break;
            case "Historial Ventas":
                panelContenido = new Frm_HistorialVentas();
                lblSistema.setText("Historial de Ventas");
                break;
            default:
                moduloActivo = null;
                lblSistema.setText("Panel de Control");
                panelContenido = new JPanel();
                panelContenido.setBackground(new Color(245, 245, 245));
                panelContenido.setLayout(new GridLayout(2, 3, 40, 40));
                panelContenido.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
                panelContenido.add(crearTarjetaModulo("Clientes", "👥", "Administrar clientes", new Color(33, 150, 243)));
                panelContenido.add(crearTarjetaModulo("Productos", "📦", "Control de inventario", new Color(76, 175, 80)));
                panelContenido.add(crearTarjetaModulo("Ventas", "🛒", "Registro de ventas", new Color(255, 152, 0)));
                panelContenido.add(crearTarjetaModulo("Inventario", "📊", "Control de existencias", new Color(103, 58, 183)));
                panelContenido.add(crearTarjetaModulo("Usuarios", "👤", "Gestión de empleados", new Color(0, 150, 136)));
                panelContenido.add(crearTarjetaModulo("Historial Ventas", "🧾", "Registro histórico", new Color(121, 85, 72)));
                break;
        }

        add(panelContenido, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void iniciarReloj() {
        timer = new Timer(1000, e -> actualizarReloj());
        timer.start();
        actualizarReloj();
    }
    
    private void actualizarReloj() {
        Date ahora = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        
        lblFecha.setText(formatoFecha.format(ahora));
        lblHora.setText(formatoHora.format(ahora));
    }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_MenuPrincipal().setVisible(true);
        });
    }
}
