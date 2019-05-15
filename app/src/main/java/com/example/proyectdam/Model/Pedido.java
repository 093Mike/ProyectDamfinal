package com.example.proyectdam.Model;

import java.util.ArrayList;

public class Pedido {
    private int id;
    public static int proximoid = 1000;
    private String nombrecomprador;
    private String fecharealizado;
    private ArrayList<Integer> productos;
    private ArrayList<Integer> cantidades;
    private Double precioTotal;
    private int estado;

    public Pedido(int id, String nombrecomprador, String fecharealizado, ArrayList<Integer> productos, ArrayList<Integer> cantidades, Double precioTotal,int estado) {
        this.id = proximoid;
        this.nombrecomprador = nombrecomprador;
        this.fecharealizado = fecharealizado;
        this.productos = productos;
        this.cantidades = cantidades;
        this.precioTotal = precioTotal;
        this.estado = estado;
        proximoid++;
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

    public String getFecharealizado() {
        return fecharealizado;
    }

    public void setFecharealizado(String fecharealizado) {
        this.fecharealizado = fecharealizado;
    }

    public ArrayList<Integer> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Integer> productos) {
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
