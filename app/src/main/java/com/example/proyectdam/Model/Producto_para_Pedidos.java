package com.example.proyectdam.Model;

public class Producto_para_Pedidos {
    private int id;
    private String nombre;
    private double cantidad;
    private double precioProveedor;
    private double precioPVP;
    private String almacen;

    public Producto_para_Pedidos(int id,String nombre, double cantidad, double precioProveedor, double precioPVP, String almacen) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioProveedor = precioProveedor;
        this.precioPVP = precioPVP;
        this.almacen = almacen;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioProveedor() {
        return precioProveedor;
    }

    public void setPrecioProveedor(double precioProveedor) {
        this.precioProveedor = precioProveedor;
    }

    public double getPrecioPVP() {
        return precioPVP;
    }

    public void setPrecioPVP(double precioPVP) {
        this.precioPVP = precioPVP;
    }

    public String  getAlmacen() { return almacen; }

    public void setAlmacen(String almacen) { this.almacen = almacen; }
}
