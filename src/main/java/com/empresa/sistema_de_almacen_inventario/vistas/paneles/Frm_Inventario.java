package com.empresa.sistema_de_almacen_inventario.vistas.paneles;

import com.empresa.sistema_de_almacen_inventario.database.ProductoDAO;
import com.empresa.sistema_de_almacen_inventario.modelos.Producto;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Frm_Inventario extends JPanel {

    private ArrayList<Producto> listaProductos;
    private DefaultTableModel modeloTabla;
    private JTable tablaInventario;
    private JTextField txtBuscar;
    private JLabel lblTotalProductos;
    private JLabel lblTotalStock;
    private JLabel lblValorTotal;

    public Frm_Inventario() {
        listaProductos = new ArrayList<>();
        initComponents();
        cargarDesdeDB();
    }

    private void initComponents() {
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(new Color(245, 245, 245));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // ── Tarjetas de resumen ──────────────────────────────────────────
        JPanel filaResumen = new JPanel(new GridLayout(1, 3, 15, 0));
        filaResumen.setBackground(new Color(245, 245, 245));
        filaResumen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        filaResumen.setAlignmentX(Component.LEFT_ALIGNMENT);

        filaResumen.add(tarjetaResumen("📦 Total Productos", lblTotalProductos = new JLabel("0"), new Color(33, 150, 243)));
        filaResumen.add(tarjetaResumen("📊 Unidades en Stock", lblTotalStock = new JLabel("0"), new Color(76, 175, 80)));
        filaResumen.add(tarjetaResumen("💰 Valor del Inventario", lblValorTotal = new JLabel("S/. 0.00"), new Color(255, 152, 0)));

        panelContenido.add(filaResumen);
        panelContenido.add(Box.createVerticalStrut(12));

        // ── Barra de búsqueda + botón actualizar ─────────────────────────
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
        panelBusqueda.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        panelBusqueda.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBuscar.setForeground(new Color(66, 66, 66));
        panelBusqueda.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscar.setPreferredSize(new Dimension(260, 30));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        txtBuscar.setToolTipText("Buscar por nombre, marca o No. Serie");
        txtBuscar.addActionListener(e -> filtrar());
        panelBusqueda.add(txtBuscar);

        JButton btnBuscar = boton("🔍 Buscar", new Color(33, 150, 243));
        btnBuscar.setPreferredSize(new Dimension(110, 30));
        btnBuscar.addActionListener(e -> filtrar());
        panelBusqueda.add(btnBuscar);

        JButton btnTodos = boton("📋 Mostrar Todos", new Color(96, 125, 139));
        btnTodos.setPreferredSize(new Dimension(145, 30));
        btnTodos.addActionListener(e -> { txtBuscar.setText(""); mostrarEnTabla(listaProductos); });
        panelBusqueda.add(btnTodos);

        JButton btnActualizar = boton("🔄 Actualizar", new Color(0, 150, 136));
        btnActualizar.setPreferredSize(new Dimension(120, 30));
        btnActualizar.addActionListener(e -> cargarDesdeDB());
        panelBusqueda.add(btnActualizar);

        panelContenido.add(panelBusqueda);
        panelContenido.add(Box.createVerticalStrut(10));

        // ── Tabla de inventario (solo lectura) ───────────────────────────
        JPanel cardTabla = new JPanel(new BorderLayout(0, 8));
        cardTabla.setBackground(Color.WHITE);
        cardTabla.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        cardTabla.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTabla = new JLabel("📋 Stock Actual de Productos  —  doble clic para ver detalle");
        lblTabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTabla.setForeground(new Color(33, 33, 33));
        cardTabla.add(lblTabla, BorderLayout.NORTH);

        String[] cols = {"No. Serie", "Producto", "Marca", "Descripción",
                         "Precio Unit.", "Stock Actual", "F. Compra", "F. Caducidad", "Estado"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaInventario = new JTable(modeloTabla);
        tablaInventario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaInventario.setRowHeight(32);
        tablaInventario.setSelectionBackground(new Color(33, 150, 243, 50));
        tablaInventario.setSelectionForeground(new Color(33, 33, 33));
        tablaInventario.setGridColor(new Color(240, 240, 240));
        tablaInventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaInventario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] anchos = {90, 180, 100, 200, 90, 90, 100, 110, 100};
        for (int i = 0; i < anchos.length; i++)
            tablaInventario.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        // Colorear filas según stock
        tablaInventario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    Object stockVal = table.getModel().getValueAt(row, 5);
                    int stock = 0;
                    try { stock = Integer.parseInt(stockVal.toString()); } catch (Exception ex) {}
                    if (stock == 0) c.setBackground(new Color(255, 235, 238));      // rojo claro
                    else if (stock <= 3) c.setBackground(new Color(255, 248, 225)); // amarillo claro
                    else c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaInventario.getSelectedRow();
                    if (fila >= 0) abrirDetalle(listaProductos.get(fila));
                }
            }
        });

        tablaInventario.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            { setOpaque(true); setBackground(new Color(33,150,243)); setForeground(Color.WHITE);
              setFont(new Font("Segoe UI", Font.BOLD, 12)); setHorizontalAlignment(CENTER); }
            @Override
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(t, v, s, f, r, c);
                setText(v != null ? v.toString() : "");
                setBackground(new Color(33,150,243)); setForeground(Color.WHITE); setOpaque(true);
                return this;
            }
        });
        tablaInventario.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollTabla = new JScrollPane(tablaInventario);
        scrollTabla.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        scrollTabla.setPreferredSize(new Dimension(0, 450));
        cardTabla.add(scrollTabla, BorderLayout.CENTER);

        // Leyenda de colores
        JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 4));
        leyenda.setBackground(Color.WHITE);
        leyenda.add(leyendaItem(new Color(255, 235, 238), "Sin stock (0 unidades)"));
        leyenda.add(leyendaItem(new Color(255, 248, 225), "Stock bajo (1-3 unidades)"));
        leyenda.add(leyendaItem(Color.WHITE, "Stock normal (4+ unidades)"));
        cardTabla.add(leyenda, BorderLayout.SOUTH);

        panelContenido.add(cardTabla);

        JScrollPane scrollPrincipal = new JScrollPane(panelContenido);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.getHorizontalScrollBar().setUnitIncrement(16);
        // </editor-fold>//GEN-END:initComponents

        add(scrollPrincipal, BorderLayout.CENTER);
    }

    // ── Modal detalle ────────────────────────────────────────────────────

    private void abrirDetalle(Producto p) {
        Window parent = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parent, "Detalle de Producto", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(480, 400);
        dialog.setLocationRelativeTo(parent);
        dialog.setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        dialog.setContentPane(panel);

        JLabel lblT = new JLabel("📦 " + p.getNombre());
        lblT.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblT.setForeground(new Color(33, 150, 243));
        lblT.setBounds(20, 15, 430, 25);
        panel.add(lblT);
        JSeparator sep = new JSeparator(); sep.setBounds(20, 45, 430, 2); panel.add(sep);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ro(panel, "No. Serie",    p.getNoSerie(),                                    20,  55, 200);
        ro(panel, "Marca",        p.getMarca(),                                     240,  55, 200);
        ro(panel, "Descripción",  p.getDescripcion(),                                20, 115, 420);
        ro(panel, "Precio",       "S/. " + String.format("%.2f", p.getPrecio()),    20, 175, 150);
        ro(panel, "Stock Actual", String.valueOf(p.getCantidad()),                  190, 175, 100);
        ro(panel, "F. Compra",    p.getFechaCompra() != null ? sdf.format(p.getFechaCompra()) : "-",     310, 175, 130);
        ro(panel, "F. Caducidad", p.getFechaCaducidad() != null ? sdf.format(p.getFechaCaducidad()) : "-", 20, 235, 130);

        // Estado visual
        String estado = p.getCantidad() == 0 ? "❌ Sin Stock"
                      : p.getCantidad() <= 3  ? "⚠️ Stock Bajo"
                      : "✅ Disponible";
        JLabel lblEstado = new JLabel(estado);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEstado.setForeground(p.getCantidad() == 0 ? new Color(244,67,54)
                              : p.getCantidad() <= 3  ? new Color(255,152,0)
                              : new Color(76,175,80));
        lblEstado.setBounds(170, 250, 200, 25);
        panel.add(lblEstado);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(175, 330, 120, 36);
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

    private void ro(JPanel p, String label, String valor, int x, int y, int w) {
        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.BOLD, 11));
        l.setForeground(new Color(66, 66, 66));
        l.setBounds(x, y, w, 18); p.add(l);
        JTextField tf = new JTextField(valor);
        tf.setBounds(x, y + 20, w, 30);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tf.setEditable(false); tf.setBackground(new Color(245, 245, 245));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        p.add(tf);
    }

    // ── Helpers UI ───────────────────────────────────────────────────────

    private JPanel tarjetaResumen(String titulo, JLabel lblValor, Color color) {
        JPanel card = new JPanel(new BorderLayout(0, 4));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 4, 0, 0, color),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTit.setForeground(new Color(117, 117, 117));
        card.add(lblTit, BorderLayout.NORTH);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblValor.setForeground(color);
        card.add(lblValor, BorderLayout.CENTER);
        return card;
    }

    private JPanel leyendaItem(Color color, String texto) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        p.setBackground(Color.WHITE);
        JPanel cuadro = new JPanel();
        cuadro.setBackground(color);
        cuadro.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        cuadro.setPreferredSize(new Dimension(16, 16));
        p.add(cuadro);
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(new Color(66, 66, 66));
        p.add(lbl);
        return p;
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

    private void filtrar() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        if (texto.isEmpty()) { mostrarEnTabla(listaProductos); return; }
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto p : listaProductos) {
            if (p.getNombre().toLowerCase().contains(texto)
             || p.getMarca().toLowerCase().contains(texto)
             || p.getNoSerie().toLowerCase().contains(texto)) {
                resultado.add(p);
            }
        }
        mostrarEnTabla(resultado);
    }

    private void mostrarEnTabla(ArrayList<Producto> lista) {
        modeloTabla.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Producto p : lista) {
            String estado = p.getCantidad() == 0 ? "❌ Sin Stock"
                          : p.getCantidad() <= 3  ? "⚠️ Stock Bajo"
                          : "✅ Disponible";
            modeloTabla.addRow(new Object[]{
                p.getNoSerie(), p.getNombre(), p.getMarca(), p.getDescripcion(),
                "S/. " + String.format("%.2f", p.getPrecio()),
                p.getCantidad(),
                p.getFechaCompra()    != null ? sdf.format(p.getFechaCompra())    : "-",
                p.getFechaCaducidad() != null ? sdf.format(p.getFechaCaducidad()) : "-",
                estado
            });
        }
    }

    private void cargarDesdeDB() {
        listaProductos = ProductoDAO.obtenerTodos();
        mostrarEnTabla(listaProductos);

        // Actualizar tarjetas de resumen
        int totalStock = 0;
        double valorTotal = 0;
        for (Producto p : listaProductos) {
            totalStock += p.getCantidad();
            valorTotal += p.getCantidad() * p.getPrecio();
        }
        lblTotalProductos.setText(String.valueOf(listaProductos.size()));
        lblTotalStock.setText(String.valueOf(totalStock));
        lblValorTotal.setText("S/. " + String.format("%.2f", valorTotal));
    }
}
