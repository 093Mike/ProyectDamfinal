package com.example.proyectdam.Model;

public class Movimientos {
    private int idproducto;
    private String descripcion;
    private int tipo;

    public Movimientos(int idproducto, String descripcion, int tipo) {
        this.idproducto = idproducto;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
