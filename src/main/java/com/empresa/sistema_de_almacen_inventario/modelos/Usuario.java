package com.empresa.sistema_de_almacen_inventario.modelos;

public class Usuario {
    private int id;
    private String identificacion;
    private String nombre;
    private String password;
    private String tipoUsuario;
    private int activo;

    public Usuario() {}

    public Usuario(String identificacion, String nombre, String password, String tipoUsuario) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.activo = 1;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public int getActivo() { return activo; }
    public void setActivo(int activo) { this.activo = activo; }
}
