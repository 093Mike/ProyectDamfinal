package com.example.proyectdam.Model;

public class Cliente {
    private int id,telefono;
    private String nombre, direccion, ciudad;
    public static int id_proximo = 1000;

    public Cliente(int id, String nombre, int telefono, String direccion, String ciudad) {
        this.id = id_proximo;
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        id_proximo++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
