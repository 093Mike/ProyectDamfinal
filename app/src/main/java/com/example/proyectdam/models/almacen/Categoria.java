package com.example.proyectdam.models.almacen;

import java.io.Serializable;

public class Categoria implements Serializable {
    private String id;
    private String nombre;

    public Categoria(String nombre){
        this.nombre = nombre;
        id = nombre.substring(0,3).toUpperCase();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
