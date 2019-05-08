package com.example.proyectdam.Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Pedido {
    private int id;
    private static int proximoid = 1000;
    private String nombrecomprador;
    private Calendar fecharealizado;
    private ArrayList<Producto> productos;
    private ArrayList<Integer> cantidades;
    private Double precioTotal;

    public Pedido(int id, String nombrecomprador, Calendar fecharealizado, Double precioTotal) {
        this.id = id;
        this.nombrecomprador = nombrecomprador;
        this.fecharealizado = fecharealizado;
        this.precioTotal = precioTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrecomprador() {
        return nombrecomprador;
    }

    public void setNombrecomprador(String nombrecomprador) {
        this.nombrecomprador = nombrecomprador;
    }

    public Calendar getFecharealizado() {
        return fecharealizado;
    }

    public void setFecharealizado(Calendar fecharealizado) {
        this.fecharealizado = fecharealizado;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(ArrayList<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
