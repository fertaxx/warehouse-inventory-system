package com.empresa.sistema_de_almacen_inventario.vistas.paneles;

import com.empresa.sistema_de_almacen_inventario.modelos.Cliente;
import com.empresa.sistema_de_almacen_inventario.database.ClienteDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

public class Frm_Clientes extends JPanel {

    private ArrayList<Cliente> listaClientes;
    private DefaultTableModel modeloTabla;

    private JTextField txtIdentificador;
    private JTextField txtNombres;
    private JTextField txtDNI;
    private JTextField txtRUC;
    private JTextField txtDireccion;
    private JComboBox<String> cboLocalidad;
    private JTable tablaClientes;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;

    public Frm_Clientes() {
        listaClientes = new ArrayList<>();
        initComponents();
        cargarClientesDesdeDB();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // Panel Superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setPreferredSize(new Dimension(1000, 80));
        panelSuperior.setLayout(null);
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblIcono = new JLabel("👥");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        lblIcono.setBounds(30, 20, 50, 40);
        panelSuperior.add(lblIcono);

        JLabel lblTitulo = new JLabel("Gestión de Clientes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(33, 33, 33));
        lblTitulo.setBounds(90, 20, 300, 30);
        panelSuperior.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Administre la información de sus clientes");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(117, 117, 117));
        lblSubtitulo.setBounds(90, 50, 300, 20);
        panelSuperior.add(lblSubtitulo);

        // Panel Principal con scroll
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setLayout(null);
        panelPrincipal.setPreferredSize(new Dimension(1010, 660));

        // Panel de Formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBounds(30, 20, 940, 250);
        panelFormulario.setLayout(null);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTituloForm = new JLabel("Información del Cliente");
        lblTituloForm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloForm.setForeground(new Color(33, 150, 243));
        lblTituloForm.setBounds(20, 10, 300, 25);
        panelFormulario.add(lblTituloForm);

        crearCampo(panelFormulario, "Identificador", txtIdentificador = new JTextField(), 20, 50, 280);
        crearCampo(panelFormulario, "Nombres y Apellidos", txtNombres = new JTextField(), 320, 50, 580);

        crearCampo(panelFormulario, "DNI", txtDNI = new JTextField(), 20, 120, 200);
        crearCampo(panelFormulario, "RUC", txtRUC = new JTextField(), 240, 120, 200);
        crearCampo(panelFormulario, "Dirección", txtDireccion = new JTextField(), 460, 120, 440);

        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLocalidad.setForeground(new Color(66, 66, 66));
        lblLocalidad.setBounds(20, 190, 100, 20);
        panelFormulario.add(lblLocalidad);

        cboLocalidad = new JComboBox<>(new String[]{"Comas", "Lima", "Callao", "San Juan de Lurigancho", "Otros"});
        cboLocalidad.setBounds(20, 210, 200, 35);
        cboLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cboLocalidad.setBackground(Color.WHITE);
        cboLocalidad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(cboLocalidad);

        btnNuevo = crearBoton("Nuevo", 260, 205, 130, 40, new Color(96, 125, 139));
        btnNuevo.addActionListener(evt -> limpiarCampos());
        panelFormulario.add(btnNuevo);

        btnGuardar = crearBoton("Guardar", 400, 205, 130, 40, new Color(76, 175, 80));
        btnGuardar.addActionListener(evt -> guardarCliente());
        panelFormulario.add(btnGuardar);

        btnEditar = crearBoton("Editar", 540, 205, 130, 40, new Color(255, 152, 0));
        btnEditar.addActionListener(evt -> editarCliente());
        panelFormulario.add(btnEditar);

        btnEliminar = crearBoton("Eliminar", 680, 205, 130, 40, new Color(244, 67, 54));
        btnEliminar.addActionListener(evt -> eliminarCliente());
        panelFormulario.add(btnEliminar);

        panelPrincipal.add(panelFormulario);

        // Panel de Tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBounds(30, 290, 940, 340);
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTituloTabla = new JLabel("📋 Lista de Clientes Registrados  —  doble clic para editar");
        lblTituloTabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTituloTabla.setForeground(new Color(33, 33, 33));
        lblTituloTabla.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelTabla.add(lblTituloTabla, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombres", "DNI", "RUC", "Dirección", "Localidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaClientes.setRowHeight(30);
        tablaClientes.setSelectionBackground(new Color(33, 150, 243, 50));
        tablaClientes.setSelectionForeground(new Color(33, 33, 33));
        tablaClientes.setGridColor(new Color(240, 240, 240));
        tablaClientes.setShowVerticalLines(true);
        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Clic simple: solo selecciona la fila visualmente (comportamiento por defecto de JTable)
                if (evt.getClickCount() == 2) {
                    int fila = tablaClientes.getSelectedRow();
                    if (fila >= 0) {
                        abrirDialogoEditar(listaClientes.get(fila));
                    }
                }
            }
        });

