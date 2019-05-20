package com.example.proyectdam.Model;

public class Incidencia {
    private String idIncidencia;
    public static String nextId;
    private String idAlmacen;
    private String idProducto;
    private String cantidad;
    private String motivo;
    private String detalles;

    public Incidencia(String idAlmacen, String idProducto, String cantidad, String motivo, String detalles) {
        idIncidencia = nextId;
        this.idAlmacen = idAlmacen;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.detalles = detalles;
    }

    public Incidencia(String idIncidencia, String idAlmacen, String idProducto, String cantidad, String motivo, String detalles) {
        this.idIncidencia = idIncidencia;
        this.idAlmacen = idAlmacen;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.detalles = detalles;
    }

    public String getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(String idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(String idIncidencia) {
        this.idIncidencia = idIncidencia;
    }
}
