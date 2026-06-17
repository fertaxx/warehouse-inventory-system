package com.empresa.sistema_de_almacen_inventario.modelos;

import java.util.Date;

public class HistorialVenta {
    private int id;
    private String numeroTicket;
    private Date fechaHora;
    private String vendedor;
    private String canalVenta;
    private String descripcionProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double descuentos;
    private double impuestos;
    private double totalNeto;
    private String metodoPago;
    private String nombreCliente;
    private String correoCliente;

    public HistorialVenta() {}

    public HistorialVenta(String numeroTicket, Date fechaHora, String vendedor, String canalVenta,
                          String descripcionProducto, int cantidad, double precioUnitario,
                          double descuentos, double impuestos, String metodoPago,
                          String nombreCliente, String correoCliente) {
        this.numeroTicket = numeroTicket;
        this.fechaHora = fechaHora;
        this.vendedor = vendedor;
        this.canalVenta = canalVenta;
        this.descripcionProducto = descripcionProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = precioUnitario * cantidad;
        this.descuentos = descuentos;
        this.impuestos = impuestos;
        this.totalNeto = subtotal - descuentos + impuestos;
        this.metodoPago = metodoPago;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumeroTicket() { return numeroTicket; }
    public void setNumeroTicket(String numeroTicket) { this.numeroTicket = numeroTicket; }
    public Date getFechaHora() { return fechaHora; }
    public void setFechaHora(Date fechaHora) { this.fechaHora = fechaHora; }
    public String getVendedor() { return vendedor; }
    public void setVendedor(String vendedor) { this.vendedor = vendedor; }
    public String getCanalVenta() { return canalVenta; }
    public void setCanalVenta(String canalVenta) { this.canalVenta = canalVenta; }
    public String getDescripcionProducto() { return descripcionProducto; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getDescuentos() { return descuentos; }
    public void setDescuentos(double descuentos) { this.descuentos = descuentos; }
    public double getImpuestos() { return impuestos; }
    public void setImpuestos(double impuestos) { this.impuestos = impuestos; }
    public double getTotalNeto() { return totalNeto; }
    public void setTotalNeto(double totalNeto) { this.totalNeto = totalNeto; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getCorreoCliente() { return correoCliente; }
    public void setCorreoCliente(String correoCliente) { this.correoCliente = correoCliente; }
}
