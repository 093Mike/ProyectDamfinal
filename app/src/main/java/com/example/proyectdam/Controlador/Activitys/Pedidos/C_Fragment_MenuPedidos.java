package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.support.annotation.NonNull;

import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Fragment_MenuPedidos  {
    private DatabaseReference mref;
    private ArrayList<Pedido> pedidos;


    public void initialite(){
        pedidos = new ArrayList<>();
        mref = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("pedidos");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pedido.proximoid = 1000;
                pedidos.clear();
                for (DataSnapshot pedidos_all : dataSnapshot.getChildren()) {
                    int id = Integer.parseInt(pedidos_all.getKey());
                    String[] productos,cantidades;
                    ArrayList<Integer> fproductos,fcantidades;
                    String fecha, nombre;
                    double precio;
                    productos=pedidos_all.child("productos").getValue(String.class).split(",");
                    cantidades=pedidos_all.child("cantidades").getValue(String.class).split(",");
                    fproductos =  new ArrayList<>();
                    fcantidades = new ArrayList<>();
                    for (int i = 0; i < productos.length ; i++){
                        fproductos.add(Integer.parseInt(productos[i]));
                        fcantidades.add(Integer.parseInt(cantidades[i]));
                    }
                    fecha = pedidos_all.child("fecha").getValue(String.class);
                    precio = pedidos_all.child("preciototal").getValue(Double.class);
                    nombre = pedidos_all.child("nombre").getValue(String.class);

                    pedidos.add(new Pedido(id,nombre,fecha,fproductos,fcantidades,precio));
                }
                Fragment_MenuPedidos.getInstance().adaptadorPedidos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
