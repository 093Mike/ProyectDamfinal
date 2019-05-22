package com.example.proyectdam.Model;

public class Cliente {
    private int id,num_telefono;
    private String nombre, direccion, ciudad;
    public static int id_proximo = 1000;

    public Cliente(int id, String nombre, int num_telefono, String direccion, String ciudad) {
        this.id = id_proximo;
        this.num_telefono = num_telefono;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        id_proximo = 1000;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_telefono() {
        return num_telefono;
    }

    public void setNum_telefono(int num_telefono) {
        this.num_telefono = num_telefono;
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
