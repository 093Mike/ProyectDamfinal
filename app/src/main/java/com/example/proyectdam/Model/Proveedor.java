package com.example.proyectdam.Model;

public class Proveedor {
    private String idProveedor;
    private String nombre;
    private String telefonoContacto;
    private String correoContacto;

    public Proveedor(String idProveedor, String nombre, String telefonoContacto, String correoContacto){
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.telefonoContacto = telefonoContacto;
        this.correoContacto = correoContacto;
    }

    public Proveedor(String nombre, String telefonoContacto, String correoContacto){
        this.nombre = nombre;
        this.telefonoContacto = telefonoContacto;
        this.correoContacto = correoContacto;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }
}
