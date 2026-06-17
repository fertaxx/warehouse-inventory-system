package com.empresa.sistema_de_almacen_inventario.modelos;

import java.util.Date;

public class Producto {
    private String noSerie;
    private String nombre;
    private String marca;
    private String descripcion;
    private double precio;
    private int cantidad;
    private Date fechaCompra;
    private Date fechaCaducidad;
    private int stock;
    
    public Producto() {
    }
    
    public Producto(String noSerie, String nombre, String marca, String descripcion, 
                   double precio, int cantidad, Date fechaCompra, Date fechaCaducidad, int stock) {
        this.noSerie = noSerie;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
        this.fechaCaducidad = fechaCaducidad;
        this.stock = stock;
    }
    
    // Getters y Setters
    public String getNoSerie() {
        return noSerie;
    }
    
    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public Date getFechaCompra() {
        return fechaCompra;
    }
    
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }
    
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
}
