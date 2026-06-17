package com.empresa.sistema_de_almacen_inventario.modelos;

public class Inventario {
    private int id;
    private String codigoSku;
    private String nombreProducto;
    private String categoria;
    private String proveedor;
    private int stockInicial;
    private int entradas;
    private int salidas;
    private int devoluciones;
    private int stockFinal;
    private double costoUnitario;
    private double precioVenta;
    private double valorInventario;
    private int puntoReorden;
    private String estado; // Disponible, Agotado, Pedido en camino

    public Inventario() {}

    public Inventario(String codigoSku, String nombreProducto, String categoria, String proveedor,
                      int stockInicial, int entradas, int salidas, int devoluciones,
                      double costoUnitario, double precioVenta, int puntoReorden, String estado) {
        this.codigoSku = codigoSku;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.stockInicial = stockInicial;
        this.entradas = entradas;
        this.salidas = salidas;
        this.devoluciones = devoluciones;
        this.stockFinal = stockInicial + entradas - salidas + devoluciones;
        this.costoUnitario = costoUnitario;
        this.precioVenta = precioVenta;
        this.valorInventario = this.stockFinal * costoUnitario;
        this.puntoReorden = puntoReorden;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigoSku() { return codigoSku; }
    public void setCodigoSku(String codigoSku) { this.codigoSku = codigoSku; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public int getStockInicial() { return stockInicial; }
    public void setStockInicial(int stockInicial) { this.stockInicial = stockInicial; }

    public int getEntradas() { return entradas; }
    public void setEntradas(int entradas) { this.entradas = entradas; }

    public int getSalidas() { return salidas; }
    public void setSalidas(int salidas) { this.salidas = salidas; }

    public int getDevoluciones() { return devoluciones; }
    public void setDevoluciones(int devoluciones) { this.devoluciones = devoluciones; }

    public int getStockFinal() { return stockFinal; }
    public void setStockFinal(int stockFinal) { this.stockFinal = stockFinal; }

    public double getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(double costoUnitario) { this.costoUnitario = costoUnitario; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public double getValorInventario() { return valorInventario; }
    public void setValorInventario(double valorInventario) { this.valorInventario = valorInventario; }

    public int getPuntoReorden() { return puntoReorden; }
    public void setPuntoReorden(int puntoReorden) { this.puntoReorden = puntoReorden; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /** Recalcula stockFinal y valorInventario automáticamente */
    public void recalcular() {
        this.stockFinal = stockInicial + entradas - salidas + devoluciones;
        this.valorInventario = this.stockFinal * costoUnitario;
    }
}
