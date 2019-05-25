package com.example.proyectdam.Model;

public class Prodcuto_en_Pedido {
    int cantidad;
    int id_producto;

    public Prodcuto_en_Pedido(int cantidad, int id_producto){
        this.cantidad = cantidad;
        this.id_producto=id_producto;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}
