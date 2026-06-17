package com.empresa.sistema_de_almacen_inventario.vistas.paneles;

import com.empresa.sistema_de_almacen_inventario.database.UsuarioDAO;
import com.empresa.sistema_de_almacen_inventario.modelos.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class Frm_Usuarios extends JPanel {

    private ArrayList<Usuario> listaUsuarios;
    private DefaultTableModel modeloTabla;
    private JTable tablaUsuarios;

    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JComboBox<String> cboTipo;

    private static final String[] TIPOS = {"Administrador", "Usuario"};

    public Frm_Usuarios() {
        listaUsuarios = new ArrayList<>();
        initComponents();
        cargarDesdeDB();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // ── Panel principal con BoxLayout ────────────────────────────────
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(new Color(245, 245, 245));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ── Fila superior: Formulario + Tabla ────────────────────────────
        JPanel filaTop = new JPanel(new GridLayout(1, 2, 20, 0));
        filaTop.setBackground(new Color(245, 245, 245));
        filaTop.setMaximumSize(new Dimension(Integer.MAX_VALUE, 320));
        filaTop.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ── Card: Datos del Empleado ─────────────────────────────────────
        JPanel cardForm = new JPanel(new BorderLayout(0, 8));
        cardForm.setBackground(Color.WHITE);
        cardForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTituloForm = new JLabel("Datos del Empleado");
        lblTituloForm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloForm.setForeground(new Color(33, 150, 243));
        cardForm.add(lblTituloForm, BorderLayout.NORTH);

        JPanel innerForm = new JPanel(new GridBagLayout());
        innerForm.setBackground(Color.WHITE);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 4, 6, 4);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;

        innerForm.add(lbl("Identificación"), g);
        g.gridy = 1;
        txtIdentificacion = campo();
        innerForm.add(txtIdentificacion, g);

        g.gridy = 2;
        innerForm.add(lbl("Nombre del Usuario"), g);
        g.gridy = 3;
        txtNombre = campo();
        innerForm.add(txtNombre, g);

        g.gridy = 4;
        innerForm.add(lbl("Password"), g);
        g.gridy = 5;
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        txtPassword.setPreferredSize(new Dimension(0, 30));
        innerForm.add(txtPassword, g);

        g.gridy = 6; g.gridwidth = 1;
        innerForm.add(lbl("Tipo Usuario"), g);
        g.gridy = 7;
        cboTipo = new JComboBox<>(TIPOS);
        cboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cboTipo.setBackground(Color.WHITE);
        innerForm.add(cboTipo, g);

        // Botones
        g.gridy = 8; g.gridx = 0; g.gridwidth = 1; g.weightx = 0.5;
        JButton btnRegistrar = boton("Registrar", new Color(33, 150, 243));
        btnRegistrar.addActionListener(e -> registrar());
        innerForm.add(btnRegistrar, g);

        g.gridx = 1;
        JButton btnEditar = boton("Editar", new Color(255, 152, 0));
        btnEditar.addActionListener(e -> editarSeleccionado());
        innerForm.add(btnEditar, g);

        g.gridy = 9; g.gridx = 0;
        JButton btnEliminar = boton("Eliminar", new Color(244, 67, 54));
        btnEliminar.addActionListener(e -> eliminar());
        innerForm.add(btnEliminar, g);

        g.gridx = 1;
        JButton btnLimpiar = boton("Limpiar", new Color(96, 125, 139));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        innerForm.add(btnLimpiar, g);

        g.gridy = 10; g.gridx = 0; g.gridwidth = 2; g.weighty = 1.0;
        g.fill = GridBagConstraints.BOTH;
        innerForm.add(Box.createVerticalGlue(), g);

        cardForm.add(innerForm, BorderLayout.CENTER);
        filaTop.add(cardForm);

        // ── Card: Lista de Usuarios ──────────────────────────────────────
        JPanel cardTabla = new JPanel(new BorderLayout(0, 8));
        cardTabla.setBackground(Color.WHITE);
        cardTabla.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTituloTabla = new JLabel("Lista de Usuarios / Empleados");
        lblTituloTabla.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloTabla.setForeground(new Color(33, 33, 33));
        cardTabla.add(lblTituloTabla, BorderLayout.NORTH);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(230, 230, 230));
        cardTabla.add(sep, BorderLayout.AFTER_LAST_LINE);

        String[] cols = {"ID", "Nombre del Empleado", "Tipo Empleado"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaUsuarios.setRowHeight(32);
        tablaUsuarios.setSelectionBackground(new Color(33, 150, 243, 50));
        tablaUsuarios.setSelectionForeground(new Color(33, 33, 33));
        tablaUsuarios.setGridColor(new Color(240, 240, 240));
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaUsuarios.setShowVerticalLines(false);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaUsuarios.getSelectedRow();
                    if (fila >= 0) abrirDialogoEditar(listaUsuarios.get(fila));
                }
            }
        });

        // Header personalizado
        tablaUsuarios.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setBackground(new Color(33, 150, 243));
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 12));
                setHorizontalAlignment(LEFT);
            }
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int r, int c) {
                super.getTableCellRendererComponent(t, v, sel, foc, r, c);
                setText(v != null ? v.toString() : "");
                setBackground(new Color(33, 150, 243));
                setForeground(Color.WHITE);
                setOpaque(true);
                return this;
            }
        });
        tablaUsuarios.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        cardTabla.add(scroll, BorderLayout.CENTER);

        filaTop.add(cardTabla);
        panelContenido.add(filaTop);

        JScrollPane scrollPrincipal = new JScrollPane(panelContenido);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        // </editor-fold>//GEN-END:initComponents

        add(scrollPrincipal, BorderLayout.CENTER);
    }

    // ── Modal de edición ─────────────────────────────────────────────────

    private void abrirDialogoEditar(Usuario original) {
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Editar Usuario", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(460, 420);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        dialog.setContentPane(p);

        JLabel lblT = new JLabel("✏️ Editar Usuario");
        lblT.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblT.setForeground(new Color(33, 150, 243));
        lblT.setBounds(20, 15, 360, 25);
        p.add(lblT);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 45, 370, 2);
        p.add(sep);

        JTextField dId = dlgCampo(p, "Identificación", 20, 55, 360);
        dId.setText(original.getIdentificacion());
        dId.setEditable(false);
        dId.setBackground(new Color(240, 240, 240));

        JTextField dNombre = dlgCampo(p, "Nombre", 20, 120, 360);
        dNombre.setText(original.getNombre());

        JLabel lPass = new JLabel("Password");
        lPass.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lPass.setForeground(new Color(66, 66, 66));
        lPass.setBounds(20, 185, 360, 18);
        p.add(lPass);
        JPasswordField dPass = new JPasswordField(original.getPassword());
        dPass.setBounds(20, 205, 360, 30);
        dPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        p.add(dPass);

        JLabel lTipo = new JLabel("Tipo Usuario");
        lTipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lTipo.setForeground(new Color(66, 66, 66));
        lTipo.setBounds(20, 245, 180, 18);
        p.add(lTipo);
        JComboBox<String> dTipo = new JComboBox<>(TIPOS);
        dTipo.setBounds(20, 263, 180, 30);
        dTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dTipo.setBackground(Color.WHITE);
        dTipo.setSelectedItem(original.getTipoUsuario());
        p.add(dTipo);

        JButton btnSave = dlgBoton("Guardar Cambios", 20, 320, 180, 38, new Color(76, 175, 80));
        JButton btnCancel = dlgBoton("Cancelar", 215, 320, 120, 38, new Color(158, 158, 158));
        p.add(btnSave); p.add(btnCancel);

        btnSave.addActionListener(e -> {
            String pass = new String(dPass.getPassword()).trim();
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "⚠️ Ingrese el password", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Usuario u = new Usuario(original.getIdentificacion(), dNombre.getText().trim(), pass,
                dTipo.getSelectedItem().toString());
            if (UsuarioDAO.actualizar(u, original.getIdentificacion())) {
                JOptionPane.showMessageDialog(dialog, "✅ Usuario actualizado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                cargarDesdeDB();
            }
        });
        btnCancel.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    // ── Helpers UI ───────────────────────────────────────────────────────

    private JLabel lbl(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(new Color(66, 66, 66));
        return l;
    }

    private JTextField campo() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        tf.setPreferredSize(new Dimension(0, 30));
        return tf;
    }

    private JButton boton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(color, 2));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 34));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(color);
                btn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        });
        return btn;
    }

    private JTextField dlgCampo(JPanel p, String label, int x, int y, int w) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setBounds(x, y, w, 18);
        p.add(lbl);
        JTextField tf = new JTextField();
        tf.setBounds(x, y + 20, w, 30);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        p.add(tf);
        return tf;
    }

    private JButton dlgBoton(String texto, int x, int y, int w, int h, Color color) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, w, h);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(color, 2));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(color);
                btn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        });
        return btn;
    }

    // ── Lógica ───────────────────────────────────────────────────────────

    private void registrar() {
        String id = txtIdentificacion.getText().trim();
        String nombre = txtNombre.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (id.isEmpty() || nombre.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Complete todos los campos", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Usuario u = new Usuario(id, nombre, pass, cboTipo.getSelectedItem().toString());
        if (UsuarioDAO.registrar(u)) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "✅ Usuario registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDesdeDB();
            limpiarCampos();
        }
    }

    private void editarSeleccionado() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "ℹ️ Seleccione un usuario de la tabla haciendo doble clic", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        abrirDialogoEditar(listaUsuarios.get(fila));
    }

    private void eliminar() {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Seleccione un usuario de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int conf = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this),
            "¿Está seguro de eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            Usuario u = listaUsuarios.get(fila);
            if (UsuarioDAO.eliminar(u.getIdentificacion())) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "✅ Usuario eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDesdeDB();
                limpiarCampos();
            }
        }
    }

    private void limpiarCampos() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtPassword.setText("");
        cboTipo.setSelectedIndex(0);
        txtIdentificacion.requestFocus();
    }

    private void cargarDesdeDB() {
        listaUsuarios = UsuarioDAO.obtenerTodos();
        modeloTabla.setRowCount(0);
        for (Usuario u : listaUsuarios) {
            modeloTabla.addRow(new Object[]{
                u.getId(), u.getNombre(), u.getTipoUsuario()
            });
        }
    }
}