        JTableHeader header = tablaClientes.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(33, 150, 243));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setBackground(new Color(33, 150, 243));
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 12));
                setHorizontalAlignment(CENTER);
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

        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla);

        JScrollPane scrollPrincipal = new JScrollPane(panelPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.getHorizontalScrollBar().setUnitIncrement(16);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPrincipal, BorderLayout.CENTER);
        // </editor-fold>//GEN-END:initComponents
    }

    // ── Modal de edición ─────────────────────────────────────────────────

    private void abrirDialogoEditar(Cliente clienteOriginal) {
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Editar Cliente", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(520, 420);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dialog.setContentPane(panel);

        // Título del diálogo
        JLabel lblTitulo = new JLabel("✏️ Editar Cliente");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(33, 150, 243));
        lblTitulo.setBounds(20, 15, 300, 25);
        panel.add(lblTitulo);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 45, 470, 2);
        panel.add(sep);

        // Campos del diálogo
        JTextField dlgIdentificador = dlgCampo(panel, "Identificador", 20, 60, 220);
        dlgIdentificador.setText(clienteOriginal.getIdentificador());
        dlgIdentificador.setEditable(false);
        dlgIdentificador.setBackground(new Color(240, 240, 240));

        JTextField dlgNombres = dlgCampo(panel, "Nombres y Apellidos", 260, 60, 230);
        dlgNombres.setText(clienteOriginal.getNombres());

        JTextField dlgDNI = dlgCampo(panel, "DNI", 20, 140, 140);
        dlgDNI.setText(clienteOriginal.getDni());

        JTextField dlgRUC = dlgCampo(panel, "RUC", 180, 140, 140);
        dlgRUC.setText(clienteOriginal.getRuc());

        JTextField dlgDireccion = dlgCampo(panel, "Dirección", 340, 140, 150);
        dlgDireccion.setText(clienteOriginal.getDireccion());

        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblLocalidad.setForeground(new Color(66, 66, 66));
        lblLocalidad.setBounds(20, 220, 150, 20);
        panel.add(lblLocalidad);

        JComboBox<String> dlgLocalidad = new JComboBox<>(
            new String[]{"Comas", "Lima", "Callao", "San Juan de Lurigancho", "Otros"});
        dlgLocalidad.setBounds(20, 242, 200, 35);
        dlgLocalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dlgLocalidad.setBackground(Color.WHITE);
        dlgLocalidad.setSelectedItem(clienteOriginal.getLocalidad());
        panel.add(dlgLocalidad);

        // Botones
        JButton btnGuardarDlg = new JButton("Guardar Cambios");
        btnGuardarDlg.setBounds(20, 330, 180, 40);
        btnGuardarDlg.setBackground(Color.WHITE);
        btnGuardarDlg.setForeground(Color.BLACK);
        btnGuardarDlg.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardarDlg.setFocusPainted(false);
        btnGuardarDlg.setBorder(BorderFactory.createLineBorder(new Color(76, 175, 80), 2));
        btnGuardarDlg.setOpaque(true);
        btnGuardarDlg.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardarDlg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnGuardarDlg.setBackground(new Color(76, 175, 80));
                btnGuardarDlg.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnGuardarDlg.setBackground(Color.WHITE);
                btnGuardarDlg.setForeground(Color.BLACK);
            }
        });
        panel.add(btnGuardarDlg);

        JButton btnCancelarDlg = new JButton("Cancelar");
        btnCancelarDlg.setBounds(215, 330, 130, 40);
        btnCancelarDlg.setBackground(Color.WHITE);
        btnCancelarDlg.setForeground(Color.BLACK);
        btnCancelarDlg.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCancelarDlg.setFocusPainted(false);
        btnCancelarDlg.setBorder(BorderFactory.createLineBorder(new Color(96, 125, 139), 2));
        btnCancelarDlg.setOpaque(true);
        btnCancelarDlg.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelarDlg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnCancelarDlg.setBackground(new Color(96, 125, 139));
                btnCancelarDlg.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnCancelarDlg.setBackground(Color.WHITE);
                btnCancelarDlg.setForeground(Color.BLACK);
            }
        });
        panel.add(btnCancelarDlg);

        // Acciones
        btnGuardarDlg.addActionListener(e -> {
            String nombres = dlgNombres.getText().trim();
            if (nombres.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "⚠️ Ingrese los nombres", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                dlgNombres.requestFocus();
                return;
            }
            Cliente clienteActualizado = new Cliente(
                clienteOriginal.getIdentificador(),
                nombres,
                dlgDNI.getText().trim(),
                dlgRUC.getText().trim(),
                dlgDireccion.getText().trim(),
                dlgLocalidad.getSelectedItem().toString()
            );
            if (ClienteDAO.actualizar(clienteActualizado, clienteOriginal.getIdentificador())) {
                JOptionPane.showMessageDialog(dialog, "✅ Cliente actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                cargarClientesDesdeDB();
            }
        });

        btnCancelarDlg.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    /** Crea un JTextField con su etiqueta dentro del diálogo. */
    private JTextField dlgCampo(JPanel panel, String label, int x, int y, int width) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setBounds(x, y, width, 18);
        panel.add(lbl);

        JTextField tf = new JTextField();
        tf.setBounds(x, y + 20, width, 32);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        panel.add(tf);
        return tf;
    }

    // ── Helpers de UI ────────────────────────────────────────────────────

    private void crearCampo(JPanel panel, String label, JTextField campo, int x, int y, int width) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setBounds(x, y, width, 20);
        panel.add(lbl);

        campo.setBounds(x, y + 20, width, 35);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        panel.add(campo);
    }

    private JButton crearBoton(String texto, int x, int y, int width, int height, Color color) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, width, height);
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(color, 2));
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(color);
                btn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        });
        return btn;
    }

    // ── Lógica ───────────────────────────────────────────────────────────

    private void limpiarCampos() {
        txtIdentificador.setText("");
        txtNombres.setText("");
        txtDNI.setText("");
        txtRUC.setText("");
        txtDireccion.setText("");
        cboLocalidad.setSelectedIndex(0);
        txtIdentificador.setEditable(true);
        txtIdentificador.requestFocus();
    }

    private void guardarCliente() {
        if (validarCampos()) {
            Cliente cliente = new Cliente(
                txtIdentificador.getText().trim(),
                txtNombres.getText().trim(),
                txtDNI.getText().trim(),
                txtRUC.getText().trim(),
                txtDireccion.getText().trim(),
                cboLocalidad.getSelectedItem().toString()
            );
            if (ClienteDAO.guardar(cliente)) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "✅ Cliente guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarClientesDesdeDB();
                limpiarCampos();
            }
        }
    }

    private void editarCliente() {
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
            "ℹ️ Seleccione un cliente de la tabla haciendo doble clic",
            "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarCliente() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this),
                "¿Está seguro de eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                Cliente cliente = listaClientes.get(filaSeleccionada);
                if (ClienteDAO.eliminar(cliente.getIdentificador())) {
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                        "✅ Cliente eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarClientesDesdeDB();
                    limpiarCampos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Seleccione un cliente de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtIdentificador.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese el identificador", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtIdentificador.requestFocus();
            return false;
        }
        if (txtNombres.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese los nombres", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtNombres.requestFocus();
            return false;
        }
        return true;
    }

    private void agregarClienteATabla(Cliente cliente) {
        Object[] fila = {
            cliente.getIdentificador(), cliente.getNombres(), cliente.getDni(),
            cliente.getRuc(), cliente.getDireccion(), cliente.getLocalidad()
        };
        modeloTabla.addRow(fila);
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Cliente cliente : listaClientes) {
            agregarClienteATabla(cliente);
        }
    }

    private void cargarClientesDesdeDB() {
        listaClientes = ClienteDAO.obtenerTodos();
        actualizarTabla();
    }
}
