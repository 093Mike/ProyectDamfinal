package com.example.proyectdam.Model;

public class UserActual {
    private String nombre;
    private String permisos;
    private String tipo_permisos;
    private String uid;

    public UserActual(String nombre, String permisos, String tipo_permisos, String uid) {
        this.nombre = nombre;
        this.permisos = permisos;
        this.tipo_permisos = tipo_permisos;
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getTipo_permisos() {
        return tipo_permisos;
    }

    public void setTipo_permisos(String tipo_permisos) {
        this.tipo_permisos = tipo_permisos;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
