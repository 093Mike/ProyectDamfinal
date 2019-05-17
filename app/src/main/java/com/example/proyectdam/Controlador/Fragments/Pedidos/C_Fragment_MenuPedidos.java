package com.example.proyectdam.Controlador.Fragments.Pedidos;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.Model.Prodcuto_en_Pedido;
import com.example.proyectdam.Model.Producto_para_Pedidos;
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
    private DatabaseReference mref,mref2;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Producto_para_Pedidos> all_prodcutos;
    private boolean[] check ;
    private ArrayList<Pedido> pedidosfiltrados;
    private int pedidoselecionado;


    public void initialite(){
        pedidos = new ArrayList<>();
        all_prodcutos = new ArrayList<>();

        pedidosfiltrados = new ArrayList<>();
        check = new boolean[]{true,true, true,true,true};
        mref = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("pedidos");
        mref2 = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("productos");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pedido.proximoid = 1000;
                pedidos.clear();
                for (DataSnapshot pedidos_all : dataSnapshot.getChildren()) {
                    int id = Integer.parseInt(pedidos_all.getKey());
                    String[] productos,cantidades;
                    ArrayList<Prodcuto_en_Pedido> fproductos;
                    fproductos =  new ArrayList<>();
                    productos=pedidos_all.child("productos").getValue(String.class).split(",");
                    cantidades=pedidos_all.child("cantidades").getValue(String.class).split(",");

                    for (int i = 0; i < productos.length ; i++){
                        fproductos.add(new Prodcuto_en_Pedido(Integer.parseInt(cantidades[i]),Integer.parseInt(productos[i])));
                    }
                    String fecha = pedidos_all.child("fecha").getValue(String.class);
                    Double precio = pedidos_all.child("preciototal").getValue(Double.class);
                    String nombre = pedidos_all.child("nombre").getValue(String.class);
                    int estado = pedidos_all.child("estado").getValue(Integer.class);

                    pedidos.add(new Pedido(id,nombre,fecha,fproductos,precio,estado));
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
        mref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                all_prodcutos.clear();
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    all_prodcutos.add(new Producto_para_Pedidos(
                            Integer.parseInt(producto.child("id").getValue(String.class)),
                            producto.child("nombre").getValue(String.class),
                            producto.child("cantidad").getValue(Double.class),
                            producto.child("precioProveedor").getValue(Double.class),
                            producto.child("precioPVP").getValue(Double.class)
                    ));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void actualizarProductos() {
        mref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                all_prodcutos.clear();
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    all_prodcutos.add(new Producto_para_Pedidos(
                            Integer.parseInt(producto.child("id").getValue(String.class)),
                            producto.child("nombre").getValue(String.class),
                            producto.child("cantidad").getValue(Double.class),
                            producto.child("precioProveedor").getValue(Double.class),
                            producto.child("precioPVP").getValue(Double.class)
                    ));
                }
                mref2.removeEventListener(this);
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

    public int getPedidoselecionado() {
        return pedidoselecionado;
    }

    public void setPedidoselecionado(int pedidoselecionado) {
        for (int i = 0 ; i < pedidos.size();i++){
            if(pedidoselecionado == pedidos.get(i).getId()){
                this.pedidoselecionado = i;
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

    public ArrayList<Producto_para_Pedidos> getAll_prodcutos() {
        return all_prodcutos;
    }

    public void setAll_prodcutos(ArrayList<Producto_para_Pedidos> all_prodcutos) {
        this.all_prodcutos = all_prodcutos;
    }
}
