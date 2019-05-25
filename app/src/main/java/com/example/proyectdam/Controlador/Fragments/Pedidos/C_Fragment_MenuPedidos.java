package com.example.proyectdam.Controlador.Fragments.Pedidos;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.example.proyectdam.Model.Cliente;
import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.Model.Prodcuto_en_Pedido;
import com.example.proyectdam.Model.Producto_para_Pedidos;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class C_Fragment_MenuPedidos  {
    private DatabaseReference mref,mref2,mref3;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Producto_para_Pedidos> all_prodcutos;
    private ArrayList<Cliente> clientes;
    private boolean[] check ;
    private ArrayList<Pedido> pedidosfiltrados;
    private int pedidoselecionado;
    public static boolean control;


    public void initialite(){
        pedidos = new ArrayList<>();
        all_prodcutos = new ArrayList<>();
        pedidosfiltrados = new ArrayList<>();
        clientes = new ArrayList<>();
        control =true;
        check = new boolean[]{true, true, true, true, true};
        mref  = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("pedidos");
        mref2 = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("productos");
        mref3 = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("clientes");

        mref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clientes.clear();
                Cliente.id_proximo = 1000;
                for (DataSnapshot clientes_all : dataSnapshot.getChildren()) {
                    clientes.add(new Cliente(
                            Integer.parseInt(clientes_all.getKey()),
                            clientes_all.child("nombre").getValue(String.class),
                            clientes_all.child("telefono").getValue(Integer.class),
                            clientes_all.child("direccion").getValue(String.class),
                            clientes_all.child("ciudad").getValue(String.class)
                    ));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (control) {
                    Pedido.proximoid = 1000;
                    pedidos.clear();
                    for (DataSnapshot pedidos_all : dataSnapshot.getChildren()) {
                        int id = Integer.parseInt(pedidos_all.getKey());
                        String[] productos, cantidades;
                        ArrayList<Prodcuto_en_Pedido> fproductos;
                        fproductos = new ArrayList<>();
                        try {
                            productos = pedidos_all.child("productos").getValue(String.class).split(",");
                            cantidades = pedidos_all.child("cantidades").getValue(String.class).split(",");
                        }catch (Exception e){
                            productos = new String[]{""};
                            cantidades = new String[]{""};
                        }
                        if(!productos[0].equals("") && !cantidades[0].equals("")){
                            for (int i = 0; i < productos.length; i++) {
                                fproductos.add(new Prodcuto_en_Pedido(Integer.parseInt(cantidades[i]), Integer.parseInt(productos[i])));
                            }
                        }
                        String fecha = pedidos_all.child("fecha").getValue(String.class);
                        Double precio = pedidos_all.child("preciototal").getValue(Double.class);
                        int nombre = 0;
                        if(pedidos_all.child("id_cliente").getValue(Integer.class)!=null) {
                            nombre = pedidos_all.child("id_cliente").getValue(Integer.class);
                        }
                        int estado = pedidos_all.child("estado").getValue(Integer.class);

                        pedidos.add(new Pedido(id, nombre, fecha, fproductos, precio, estado));
                    }
                    Collections.sort(pedidos, new Comparator<Pedido>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        public int compare(Pedido o1, Pedido o2) {
                            return Integer.compare(o2.getId(), o1.getId());
                        }
                    });
                    Fragment_MenuPedidos.getInstance().adaptadorPedidos.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        todosproductos();

    }

    public void todosproductos(){
        mref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                all_prodcutos.clear();
                String id_almacen="";
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    id_almacen = producto.child("almacen").child("id").getValue(String.class);
                    all_prodcutos.add(new Producto_para_Pedidos(
                            Integer.parseInt(producto.child("id").getValue(String.class)),
                            producto.child("nombre").getValue(String.class),
                            producto.child("cantidad").getValue(Double.class),
                            producto.child("precioProveedor").getValue(Double.class),
                            producto.child("precioPVP").getValue(Double.class),
                            id_almacen
                    ));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void actualizarProductos() {
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (control) {
                    Pedido.proximoid = 1000;
                    pedidos.clear();
                    for (DataSnapshot pedidos_all : dataSnapshot.getChildren()) {
                        int id = Integer.parseInt(pedidos_all.getKey());
                        String[] productos, cantidades;
                        ArrayList<Prodcuto_en_Pedido> fproductos;
                        fproductos = new ArrayList<>();
                        try {
                            productos = pedidos_all.child("productos").getValue(String.class).split(",");
                            cantidades = pedidos_all.child("cantidades").getValue(String.class).split(",");

                        }catch (Exception e){
                            productos = new String[]{""};
                            cantidades = new String[]{""};
                        }
                        if(!productos[0].equals("") && !cantidades[0].equals("")){
                            for (int i = 0; i < productos.length; i++) {
                                fproductos.add(new Prodcuto_en_Pedido(Integer.parseInt(cantidades[i]), Integer.parseInt(productos[i])));
                            }
                        }
                        String fecha = pedidos_all.child("fecha").getValue(String.class);
                        Double precio = pedidos_all.child("preciototal").getValue(Double.class);
                        int nombre = 0;
                        if(pedidos_all.child("id_cliente").getValue(Integer.class)!=null) {
                            nombre = pedidos_all.child("id_cliente").getValue(Integer.class);
                        }
                        int estado = pedidos_all.child("estado").getValue(Integer.class);

                        pedidos.add(new Pedido(id, nombre, fecha, fproductos, precio, estado));
                    }
                    Collections.sort(pedidos, new Comparator<Pedido>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        public int compare(Pedido o1, Pedido o2) {
                            return Integer.compare(o2.getId(), o1.getId());
                        }
                    });
                    Fragment_MenuPedidos.getInstance().adaptadorPedidos.notifyDataSetChanged();
                }
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

    public void anadirPedido() {

        control = false;
        DatabaseReference myRef = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("pedidos/" + Pedido.proximoid);
        myRef.child("estado").setValue(0);
        myRef.child("cantidades").setValue("");
        myRef.child("productos").setValue("");
        myRef.child("preciototal").setValue(0);
        myRef.child("fecha").setValue(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()));
        control = true;
    }



    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
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
