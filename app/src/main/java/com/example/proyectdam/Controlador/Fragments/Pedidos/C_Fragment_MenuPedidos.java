package com.example.proyectdam.Controlador.Fragments.Pedidos;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class C_Fragment_MenuPedidos  {
    private DatabaseReference mref;
    private ArrayList<Pedido> pedidos;
    private boolean[] check ;
    private ArrayList<Pedido> pedidosfiltrados;


    public void initialite(){
        pedidos = new ArrayList<>();
        pedidosfiltrados = new ArrayList<>();
        check = new boolean[]{true,true, true,true,true};
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
                    productos=pedidos_all.child("productos").getValue(String.class).split(",");
                    cantidades=pedidos_all.child("cantidades").getValue(String.class).split(",");
                    fproductos =  new ArrayList<>();
                    fcantidades = new ArrayList<>();
                    for (int i = 0; i < productos.length ; i++){
                        fproductos.add(Integer.parseInt(productos[i]));
                        fcantidades.add(Integer.parseInt(cantidades[i]));
                    }
                    String fecha = pedidos_all.child("fecha").getValue(String.class);
                    Double precio = pedidos_all.child("preciototal").getValue(Double.class);
                    String nombre = pedidos_all.child("nombre").getValue(String.class);
                    int estado = pedidos_all.child("estado").getValue(Integer.class);
                    pedidos.add(new Pedido(id,nombre,fecha,fproductos,fcantidades,precio,estado));
                }
                Collections.sort(pedidos,new Comparator<Pedido>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    public int compare(Pedido o1, Pedido o2) {
                        return Integer.compare(o2.getId(), o1.getId());
                    }
                });
                Fragment_MenuPedidos.getInstance().adaptadorPedidos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void actualizarFiltros(){
        pedidosfiltrados.clear();
        for (int i=0; i < pedidos.size();i++){
            if(check[pedidos.get(i).getEstado()]) {
                pedidosfiltrados.add(pedidos.get(i));
            }
        }
    }


    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Pedido> getPedidosfiltrados() {
        return pedidosfiltrados;
    }

    public void setPedidosfiltrados(ArrayList<Pedido> pedidosfiltrados) {
        this.pedidosfiltrados = pedidosfiltrados;
    }

    public boolean[] getCheck() {
        return check;
    }

    public void setCheck(boolean[] check) {
        this.check = check;
    }
}
