package com.empresa.sistema_de_almacen_inventario.vistas.paneles;

import com.empresa.sistema_de_almacen_inventario.database.ClienteDAO;
import com.empresa.sistema_de_almacen_inventario.database.HistorialVentaDAO;
import com.empresa.sistema_de_almacen_inventario.database.ProductoDAO;
import com.empresa.sistema_de_almacen_inventario.database.VentaDAO;
import com.empresa.sistema_de_almacen_inventario.modelos.Cliente;
import com.empresa.sistema_de_almacen_inventario.modelos.HistorialVenta;
import com.empresa.sistema_de_almacen_inventario.modelos.Producto;
import com.empresa.sistema_de_almacen_inventario.modelos.Venta;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frm_Ventas extends JPanel {

    // Campos cliente
    private JTextField txtDniRuc;
    private JTextField txtNombreCliente;
    private JTextField txtDireccion;
    private JTextField txtCorreoCliente;

    // Campos producto
    private JTextField txtNoSerie;
    private JTextField txtDescripcionProducto;
    private JTextField txtDescripcionAdicional;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtFechaVenta;

    // Datos de la transacción
    private JTextField txtNumeroTicket;
    private JComboBox<String> cboMetodoPago;

    // Totales
    private JTextField txtSubtotal;
    private JTextField txtIgv;
    private JTextField txtNeto;

    // Estado
    private Producto productoSeleccionado;
    private Cliente clienteSeleccionado;
    private HistorialVenta ultimoTicket;

    private static final String[] METODOS_PAGO = {
        "Efectivo", "Tarjeta Débito", "Tarjeta Crédito", "Transferencia", "Yape/Plin"
    };

    public Frm_Ventas() {
        initComponents();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // ── Panel Superior ──────────────────────────────────────────────
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblIcono = new JLabel("🛒");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        panelSuperior.add(lblIcono);

        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
        panelTitulos.setBackground(Color.WHITE);
        JLabel lblTitulo = new JLabel("Sistema de Ventas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(33, 33, 33));
        JLabel lblSub = new JLabel("Registro y gestión de ventas");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(117, 117, 117));
        panelTitulos.add(lblTitulo);
        panelTitulos.add(lblSub);
        panelSuperior.add(panelTitulos);

        add(panelSuperior, BorderLayout.NORTH);

        // ── Contenido principal (scrollable) ────────────────────────────
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(new Color(245, 245, 245));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── Fila de dos cards ────────────────────────────────────────────
        JPanel filaCards = new JPanel(new GridLayout(1, 2, 15, 0));
        filaCards.setBackground(new Color(245, 245, 245));
        filaCards.setMaximumSize(new Dimension(Integer.MAX_VALUE, 280));
        filaCards.setAlignmentX(Component.LEFT_ALIGNMENT);

        // ── Card: Datos del Cliente ──────────────────────────────────────
        JPanel cardCliente = buildCard("Datos del Cliente", new Color(33, 150, 243));
        JPanel innerCliente = (JPanel) cardCliente.getComponent(1);
        innerCliente.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 6, 4, 6);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;

        // Fila DNI/RUC + botón
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
        innerCliente.add(lbl("DNI / RUC"), gc);

        gc.gridy = 1; gc.gridwidth = 1; gc.weightx = 1.0;
        txtDniRuc = campo();
        innerCliente.add(txtDniRuc, gc);
        gc.gridx = 1; gc.weightx = 0.0;
        JButton btnBuscarCliente = boton("🔍 Buscar", new Color(33, 150, 243));
        btnBuscarCliente.setPreferredSize(new Dimension(110, 30));
        btnBuscarCliente.addActionListener(e -> buscarCliente());
        innerCliente.add(btnBuscarCliente, gc);

        gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 2; gc.weightx = 1.0;
        innerCliente.add(lbl("Nombre / Razón Social"), gc);
        gc.gridy = 3;
        txtNombreCliente = campo();
        txtNombreCliente.setEditable(false);
        txtNombreCliente.setBackground(new Color(245, 245, 245));
        innerCliente.add(txtNombreCliente, gc);

        gc.gridy = 4;
        innerCliente.add(lbl("Dirección"), gc);
        gc.gridy = 5;
        txtDireccion = campo();
        txtDireccion.setEditable(false);
        txtDireccion.setBackground(new Color(245, 245, 245));
        innerCliente.add(txtDireccion, gc);

        gc.gridy = 6;
        innerCliente.add(lbl("Correo Electrónico"), gc);
        gc.gridy = 7;
        txtCorreoCliente = campo();
        innerCliente.add(txtCorreoCliente, gc);

        gc.gridy = 8; gc.weighty = 1.0; gc.fill = GridBagConstraints.BOTH;
        innerCliente.add(Box.createVerticalGlue(), gc);

        filaCards.add(cardCliente);

        // ── Card: Datos del Producto ─────────────────────────────────────
        JPanel cardProducto = buildCard("Datos del Producto", new Color(76, 175, 80));
        JPanel innerProducto = (JPanel) cardProducto.getComponent(1);
        innerProducto.setLayout(new GridBagLayout());
        GridBagConstraints gp = new GridBagConstraints();
        gp.insets = new Insets(4, 6, 4, 6);
        gp.fill = GridBagConstraints.HORIZONTAL;
        gp.weightx = 1.0;

        // Fila No.Serie + botón
        gp.gridx = 0; gp.gridy = 0; gp.gridwidth = 2;
        innerProducto.add(lbl("No. Serie"), gp);

        gp.gridy = 1; gp.gridwidth = 1; gp.weightx = 1.0;
        txtNoSerie = campo();
        innerProducto.add(txtNoSerie, gp);
        gp.gridx = 1; gp.weightx = 0.0;
        JButton btnBuscarProducto = boton("🔍 Buscar", new Color(76, 175, 80));
        btnBuscarProducto.setPreferredSize(new Dimension(110, 30));
        btnBuscarProducto.addActionListener(e -> buscarProducto());
        innerProducto.add(btnBuscarProducto, gp);

        gp.gridx = 0; gp.gridy = 2; gp.gridwidth = 4; gp.weightx = 1.0;
        innerProducto.add(lbl("Descripción del Producto"), gp);
        gp.gridy = 3;
        txtDescripcionProducto = campo();
        txtDescripcionProducto.setEditable(false);
        txtDescripcionProducto.setBackground(new Color(245, 245, 245));
        innerProducto.add(txtDescripcionProducto, gp);

        gp.gridy = 4;
        innerProducto.add(lbl("Descripción adicional"), gp);
        gp.gridy = 5;
        txtDescripcionAdicional = campo();
        innerProducto.add(txtDescripcionAdicional, gp);

        // Fila: Cantidad | Precio | Stock | Fecha
        gp.gridwidth = 1; gp.gridy = 6;
        gp.gridx = 0; gp.weightx = 0.5; innerProducto.add(lbl("Cantidad"), gp);
        gp.gridx = 1; gp.weightx = 0.5; innerProducto.add(lbl("Precio Unit."), gp);
        gp.gridx = 2; gp.weightx = 0.5; innerProducto.add(lbl("Stock"), gp);
        gp.gridx = 3; gp.weightx = 0.5; innerProducto.add(lbl("Fecha Venta"), gp);

        gp.gridy = 7;
        gp.gridx = 0;
        txtCantidad = campo();
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) { calcularTotales(); }
        });
        innerProducto.add(txtCantidad, gp);
        gp.gridx = 1;
        txtPrecio = campo();
        txtPrecio.setEditable(false);
        txtPrecio.setBackground(new Color(245, 245, 245));
        innerProducto.add(txtPrecio, gp);
        gp.gridx = 2;
        txtStock = campo();
        txtStock.setEditable(false);
        txtStock.setBackground(new Color(245, 245, 245));
        innerProducto.add(txtStock, gp);
        gp.gridx = 3;
        txtFechaVenta = campo();
        txtFechaVenta.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        innerProducto.add(txtFechaVenta, gp);

        gp.gridy = 8; gp.gridx = 0; gp.gridwidth = 4; gp.weighty = 1.0;
        gp.fill = GridBagConstraints.BOTH;
        innerProducto.add(Box.createVerticalGlue(), gp);

        filaCards.add(cardProducto);
        panelContenido.add(filaCards);
        panelContenido.add(Box.createVerticalStrut(12));

        // ── Card: Datos de la Transacción ────────────────────────────────
        JPanel cardTransaccion = buildCard("Datos de la Transacción", new Color(123, 31, 162));
        JPanel innerTrans = (JPanel) cardTransaccion.getComponent(1);
        innerTrans.setLayout(new GridBagLayout());
        GridBagConstraints gt2 = new GridBagConstraints();
        gt2.insets = new Insets(4, 6, 4, 6);
        gt2.fill = GridBagConstraints.HORIZONTAL;
        gt2.weightx = 1.0;

        gt2.gridx = 0; gt2.gridy = 0; gt2.gridwidth = 1;
        innerTrans.add(lbl("Número de Ticket"), gt2);
        gt2.gridx = 1;
        innerTrans.add(lbl("Método de Pago"), gt2);

        gt2.gridy = 1; gt2.gridx = 0;
        txtNumeroTicket = campo();
        innerTrans.add(txtNumeroTicket, gt2);
        gt2.gridx = 1;
        cboMetodoPago = new JComboBox<>(METODOS_PAGO);
        cboMetodoPago.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cboMetodoPago.setBackground(Color.WHITE);
        cboMetodoPago.setPreferredSize(new Dimension(0, 30));
        innerTrans.add(cboMetodoPago, gt2);

        gt2.gridy = 2; gt2.gridx = 0; gt2.gridwidth = 2; gt2.weighty = 1.0;
        gt2.fill = GridBagConstraints.BOTH;
        innerTrans.add(Box.createVerticalGlue(), gt2);

        cardTransaccion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        cardTransaccion.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(cardTransaccion);
        panelContenido.add(Box.createVerticalStrut(12));

        // ── Barra de totales + botones ───────────────────────────────────
        JPanel panelAcciones = new JPanel(new BorderLayout(10, 0));
        panelAcciones.setBackground(Color.WHITE);
        panelAcciones.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        panelAcciones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        panelAcciones.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Botones a la izquierda
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelBotones.setBackground(Color.WHITE);
        JButton btnNuevo     = boton("➕ Nueva Venta",    new Color(96, 125, 139));
        JButton btnRegistrar = boton("💾 Registrar Venta", new Color(76, 175, 80));
        JButton btnAnular    = boton("🗑 Anular",          new Color(244, 67, 54));
        JButton btnTicket    = boton("🎫 Emitir Ticket",   new Color(123, 31, 162));
        btnNuevo.setPreferredSize(new Dimension(150, 36));
        btnRegistrar.setPreferredSize(new Dimension(165, 36));
        btnAnular.setPreferredSize(new Dimension(120, 36));
        btnTicket.setPreferredSize(new Dimension(150, 36));
        btnNuevo.addActionListener(e -> limpiarCampos());
        btnRegistrar.addActionListener(e -> registrarVenta());
        btnAnular.addActionListener(e -> anularVenta());
        btnTicket.addActionListener(e -> emitirTicket());
        panelBotones.add(btnNuevo);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAnular);
        panelBotones.add(btnTicket);
        panelAcciones.add(panelBotones, BorderLayout.WEST);

        // Totales a la derecha
        JPanel panelTotales = new JPanel(new GridBagLayout());
        panelTotales.setBackground(Color.WHITE);
        GridBagConstraints gt = new GridBagConstraints();
        gt.insets = new Insets(2, 6, 2, 6);
        gt.fill = GridBagConstraints.HORIZONTAL;

        gt.gridx = 0; gt.gridy = 0; gt.weightx = 0.0;
        JLabel lblSubLbl = new JLabel("SubTotal:");
        lblSubLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelTotales.add(lblSubLbl, gt);
        gt.gridx = 1; gt.weightx = 1.0;
        txtSubtotal = campoTotal();
        panelTotales.add(txtSubtotal, gt);

        gt.gridx = 2; gt.weightx = 0.0;
        JLabel lblIgvLbl = new JLabel("IGV (18%):");
        lblIgvLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelTotales.add(lblIgvLbl, gt);
        gt.gridx = 3; gt.weightx = 1.0;
        txtIgv = campoTotal();
        panelTotales.add(txtIgv, gt);

        gt.gridx = 4; gt.weightx = 0.0;
        JLabel lblNetoLbl = new JLabel("Neto a Pagar:");
        lblNetoLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNetoLbl.setForeground(new Color(76, 175, 80));
        panelTotales.add(lblNetoLbl, gt);
        gt.gridx = 5; gt.weightx = 1.5;
        txtNeto = campoTotal();
        txtNeto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtNeto.setForeground(new Color(76, 175, 80));
        panelTotales.add(txtNeto, gt);

        panelAcciones.add(panelTotales, BorderLayout.CENTER);
        panelContenido.add(panelAcciones);

        // ── Scroll principal ─────────────────────────────────────────────
        JScrollPane scrollPrincipal = new JScrollPane(panelContenido);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPrincipal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPrincipal, BorderLayout.CENTER);
        // </editor-fold>//GEN-END:initComponents
    }

    // ── Emitir Ticket ────────────────────────────────────────────────────

    private void emitirTicket() {
        if (ultimoTicket == null) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ No hay ninguna venta registrada en esta sesión.\nRegistre una venta primero.",
                "Sin ticket", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "🎫 Ticket de Venta", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(460, 470);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dialog.setContentPane(panel);

        JLabel lblTitulo = new JLabel("🎫 Ticket de Venta");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(123, 31, 162));
        lblTitulo.setBounds(20, 15, 400, 28);
        panel.add(lblTitulo);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 50, 410, 2);
        panel.add(sep);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ticketCampo(panel, "Número de Ticket",  ultimoTicket.getNumeroTicket(),                                    20,  60, 200);
        ticketCampo(panel, "Fecha / Hora",       ultimoTicket.getFechaHora() != null ? sdf.format(ultimoTicket.getFechaHora()) : "-", 240, 60, 180);
        ticketCampo(panel, "Cliente",            ultimoTicket.getNombreCliente(),                                  20, 120, 200);
        ticketCampo(panel, "Correo",             ultimoTicket.getCorreoCliente(),                                 240, 120, 180);
        ticketCampo(panel, "Producto",           ultimoTicket.getDescripcionProducto(),                            20, 180, 410);
        ticketCampo(panel, "Cantidad",           String.valueOf(ultimoTicket.getCantidad()),                       20, 240, 100);
        ticketCampo(panel, "Precio Unit.",       "S/. " + String.format("%.2f", ultimoTicket.getPrecioUnitario()), 135, 240, 120);
        ticketCampo(panel, "Método de Pago",     ultimoTicket.getMetodoPago(),                                    270, 240, 160);

        JPanel panelTotal = new JPanel(null);
        panelTotal.setBackground(new Color(243, 229, 245));
        panelTotal.setBounds(20, 300, 410, 50);
        panelTotal.setBorder(BorderFactory.createLineBorder(new Color(123, 31, 162), 1));
        panel.add(panelTotal);

        JLabel lblTotal = new JLabel("TOTAL NETO: S/. " + String.format("%.2f", ultimoTicket.getTotalNeto()));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setForeground(new Color(123, 31, 162));
        lblTotal.setBounds(10, 12, 380, 24);
        panelTotal.add(lblTotal);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(165, 415, 120, 36);
        btnCerrar.setBackground(Color.WHITE);
        btnCerrar.setForeground(Color.BLACK);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCerrar.setFocusPainted(false);
        btnCerrar.setBorder(BorderFactory.createLineBorder(new Color(96, 125, 139), 2));
        btnCerrar.setOpaque(true);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnCerrar.setBackground(new Color(96, 125, 139));
                btnCerrar.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnCerrar.setBackground(Color.WHITE);
                btnCerrar.setForeground(Color.BLACK);
            }
        });
        btnCerrar.addActionListener(e -> dialog.dispose());
        panel.add(btnCerrar);

        dialog.setVisible(true);
    }

    private void ticketCampo(JPanel panel, String label, String valor, int x, int y, int width) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(new Color(66, 66, 66));
        lbl.setBounds(x, y, width, 18);
        panel.add(lbl);
        JTextField tf = new JTextField(valor);
        tf.setBounds(x, y + 20, width, 28);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setEditable(false);
        tf.setBackground(new Color(245, 245, 245));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(3, 7, 3, 7)));
        panel.add(tf);
    }

    // ── Helpers de UI ────────────────────────────────────────────────────

    private JPanel buildCard(String titulo, Color color) {
        JPanel outer = new JPanel(new BorderLayout(0, 6));
        outer.setBackground(Color.WHITE);
        outer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(color);
        outer.add(lbl, BorderLayout.NORTH);

        JPanel inner = new JPanel();
        inner.setBackground(Color.WHITE);
        outer.add(inner, BorderLayout.CENTER);
        return outer;
    }

    private JLabel lbl(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", Font.BOLD, 11));
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

    private JTextField campoTotal() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setEditable(false);
        tf.setBackground(new Color(245, 245, 245));
        tf.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        tf.setPreferredSize(new Dimension(110, 28));
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
        btn.setPreferredSize(new Dimension(140, 36));
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

    // ── Lógica de negocio ────────────────────────────────────────────────

    private void buscarCliente() {
        String texto = txtDniRuc.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese un DNI o RUC para buscar", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Cliente c = ClienteDAO.buscarPorIdentificador(texto);
        if (c == null) {
            for (Cliente cl : ClienteDAO.obtenerTodos()) {
                if (texto.equals(cl.getDni()) || texto.equals(cl.getRuc())) {
                    c = cl; break;
                }
            }
        }
        if (c != null) {
            clienteSeleccionado = c;
            txtNombreCliente.setText(c.getNombres());
            txtDireccion.setText(c.getDireccion());
        } else {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "❌ No se encontró cliente con ese DNI/RUC", "Sin resultados", JOptionPane.WARNING_MESSAGE);
            clienteSeleccionado = null;
            txtNombreCliente.setText("");
            txtDireccion.setText("");
        }
    }

    private void buscarProducto() {
        String serie = txtNoSerie.getText().trim();
        if (serie.isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese el No. de Serie del producto", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Producto p = ProductoDAO.buscarPorNoSerie(serie);
        if (p != null) {
            productoSeleccionado = p;
            txtDescripcionProducto.setText(p.getNombre() + " - " + p.getMarca());
            txtPrecio.setText(String.format("%.2f", p.getPrecio()));
            txtStock.setText(String.valueOf(p.getCantidad()));
            calcularTotales();
        } else {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "❌ No se encontró producto con ese No. de Serie", "Sin resultados", JOptionPane.WARNING_MESSAGE);
            productoSeleccionado = null;
            txtDescripcionProducto.setText("");
            txtPrecio.setText("");
            txtStock.setText("");
            limpiarTotales();
        }
    }

    private void calcularTotales() {
        try {
            if (productoSeleccionado == null || txtCantidad.getText().trim().isEmpty()) {
                limpiarTotales(); return;
            }
            int cant = Integer.parseInt(txtCantidad.getText().trim());
            double precio = productoSeleccionado.getPrecio();
            double sub  = precio * cant;
            double igv  = sub * 0.18;
            double neto = sub + igv;
            txtSubtotal.setText("S/. " + String.format("%.2f", sub));
            txtIgv.setText("S/. " + String.format("%.2f", igv));
            txtNeto.setText("S/. " + String.format("%.2f", neto));
        } catch (NumberFormatException ex) {
            limpiarTotales();
        }
    }

    private void limpiarTotales() {
        txtSubtotal.setText(""); txtIgv.setText(""); txtNeto.setText("");
    }

    private void registrarVenta() {
        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Busque y seleccione un cliente", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Busque y seleccione un producto", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtCantidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "⚠️ Ingrese la cantidad", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtCantidad.requestFocus(); return;
        }

        int cant;
        try {
            cant = Integer.parseInt(txtCantidad.getText().trim());
            if (cant <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "❌ Cantidad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cant > productoSeleccionado.getCantidad()) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "❌ Stock insuficiente. Disponible: " + productoSeleccionado.getCantidad(),
                "Sin stock", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date fechaVenta;
        try {
            fechaVenta = new SimpleDateFormat("dd/MM/yyyy").parse(txtFechaVenta.getText().trim());
        } catch (Exception e) {
            fechaVenta = new Date();
        }

        Venta venta = new Venta(
            clienteSeleccionado.getIdentificador(),
            clienteSeleccionado.getNombres(),
            productoSeleccionado.getNoSerie(),
            productoSeleccionado.getNombre(),
            cant,
            productoSeleccionado.getPrecio(),
            fechaVenta
        );

        if (VentaDAO.guardar(venta)) {
            productoSeleccionado.setCantidad(productoSeleccionado.getCantidad() - cant);
            ProductoDAO.actualizar(productoSeleccionado, productoSeleccionado.getNoSerie());

            // Construir descripción completa del producto
            String descProducto = productoSeleccionado.getNombre() + " - " + productoSeleccionado.getMarca();
            String descAdicional = txtDescripcionAdicional.getText().trim();
            if (!descAdicional.isEmpty()) {
                descProducto = descProducto + " | " + descAdicional;
            }

            // Número de ticket (puede estar vacío, se genera uno automático si no se ingresó)
            String numTicket = txtNumeroTicket.getText().trim();
            if (numTicket.isEmpty()) {
                numTicket = "TKT-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            }

            String metodoPago = cboMetodoPago.getSelectedItem() != null
                ? cboMetodoPago.getSelectedItem().toString() : "Efectivo";
            String correo = txtCorreoCliente.getText().trim();

            // Guardar en historial
            HistorialVenta historial = new HistorialVenta(
                numTicket,
                new Date(),
                "Admin",
                "Mostrador",
                descProducto,
                cant,
                productoSeleccionado.getPrecio(),
                0.0,
                venta.getIgv(),
                metodoPago,
                clienteSeleccionado.getNombres(),
                correo
            );
            HistorialVentaDAO.guardar(historial);
            ultimoTicket = historial;

            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "✅ Venta registrada exitosamente\nTotal: S/. " + String.format("%.2f", venta.getTotal()),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        }
    }

    private void anularVenta() {
        int conf = JOptionPane.showConfirmDialog(SwingUtilities.getWindowAncestor(this),
            "¿Está seguro de anular la última venta registrada en esta sesión?",
            "Confirmar anulación", JOptionPane.YES_NO_OPTION);
        if (conf == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                "ℹ️ Para anular ventas específicas, consulte el Historial de Ventas.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtDniRuc.setText(""); txtNombreCliente.setText(""); txtDireccion.setText("");
        txtCorreoCliente.setText("");
        txtNoSerie.setText(""); txtDescripcionProducto.setText(""); txtDescripcionAdicional.setText("");
        txtCantidad.setText(""); txtPrecio.setText(""); txtStock.setText("");
        txtFechaVenta.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        txtNumeroTicket.setText("");
        if (cboMetodoPago.getItemCount() > 0) cboMetodoPago.setSelectedIndex(0);
        clienteSeleccionado = null; productoSeleccionado = null;
        limpiarTotales();
    }
}
