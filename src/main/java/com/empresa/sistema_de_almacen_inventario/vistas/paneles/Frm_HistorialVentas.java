package com.empresa.sistema_de_almacen_inventario.vistas.paneles;

import com.empresa.sistema_de_almacen_inventario.database.HistorialVentaDAO;
import com.empresa.sistema_de_almacen_inventario.modelos.HistorialVenta;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Frm_HistorialVentas extends JPanel {

    private ArrayList<HistorialVenta> listaHistorial;
    private ArrayList<HistorialVenta> listaFiltrada;
    private DefaultTableModel modeloTabla;
    private JTable tablaHistorial;
    private JTextField txtBuscar;

    public Frm_HistorialVentas() {
        listaHistorial = new ArrayList<>();
        listaFiltrada  = new ArrayList<>();
        initComponents();
        cargarDesdeDB();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // ── Panel Superior ───────────────────────────────────────────────
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        JLabel lblIcono = new JLabel("📋");
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        panelSuperior.add(lblIcono);

        JPanel panelTitulos = new JPanel();
        panelTitulos.setLayout(new BoxLayout(panelTitulos, BoxLayout.Y_AXIS));
        panelTitulos.setBackground(Color.WHITE);
        JLabel lblTitulo = new JLabel("Historial de Ventas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(33, 33, 33));
        JLabel lblSub = new JLabel("Consulta de transacciones registradas");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(117, 117, 117));
        panelTitulos.add(lblTitulo);
        panelTitulos.add(lblSub);
        panelSuperior.add(panelTitulos);

        add(panelSuperior, BorderLayout.NORTH);

        // ── Contenido principal ──────────────────────────────────────────
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(new Color(245, 245, 245));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── Barra de búsqueda ────────────────────────────────────────────
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        panelBusqueda.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        panelBusqueda.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBuscar.setForeground(new Color(66, 66, 66));
        panelBusqueda.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscar.setPreferredSize(new Dimension(280, 30));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        txtBuscar.setToolTipText("Buscar por cliente o número de ticket");
        panelBusqueda.add(txtBuscar);

        JButton btnBuscar = boton("🔍 Buscar", new Color(33, 150, 243));
        btnBuscar.setPreferredSize(new Dimension(120, 30));
        btnBuscar.addActionListener(e -> filtrarTabla());
        panelBusqueda.add(btnBuscar);

        JButton btnMostrarTodos = boton("📋 Mostrar Todos", new Color(96, 125, 139));
        btnMostrarTodos.setPreferredSize(new Dimension(150, 30));
        btnMostrarTodos.addActionListener(e -> {
            txtBuscar.setText("");
            mostrarEnTabla(listaHistorial);
        });
        panelBusqueda.add(btnMostrarTodos);

        // Permitir buscar con Enter
        txtBuscar.addActionListener(e -> filtrarTabla());

        panelContenido.add(panelBusqueda);
        panelContenido.add(Box.createVerticalStrut(10));

        // ── Tabla ────────────────────────────────────────────────────────
        JPanel cardTabla = new JPanel(new BorderLayout(0, 8));
        cardTabla.setBackground(Color.WHITE);
        cardTabla.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        cardTabla.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTabla = new JLabel("📋 Historial de Ventas  —  doble clic para ver detalle");
        lblTabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTabla.setForeground(new Color(33, 33, 33));
        cardTabla.add(lblTabla, BorderLayout.NORTH);

        String[] cols = {"Folio", "Fecha", "Cliente", "Producto", "Cant.", "Total", "Pago", "Vendedor"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaHistorial = new JTable(modeloTabla);
        tablaHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaHistorial.setRowHeight(30);
        tablaHistorial.setSelectionBackground(new Color(33, 150, 243, 50));
        tablaHistorial.setGridColor(new Color(240, 240, 240));
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaHistorial.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] anchos = {80, 120, 160, 180, 55, 90, 120, 120};
        for (int i = 0; i < anchos.length; i++) {
            tablaHistorial.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        tablaHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaHistorial.getSelectedRow();
                    if (fila >= 0) abrirDetalle(listaFiltrada.get(fila));
                }
            }
        });

        tablaHistorial.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setBackground(new Color(33, 150, 243));
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 12));
                setHorizontalAlignment(CENTER);
            }
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v,
                    boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(t, v, s, f, r, c);
                setText(v != null ? v.toString() : "");
                setBackground(new Color(33, 150, 243));
                setForeground(Color.WHITE);
                setOpaque(true);
                return this;
            }
        });
        tablaHistorial.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollTabla = new JScrollPane(tablaHistorial);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        scrollTabla.setPreferredSize(new Dimension(0, 400));
        cardTabla.add(scrollTabla, BorderLayout.CENTER);
        panelContenido.add(cardTabla);

        JScrollPane scrollPrincipal = new JScrollPane(panelContenido);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPrincipal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // </editor-fold>//GEN-END:initComponents

        add(scrollPrincipal, BorderLayout.CENTER);
    }

    // ── Modal detalle ────────────────────────────────────────────────────

    private void abrirDetalle(HistorialVenta h) {
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Detalle de Venta", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(500, 530);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);
        dialog.setContentPane(p);

        JLabel lblT = new JLabel("🧾 Detalle — Ticket #" + h.getNumeroTicket());
        lblT.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblT.setForeground(new Color(33, 150, 243));
        lblT.setBounds(20, 15, 450, 25);
        p.add(lblT);
        JSeparator sep = new JSeparator();
        sep.setBounds(20, 45, 450, 2);
        p.add(sep);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dlgRO(p, "Fecha",           h.getFechaHora() != null ? sdf.format(h.getFechaHora()) : "-", 20,  55, 210);
        dlgRO(p, "Vendedor",        h.getVendedor(),          250,  55, 210);
        dlgRO(p, "Canal de Venta",  h.getCanalVenta(),         20, 115, 210);
        dlgRO(p, "Método de Pago",  h.getMetodoPago(),        250, 115, 210);
        dlgRO(p, "Producto",        h.getDescripcionProducto(), 20, 175, 440);
        dlgRO(p, "Cantidad",        String.valueOf(h.getCantidad()),                          20, 235, 100);
        dlgRO(p, "Precio Unit.",    "S/. " + String.format("%.2f", h.getPrecioUnitario()),  135, 235, 120);
        dlgRO(p, "SubTotal",        "S/. " + String.format("%.2f", h.getSubtotal()),        270, 235, 110);

        JPanel panelTot = new JPanel(null);
        panelTot.setBackground(new Color(232, 245, 253));
        panelTot.setBounds(20, 295, 450, 70);
        panelTot.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 1));
        p.add(panelTot);
        addLblTot(panelTot, "Descuentos: S/. " + String.format("%.2f", h.getDescuentos()),  10, 8);
        addLblTot(panelTot, "Impuestos: S/. "  + String.format("%.2f", h.getImpuestos()),  200, 8);
        JLabel lblTotal = new JLabel("TOTAL NETO: S/. " + String.format("%.2f", h.getTotalNeto()));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTotal.setForeground(new Color(33, 150, 243));
        lblTotal.setBounds(10, 35, 300, 22);
        panelTot.add(lblTotal);

        dlgRO(p, "Cliente",  h.getNombreCliente(),  20, 375, 210);
        dlgRO(p, "Correo",   h.getCorreoCliente(), 250, 375, 210);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(185, 480, 120, 35);
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
        p.add(btnCerrar);

        dialog.setVisible(true);
    }

    private void dlgRO(JPanel p, String label, String valor, int x, int y, int w) {
        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.BOLD, 11));
        l.setForeground(new Color(66, 66, 66));
        l.setBounds(x, y, w, 18);
        p.add(l);
        JTextField tf = new JTextField(valor);
        tf.setBounds(x, y + 20, w, 30);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setEditable(false);
        tf.setBackground(new Color(245, 245, 245));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        p.add(tf);
    }

    private void addLblTot(JPanel p, String texto, int x, int y) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        l.setBounds(x, y, 200, 20);
        p.add(l);
    }

    // ── Helpers UI ───────────────────────────────────────────────────────

    private JButton boton(String texto, Color color) {
        JButton btn = new JButton(texto);
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

    private void filtrarTabla() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        if (texto.isEmpty()) {
            mostrarEnTabla(listaHistorial);
            return;
        }
        ArrayList<HistorialVenta> resultado = new ArrayList<>();
        for (HistorialVenta h : listaHistorial) {
            String cliente = h.getNombreCliente() != null ? h.getNombreCliente().toLowerCase() : "";
            String ticket  = h.getNumeroTicket()  != null ? h.getNumeroTicket().toLowerCase()  : "";
            if (cliente.contains(texto) || ticket.contains(texto)) {
                resultado.add(h);
            }
        }
        mostrarEnTabla(resultado);
    }

    private void mostrarEnTabla(ArrayList<HistorialVenta> lista) {
        listaFiltrada = lista;
        modeloTabla.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (HistorialVenta h : lista) {
            modeloTabla.addRow(new Object[]{
                h.getNumeroTicket(),
                h.getFechaHora() != null ? sdf.format(h.getFechaHora()) : "-",
                h.getNombreCliente(),
                h.getDescripcionProducto(),
                h.getCantidad(),
                "S/. " + String.format("%.2f", h.getTotalNeto()),
                h.getMetodoPago(),
                h.getVendedor()
            });
        }
    }

    public void cargarDesdeDB() {
        listaHistorial = HistorialVentaDAO.obtenerTodos();
        mostrarEnTabla(listaHistorial);
    }
}
