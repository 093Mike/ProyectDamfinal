package com.example.proyectdam.Model;

import java.util.ArrayList;

public class Pedido {
    private int id;
    public static int proximoid = 1000;
    private String nombrecomprador;
    private String fecharealizado;
    private ArrayList <Prodcuto_en_Pedido> productos;
    private Double precioTotal;
    private int estado;

    public Pedido(int id, String nombrecomprador, String fecharealizado, ArrayList<Prodcuto_en_Pedido> productos, Double precioTotal,int estado) {
        this.id = proximoid;
        this.nombrecomprador = nombrecomprador;
        this.fecharealizado = fecharealizado;
        this.productos = productos;
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

    public ArrayList<Prodcuto_en_Pedido> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Prodcuto_en_Pedido> productos) {
        this.productos = productos;
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
