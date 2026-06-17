package com.empresa.sistema_de_almacen_inventario.modelos;

public class Cliente {
    private String identificador;
    private String nombres;
    private String dni;
    private String ruc;
    private String direccion;
    private String localidad;
    
    public Cliente() {
    }
    
    public Cliente(String identificador, String nombres, String dni, String ruc, String direccion, String localidad) {
        this.identificador = identificador;
        this.nombres = nombres;
        this.dni = dni;
        this.ruc = ruc;
        this.direccion = direccion;
        this.localidad = localidad;
    }
    
    // Getters y Setters
    public String getIdentificador() {
        return identificador;
    }
    
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String getRuc() {
        return ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getLocalidad() {
        return localidad;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
