package com.example.proyectdam.Model;

// TODO: Implementar ubicación del producto --> almacén en el que se encuentra y en qué parte

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;

public class Producto {
    private int id;
    public static int nextId;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private double cantidad;
    private Almacen almacen;
    private String ubicacion;
    private String proveedor;
    private double precioProveedor;
    private double precioPVP;

    public Producto(String nombre, String descripcion, Categoria categoria, double cantidad,  String proveedor,
                     double precioProveedor, double precioPVP) {
        new C_Almacen().siguienteId_producto();
        id = nextId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.precioProveedor = precioProveedor;
        this.precioPVP = precioPVP;
    }

    public Producto(int id, String nombre, String descripcion, Categoria categoria, double cantidad,  String proveedor,
                    double precioProveedor, double precioPVP) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.precioProveedor = precioProveedor;
        this.precioPVP = precioPVP;
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

//    public String getModelo() {
//        return modelo;
//    }
//
//    public void setModelo(String modelo) {
//        this.modelo = modelo;
//    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
