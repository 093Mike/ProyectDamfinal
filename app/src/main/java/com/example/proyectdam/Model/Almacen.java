package com.example.proyectdam.Model;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;

import java.util.ArrayList;

public class Almacen {
    public static int numeroAlmacen;
    private String id;
    private String direccion;
    private ArrayList<String> secciones;
    private ArrayList<String> pasillos;
    private ArrayList<String> estanterias;

    public Almacen(String direccion){
        new C_Almacen().siguienteId_almacen();
        id = "ALM" + String.valueOf(numeroAlmacen);
        this.direccion = direccion;
        secciones = new ArrayList<>();
        pasillos = new ArrayList<>();
        estanterias = new ArrayList<>();
    }

    public Almacen(String id, String direccion){
        this.id = id;
        this.direccion = direccion;
        secciones = new ArrayList<>();
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

    public ArrayList<String> getSecciones() {
        return secciones;
    }

    public void setSecciones(ArrayList<String> secciones) {
        this.secciones = secciones;
    }

    public ArrayList<String> getPasillos() {
        return pasillos;
    }

    public void setPasillos(ArrayList<String> pasillos) {
        this.pasillos = pasillos;
    }

    public ArrayList<String> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(ArrayList<String> estanterias) {
        this.estanterias = estanterias;
    }
}