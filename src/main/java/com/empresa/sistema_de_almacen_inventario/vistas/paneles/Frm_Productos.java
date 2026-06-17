package com.empresa.sistema_de_almacen_inventario.vistas.paneles;

import com.empresa.sistema_de_almacen_inventario.modelos.Producto;
import com.empresa.sistema_de_almacen_inventario.database.ProductoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Frm_Productos extends JPanel {

    private ArrayList<Producto> listaProductos;
    private DefaultTableModel modeloTabla;

    private JTextField txtNoSerie;
    private JTextField txtProducto;
    private JComboBox<String> cboMarca;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JCheckBox chkStock;
    private JTable tablaProductos;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JTextField txtFechaCompra;
    private JTextField txtFechaCaducidad;
    private JTextField txtBuscarTexto;
    private JComboBox<String> cboBuscarPor;
    private JTextField txtBuscarFecha;
    private JButton btnBuscar;
    private JButton btnMostrarTodos;

    public Frm_Productos() {
        listaProductos = new ArrayList<>();
        initComponents();
        cargarProductosDesdeDB();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // Panel Superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setPreferredSize(new Dimension(1300, 80));
        panelSuperior.setLayout(null);
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblIcono = new JLabel("📦");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        lblIcono.setBounds(30, 20, 50, 40);
        panelSuperior.add(lblIcono);

        JLabel lblTitulo = new JLabel("Gestión de Productos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(33, 33, 33));
        lblTitulo.setBounds(90, 20, 300, 30);
        panelSuperior.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Control de inventario y productos");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(117, 117, 117));
        lblSubtitulo.setBounds(90, 50, 300, 20);
        panelSuperior.add(lblSubtitulo);

        // Panel Principal con scroll
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setLayout(null);
        panelPrincipal.setPreferredSize(new Dimension(1280, 820));

        // Panel de Formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBounds(30, 20, 1240, 350);
        panelFormulario.setLayout(null);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTituloForm = new JLabel("Información del Producto");
        lblTituloForm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTituloForm.setForeground(new Color(76, 175, 80));
        lblTituloForm.setBounds(20, 10, 300, 25);
        panelFormulario.add(lblTituloForm);

        crearCampo(panelFormulario, "No. Serie", txtNoSerie = new JTextField(), 20, 50, 200);
        crearCampo(panelFormulario, "Producto", txtProducto = new JTextField(), 20, 120, 200);
        crearCampo(panelFormulario, "Descripción", txtDescripcion = new JTextField(), 20, 190, 200);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMarca.setForeground(new Color(66, 66, 66));
        lblMarca.setBounds(240, 50, 100, 20);
        panelFormulario.add(lblMarca);

        cboMarca = new JComboBox<>(new String[]{"Samsung", "LG", "Sony", "Apple", "Xiaomi", "Huawei", "Otra"});
        cboMarca.setBounds(240, 70, 200, 35);
        cboMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cboMarca.setBackground(Color.WHITE);
        cboMarca.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(cboMarca);

        crearCampo(panelFormulario, "Precio (S/.)", txtPrecio = new JTextField(), 240, 120, 90);
        crearCampo(panelFormulario, "Cantidad", txtCantidad = new JTextField(), 350, 120, 90);

        crearCampo(panelFormulario, "Fecha Compra (dd/MM/yyyy)", txtFechaCompra = new JTextField(), 240, 190, 200);
        txtFechaCompra.setToolTipText("Formato: dd/MM/yyyy");

        crearCampo(panelFormulario, "Fecha Caducidad (dd/MM/yyyy)", txtFechaCaducidad = new JTextField(), 240, 260, 200);
        txtFechaCaducidad.setToolTipText("Formato: dd/MM/yyyy");

        chkStock = new JCheckBox("En Stock");
        chkStock.setBounds(20, 270, 200, 30);
        chkStock.setFont(new Font("Segoe UI", Font.BOLD, 12));
        chkStock.setBackground(Color.WHITE);
        chkStock.setForeground(new Color(76, 175, 80));
        chkStock.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelFormulario.add(chkStock);

        // Panel de Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(250, 250, 250));
        panelBotones.setBounds(460, 50, 740, 280);
        panelBotones.setLayout(new GridLayout(4, 1, 0, 15));
        panelBotones.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        btnNuevo = crearBotonGrande("Nuevo Producto", new Color(96, 125, 139));
        btnNuevo.addActionListener(evt -> limpiarCampos());
        panelBotones.add(btnNuevo);

        btnGuardar = crearBotonGrande("Guardar", new Color(76, 175, 80));
        btnGuardar.addActionListener(evt -> guardarProducto());
        panelBotones.add(btnGuardar);

        btnEditar = crearBotonGrande("Editar", new Color(255, 152, 0));
        btnEditar.addActionListener(evt -> editarProducto());
        panelBotones.add(btnEditar);

        btnEliminar = crearBotonGrande("Eliminar", new Color(244, 67, 54));
        btnEliminar.addActionListener(evt -> eliminarProducto());
        panelBotones.add(btnEliminar);

        panelFormulario.add(panelBotones);
        panelPrincipal.add(panelFormulario);

        // Panel de Búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBounds(30, 390, 1240, 120);
        panelBusqueda.setLayout(null);
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblBusqueda = new JLabel("🔍 Búsqueda de Productos");
        lblBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBusqueda.setForeground(new Color(33, 33, 33));
        lblBusqueda.setBounds(15, 5, 250, 25);
        panelBusqueda.add(lblBusqueda);

        JLabel lblBuscarPor = new JLabel("Buscar por:");
        lblBuscarPor.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblBuscarPor.setForeground(new Color(66, 66, 66));
        lblBuscarPor.setBounds(15, 35, 80, 20);
        panelBusqueda.add(lblBuscarPor);

        cboBuscarPor = new JComboBox<>(new String[]{"Nombre", "Marca", "No. Serie", "Descripción", "Fecha Compra", "Fecha Caducidad"});
        cboBuscarPor.setBounds(95, 32, 150, 30);
        cboBuscarPor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        cboBuscarPor.setBackground(Color.WHITE);
        cboBuscarPor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cboBuscarPor.addActionListener(evt -> actualizarCamposBusqueda());
        panelBusqueda.add(cboBuscarPor);

        JLabel lblTexto = new JLabel("Texto:");
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTexto.setForeground(new Color(66, 66, 66));
        lblTexto.setBounds(265, 35, 50, 20);
        panelBusqueda.add(lblTexto);

        txtBuscarTexto = new JTextField();
        txtBuscarTexto.setBounds(315, 32, 300, 30);
        txtBuscarTexto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscarTexto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtBuscarTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) buscarProductos();
            }
        });
        panelBusqueda.add(txtBuscarTexto);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFecha.setForeground(new Color(66, 66, 66));
        lblFecha.setBounds(265, 75, 50, 20);
        panelBusqueda.add(lblFecha);

        txtBuscarFecha = new JTextField();
        txtBuscarFecha.setBounds(315, 72, 200, 30);
        txtBuscarFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscarFecha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtBuscarFecha.setToolTipText("Formato: dd/MM/yyyy");
        txtBuscarFecha.setEnabled(false);
        txtBuscarFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) buscarProductos();
            }
        });
        panelBusqueda.add(txtBuscarFecha);

        JLabel lblFormatoFecha = new JLabel("(dd/MM/yyyy)");
        lblFormatoFecha.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        lblFormatoFecha.setForeground(new Color(117, 117, 117));
        lblFormatoFecha.setBounds(525, 75, 100, 20);
        panelBusqueda.add(lblFormatoFecha);

        btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.setBounds(635, 52, 120, 30);
        btnBuscar.setBackground(new Color(33, 150, 243));
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(evt -> buscarProductos());
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btnBuscar.setBackground(new Color(21, 101, 192)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btnBuscar.setBackground(new Color(33, 150, 243)); }
        });
        panelBusqueda.add(btnBuscar);

        btnMostrarTodos = new JButton("📋 Mostrar Todos");
        btnMostrarTodos.setBounds(775, 52, 150, 30);
        btnMostrarTodos.setBackground(new Color(96, 125, 139));
        btnMostrarTodos.setForeground(Color.BLACK);
        btnMostrarTodos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnMostrarTodos.setFocusPainted(false);
        btnMostrarTodos.setBorderPainted(false);
        btnMostrarTodos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMostrarTodos.addActionListener(evt -> cargarProductosDesdeDB());
        btnMostrarTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btnMostrarTodos.setBackground(new Color(69, 90, 100)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btnMostrarTodos.setBackground(new Color(96, 125, 139)); }
        });
        panelBusqueda.add(btnMostrarTodos);

        panelPrincipal.add(panelBusqueda);

        // Panel de Tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBounds(30, 530, 1240, 270);
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTituloTabla = new JLabel("📋 Inventario de Productos  —  doble clic para editar");
        lblTituloTabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTituloTabla.setForeground(new Color(33, 33, 33));
        lblTituloTabla.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelTabla.add(lblTituloTabla, BorderLayout.NORTH);

        String[] columnas = {"Serie", "Producto", "Marca", "Descripción", "Precio", "Cantidad", "F. Compra", "F. Caducidad", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaProductos.setRowHeight(30);
        tablaProductos.setSelectionBackground(new Color(76, 175, 80, 50));
        tablaProductos.setSelectionForeground(new Color(33, 33, 33));
        tablaProductos.setGridColor(new Color(240, 240, 240));
        tablaProductos.setShowVerticalLines(true);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Clic simple: solo selecciona la fila visualmente (comportamiento por defecto de JTable)
                if (evt.getClickCount() == 2) {
                    int fila = tablaProductos.getSelectedRow();
                    if (fila >= 0) {
                        abrirDialogoEditar(listaProductos.get(fila));
                    }
                }
            }
        });

        JTableHeader header = tablaProductos.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(76, 175, 80));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setBackground(new Color(76, 175, 80));
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 12));
                setHorizontalAlignment(CENTER);
            }
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean sel, boolean foc, int r, int c) {
                super.getTableCellRendererComponent(t, v, sel, foc, r, c);
                setText(v != null ? v.toString() : "");
                setBackground(new Color(76, 175, 80));
                setForeground(Color.WHITE);
                setOpaque(true);
                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        panelPrincipal.add(panelTabla);

        JScrollPane scrollPrincipal = new JScrollPane(panelPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPrincipal, BorderLayout.CENTER);
        // </editor-fold>//GEN-END:initComponents
    }

    // ── Modal de edición ─────────────────────────────────────────────────

    private void abrirDialogoEditar(Producto productoOriginal) {
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Editar Producto", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(560, 500);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dialog.setContentPane(panel);

        // Título
        JLabel lblTitulo = new JLabel("✏️ Editar Producto");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(76, 175, 80));
        lblTitulo.setBounds(20, 15, 300, 25);
        panel.add(lblTitulo);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 45, 510, 2);
        panel.add(sep);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Fila 1: No. Serie (no editable) + Producto
        JTextField dlgNoSerie = dlgCampo(panel, "No. Serie", 20, 60, 160);
        dlgNoSerie.setText(productoOriginal.getNoSerie());
        dlgNoSerie.setEditable(false);
        dlgNoSerie.setBackground(new Color(240, 240, 240));

        JTextField dlgProducto = dlgCampo(panel, "Producto", 200, 60, 330);
        dlgProducto.setText(productoOriginal.getNombre());

        // Fila 2: Marca (JComboBox) + Descripción
        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMarca.setForeground(new Color(66, 66, 66));
        lblMarca.setBounds(20, 140, 160, 18);
        panel.add(lblMarca);

        JComboBox<String> dlgMarca = new JComboBox<>(
            new String[]{"Samsung", "LG", "Sony", "Apple", "Xiaomi", "Huawei", "Otra"});
        dlgMarca.setBounds(20, 160, 160, 32);
        dlgMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dlgMarca.setBackground(Color.WHITE);
        dlgMarca.setSelectedItem(productoOriginal.getMarca());
        panel.add(dlgMarca);

        JTextField dlgDescripcion = dlgCampo(panel, "Descripción", 200, 140, 330);
        dlgDescripcion.setText(productoOriginal.getDescripcion());

        // Fila 3: Precio + Cantidad
        JTextField dlgPrecio = dlgCampo(panel, "Precio (S/.)", 20, 215, 150);
        dlgPrecio.setText(String.valueOf(productoOriginal.getPrecio()));

        JTextField dlgCantidad = dlgCampo(panel, "Cantidad", 190, 215, 120);
        dlgCantidad.setText(String.valueOf(productoOriginal.getCantidad()));

        // Fila 4: Fechas
        JTextField dlgFechaCompra = dlgCampo(panel, "Fecha Compra (dd/MM/yyyy)", 20, 290, 220);
        dlgFechaCompra.setText(productoOriginal.getFechaCompra() != null
            ? sdf.format(productoOriginal.getFechaCompra()) : "");

        JTextField dlgFechaCaducidad = dlgCampo(panel, "Fecha Caducidad (dd/MM/yyyy)", 260, 290, 220);
        dlgFechaCaducidad.setText(productoOriginal.getFechaCaducidad() != null
            ? sdf.format(productoOriginal.getFechaCaducidad()) : "");

        // Fila 5: En Stock
        JCheckBox dlgStock = new JCheckBox("En Stock");
        dlgStock.setBounds(20, 365, 150, 28);
        dlgStock.setFont(new Font("Segoe UI", Font.BOLD, 12));
        dlgStock.setBackground(Color.WHITE);
        dlgStock.setForeground(new Color(76, 175, 80));
        dlgStock.setSelected(productoOriginal.getStock() > 0);
        panel.add(dlgStock);

        // Botones
        JButton btnGuardarDlg = new JButton("Guardar Cambios");
        btnGuardarDlg.setBounds(20, 415, 180, 40);
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
        btnCancelarDlg.setBounds(215, 415, 130, 40);
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
            String nombre = dlgProducto.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "⚠️ Ingrese el nombre del producto",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
                dlgProducto.requestFocus();
                return;
            }
            double precio;
            int cantidad;
            try {
                precio = Double.parseDouble(dlgPrecio.getText().trim());
                cantidad = Integer.parseInt(dlgCantidad.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "❌ Precio y Cantidad deben ser números válidos",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date fechaCompra = null, fechaCaducidad = null;
            if (!dlgFechaCompra.getText().trim().isEmpty()) {
                try { fechaCompra = sdf.parse(dlgFechaCompra.getText().trim()); }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "❌ Formato de fecha de compra inválido. Use dd/MM/yyyy",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (!dlgFechaCaducidad.getText().trim().isEmpty()) {
                try { fechaCaducidad = sdf.parse(dlgFechaCaducidad.getText().trim()); }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "❌ Formato de fecha de caducidad inválido. Use dd/MM/yyyy",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Producto productoActualizado = new Producto(
                productoOriginal.getNoSerie(),
                nombre,
                dlgMarca.getSelectedItem().toString(),
                dlgDescripcion.getText().trim(),
                precio,
                cantidad,
                fechaCompra,
                fechaCaducidad,
                dlgStock.isSelected() ? 1 : 0
            );
            if (ProductoDAO.actualizar(productoActualizado, productoOriginal.getNoSerie())) {
                JOptionPane.showMessageDialog(dialog, "✅ Producto actualizado exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                cargarProductosDesdeDB();
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

    private JButton crearBotonGrande(String texto, Color color) {
        JButton btn = new JButton(texto);
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
        txtNoSerie.setText(""); txtProducto.setText(""); txtDescripcion.setText("");
        txtPrecio.setText(""); txtCantidad.setText("");
        txtFechaCompra.setText(""); txtFechaCaducidad.setText("");
        cboMarca.setSelectedIndex(0); chkStock.setSelected(false);
        txtNoSerie.setEditable(true);
        txtNoSerie.requestFocus();
    }

    private void guardarProducto() {
        if (validarCampos()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaCompra = null, fechaCaducidad = null;
                if (!txtFechaCompra.getText().trim().isEmpty()) {
                    try { fechaCompra = sdf.parse(txtFechaCompra.getText().trim()); }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                            "❌ Formato de fecha de compra inválido. Use dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (!txtFechaCaducidad.getText().trim().isEmpty()) {
                    try { fechaCaducidad = sdf.parse(txtFechaCaducidad.getText().trim()); }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                            "❌ Formato de fecha de caducidad inválido. Use dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                Producto producto = new Producto(
                    txtNoSerie.getText().trim(), txtProducto.getText().trim(),
                    cboMarca.getSelectedItem().toString(), txtDescripcion.getText().trim(),
                    Double.parseDouble(txtPrecio.getText().trim()),
                    Integer.parseInt(txtCantidad.getText().trim()),
                    fechaCompra, fechaCaducidad, chkStock.isSelected() ? 1 : 0
                );
                if (ProductoDAO.guardar(producto)) {
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                        "✅ Producto guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarProductosDesdeDB(); limpiarCampos();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "❌ Error en formato de números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarProducto() {
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
            "ℹ️ Seleccione un producto de la tabla haciendo doble clic",
            "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila >= 0) {
            int conf = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this),
                "¿Está seguro de eliminar este producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                Producto producto = listaProductos.get(fila);
                if (ProductoDAO.eliminar(producto.getNoSerie())) {
                    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                        "✅ Producto eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarProductosDesdeDB(); limpiarCampos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Seleccione un producto de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtNoSerie.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese el número de serie", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtNoSerie.requestFocus(); return false;
        }
        if (txtProducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese el nombre del producto", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtProducto.requestFocus(); return false;
        }
        return true;
    }

    private void agregarProductoATabla(Producto p) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String estadoStock = p.getStock() > 0 && p.getCantidad() > 0 ? "✅ Disponible"
                           : p.getCantidad() == 0 ? "❌ Agotado" : "⚠️ Sin Stock";
        modeloTabla.addRow(new Object[]{
            p.getNoSerie(), p.getNombre(), p.getMarca(), p.getDescripcion(),
            "S/. " + String.format("%.2f", p.getPrecio()), p.getCantidad(),
            p.getFechaCompra() != null ? sdf.format(p.getFechaCompra()) : "-",
            p.getFechaCaducidad() != null ? sdf.format(p.getFechaCaducidad()) : "-",
            estadoStock
        });
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Producto p : listaProductos) agregarProductoATabla(p);
    }

    private void cargarProductosDesdeDB() {
        listaProductos = ProductoDAO.obtenerTodos();
        actualizarTabla();
        txtBuscarTexto.setText("");
    }

    private void buscarProductos() {
        String criterio = cboBuscarPor.getSelectedItem().toString();
        ArrayList<Producto> resultados = new ArrayList<>();

        if (criterio.equals("Fecha Compra") || criterio.equals("Fecha Caducidad")) {
            String fechaBusqueda = txtBuscarFecha.getText().trim();
            if (fechaBusqueda.isEmpty()) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "⚠️ Ingrese una fecha para buscar (formato: dd/MM/yyyy)", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try { sdf.parse(fechaBusqueda); }
            catch (Exception e) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "❌ Formato de fecha inválido. Use dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (Producto p : listaProductos) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                boolean coincide = criterio.equals("Fecha Compra") && p.getFechaCompra() != null
                                    && sdf2.format(p.getFechaCompra()).equals(fechaBusqueda)
                                || criterio.equals("Fecha Caducidad") && p.getFechaCaducidad() != null
                                    && sdf2.format(p.getFechaCaducidad()).equals(fechaBusqueda);
                if (coincide) resultados.add(p);
            }
        } else {
            String texto = txtBuscarTexto.getText().trim().toLowerCase();
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "⚠️ Ingrese un texto para buscar", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            for (Producto p : listaProductos) {
                boolean coincide = false;
                switch (criterio) {
                    case "Nombre":      coincide = p.getNombre().toLowerCase().contains(texto); break;
                    case "Marca":       coincide = p.getMarca().toLowerCase().contains(texto); break;
                    case "No. Serie":   coincide = p.getNoSerie().toLowerCase().contains(texto); break;
                    case "Descripción": coincide = p.getDescripcion().toLowerCase().contains(texto); break;
                }
                if (coincide) resultados.add(p);
            }
        }

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "❌ No se encontraron productos", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            modeloTabla.setRowCount(0);
            for (Producto p : resultados) agregarProductoATabla(p);
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "✅ Se encontraron " + resultados.size() + " producto(s)", "Resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarCamposBusqueda() {
        String criterio = cboBuscarPor.getSelectedItem().toString();
        boolean esFecha = criterio.equals("Fecha Compra") || criterio.equals("Fecha Caducidad");
        txtBuscarFecha.setEnabled(esFecha);
        txtBuscarTexto.setEnabled(!esFecha);
        if (esFecha) { txtBuscarTexto.setText(""); txtBuscarFecha.requestFocus(); }
        else { txtBuscarFecha.setText(""); txtBuscarTexto.requestFocus(); }
    }
}
