package com.example.proyectdam.Model;

import java.util.ArrayList;

public class Almacen {
    public static int numeroAlmacen;
    private String id;
    private String direccion;
    private ArrayList<String> pasillos;
    private ArrayList<String> estanterias;

    public Almacen(String direccion){
        id = "ALM" + String.valueOf(numeroAlmacen);
        this.direccion = direccion;
        pasillos = new ArrayList<>();
        estanterias = new ArrayList<>();
    }

    public Almacen(String id, String direccion){
        this.id = id;
        this.direccion = direccion;
        pasillos = new ArrayList<>();
        estanterias = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<String> getPasillos() {
        return pasillos;
    }

    public void setPasillos(String pasillo) { pasillos.add(pasillo); }

    public ArrayList<String> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(String estanteria) { estanterias.add(estanteria); }
}