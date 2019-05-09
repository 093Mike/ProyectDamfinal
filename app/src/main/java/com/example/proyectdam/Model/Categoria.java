package com.example.proyectdam.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable {
    private String id;
    private String nombre;
    private ArrayList<String> idAlmacenes;

    public Categoria(String nombre){
        this.nombre = nombre;
        id = nombre.substring(0,3).toUpperCase();
        idAlmacenes = new ArrayList<>();
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

    public ArrayList<String> getIdAlmacenes() { return idAlmacenes; }

    public void setIdAlmacenes(String idAlmacen) { idAlmacenes.add(idAlmacen); }
}
