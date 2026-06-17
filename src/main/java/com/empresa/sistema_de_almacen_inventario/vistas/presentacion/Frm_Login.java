package com.empresa.sistema_de_almacen_inventario.vistas.presentacion;

import com.empresa.sistema_de_almacen_inventario.database.UsuarioDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class Frm_Login extends JFrame {
    private int xMouse;
    private int yMouse;
    
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnAcceder;
    private JButton btnCancelar;
    private JButton btnCerrar;
    
    public Frm_Login() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(900, 550);
        setLayout(new BorderLayout());
        setShape(new RoundRectangle2D.Double(0, 0, 900, 550, 30, 30));
        
        // Panel Izquierdo con gradiente
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
        panelIzquierdo.setPreferredSize(new Dimension(450, 550));
        panelIzquierdo.setLayout(null);
        
        // Logo grande
        JLabel lblIcono = new JLabel("🏢");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        lblIcono.setBounds(185, 120, 100, 100);
        panelIzquierdo.add(lblIcono);
        
        JLabel lblTitulo = new JLabel("InnoTech");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 220, 350, 50);
        panelIzquierdo.add(lblTitulo);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Almacén e Inventario");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(200, 230, 255));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(50, 270, 350, 30);
        panelIzquierdo.add(lblSubtitulo);
        
        // Información inferior
        JLabel lblVersion = new JLabel("Versión 1.0");
        lblVersion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblVersion.setForeground(new Color(200, 230, 255));
        lblVersion.setBounds(30, 480, 150, 20);
        panelIzquierdo.add(lblVersion);
        
        JLabel lblAutorizado = new JLabel("Autorizado por: Sistema de Gestión Empresarial SAC");
        lblAutorizado.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblAutorizado.setForeground(new Color(200, 230, 255));
        lblAutorizado.setBounds(30, 500, 400, 20);
        panelIzquierdo.add(lblAutorizado);
        
        JLabel lblDistribuido = new JLabel("Distribuido por: Consac Peru");
        lblDistribuido.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblDistribuido.setForeground(new Color(200, 230, 255));
        lblDistribuido.setBounds(30, 515, 400, 20);
        panelIzquierdo.add(lblDistribuido);
        
        // Panel Derecho
        panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.WHITE);
        panelDerecho.setPreferredSize(new Dimension(450, 550));
        panelDerecho.setLayout(null);
        
        // Botón cerrar
        btnCerrar = new JButton("✕");
        btnCerrar.setBounds(400, 10, 40, 40);
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
        panelDerecho.add(btnCerrar);
        
        // Icono de usuario
        JLabel lblUsuarioIcono = new JLabel("👤");
        lblUsuarioIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        lblUsuarioIcono.setBounds(195, 80, 60, 60);
        panelDerecho.add(lblUsuarioIcono);
        
        JLabel lblBienvenida = new JLabel("Bienvenido");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblBienvenida.setForeground(new Color(33, 33, 33));
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenida.setBounds(50, 150, 350, 35);
        panelDerecho.add(lblBienvenida);
        
        JLabel lblInstruccion = new JLabel("Ingrese sus credenciales para continuar");
        lblInstruccion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblInstruccion.setForeground(new Color(117, 117, 117));
        lblInstruccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblInstruccion.setBounds(50, 185, 350, 25);
        panelDerecho.add(lblInstruccion);
        
        // Campo Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblEmail.setForeground(new Color(33, 33, 33));
        lblEmail.setBounds(75, 240, 100, 20);
        panelDerecho.add(lblEmail);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(75, 265, 300, 40);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtUsuario.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(25, 118, 210), 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
            public void focusLost(FocusEvent e) {
                txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
        });
        panelDerecho.add(txtUsuario);
        
        // Campo Contraseña
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblContrasena.setForeground(new Color(33, 33, 33));
        lblContrasena.setBounds(75, 320, 100, 20);
        panelDerecho.add(lblContrasena);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(75, 345, 300, 40);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtPassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtPassword.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(25, 118, 210), 2),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
            public void focusLost(FocusEvent e) {
                txtPassword.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
                ));
            }
        });
        txtPassword.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnAccederActionPerformed(null);
                }
            }
        });
        panelDerecho.add(txtPassword);
        
        // Botón Acceder
        btnAcceder = new JButton("Iniciar Sesión");
        btnAcceder.setBounds(75, 410, 300, 45);
        btnAcceder.setBackground(new Color(25, 118, 210));
        btnAcceder.setForeground(Color.BLACK);  // TEXTO NEGRO
        btnAcceder.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAcceder.setFocusPainted(false);
        btnAcceder.setBorderPainted(false);
        btnAcceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAcceder.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnAcceder.setBackground(new Color(21, 101, 192));
            }
            public void mouseExited(MouseEvent e) {
                btnAcceder.setBackground(new Color(25, 118, 210));
            }
        });
        btnAcceder.addActionListener(evt -> btnAccederActionPerformed(evt));
        panelDerecho.add(btnAcceder);
        
        // Link de ayuda
        JLabel lblAyuda = new JLabel("<html><u>¿Olvidaste tu contraseña?</u></html>");
        lblAyuda.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblAyuda.setForeground(new Color(25, 118, 210));
        lblAyuda.setHorizontalAlignment(SwingConstants.CENTER);
        lblAyuda.setBounds(75, 470, 300, 20);
        lblAyuda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblAyuda.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Contacte al administrador del sistema", "Ayuda", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelDerecho.add(lblAyuda);
        
        // Hacer la ventana arrastrable
        panelDerecho.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });
        
        panelDerecho.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                setLocation(x - xMouse - 450, y - yMouse);
            }
        });
        
        panelIzquierdo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                xMouse = evt.getX();
                yMouse = evt.getY();
            }
        });
        
        panelIzquierdo.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int x = evt.getXOnScreen();
                int y = evt.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
        
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        // </editor-fold>//GEN-END:initComponents
    }
    
    private void btnAccederActionPerformed(ActionEvent evt) {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword());
        
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese su email", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtUsuario.requestFocus();
            return;
        }
        
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese su contraseña", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return;
        }
        
        // Validar contra la base de datos
        if (UsuarioDAO.validarCredenciales(usuario, password)) {
            JOptionPane.showMessageDialog(this, "¡Bienvenido al sistema!", "Acceso Exitoso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new Frm_MenuPrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Login().setVisible(true);
        });
    }
}
