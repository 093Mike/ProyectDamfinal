package com.example.proyectdam.Model;

import java.util.ArrayList;

public class Pedido {
    private int id;
    public static int proximoid = 1000;
    private int id_cliente;
    private String fecharealizado;
    private ArrayList <Prodcuto_en_Pedido> productos;
    private Double precioTotal;
    private int estado;

    public Pedido(int id, int id_cliente, String fecharealizado, ArrayList<Prodcuto_en_Pedido> productos, Double precioTotal,int estado) {
        this.id = proximoid;
        this.id_cliente = id_cliente;
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

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
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
