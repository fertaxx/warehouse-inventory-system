package com.empresa.sistema_de_almacen_inventario.modelos;

import java.util.Date;

public class Venta {
    private int id;
    private String clienteIdentificador;
    private String clienteNombre;
    private String productoNoSerie;
    private String productoNombre;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double igv;
    private double total;
    private Date fechaVenta;

    public Venta() {}

    public Venta(String clienteIdentificador, String clienteNombre,
                 String productoNoSerie, String productoNombre,
                 int cantidad, double precioUnitario, Date fechaVenta) {
        this.clienteIdentificador = clienteIdentificador;
        this.clienteNombre = clienteNombre;
        this.productoNoSerie = productoNoSerie;
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = precioUnitario * cantidad;
        this.igv = this.subtotal * 0.18;
        this.total = this.subtotal + this.igv;
        this.fechaVenta = fechaVenta;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClienteIdentificador() { return clienteIdentificador; }
    public void setClienteIdentificador(String clienteIdentificador) { this.clienteIdentificador = clienteIdentificador; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getProductoNoSerie() { return productoNoSerie; }
    public void setProductoNoSerie(String productoNoSerie) { this.productoNoSerie = productoNoSerie; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getIgv() { return igv; }
    public void setIgv(double igv) { this.igv = igv; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
}
