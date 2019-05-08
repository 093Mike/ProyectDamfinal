package com.example.proyectdam.Controlador.Activitys.Pedidos;

import com.example.proyectdam.Model.Pedido;

import java.util.ArrayList;

public class C_Fragment_MenuPedidos  {
    ArrayList<Pedido> pedidos = new ArrayList<>();

    public void initialite(){
        pedidos.add(new Pedido(1000,"Anonimo",null,15.45));
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
