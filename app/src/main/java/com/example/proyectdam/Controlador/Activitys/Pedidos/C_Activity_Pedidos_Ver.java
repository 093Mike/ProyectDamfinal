package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.proyectdam.Controlador.Fragments.Pedidos.AdaptadorPedidos;
import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.Activity_VerPedido;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Activity_Pedidos_Ver extends Activity {
    DatabaseReference mref;
    FirebaseDatabase database;
    Pedido pedidoActual;
    ArrayList<String> productos;
    ArrayList<Double> precios;

    public void initialize(){
        productos = new ArrayList<>();
        precios = new ArrayList<>();

        boolean filtro=false;
        for (int i = 0 ; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getCheck().length ; i++){
            if(!Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getCheck()[i]){
                filtro = true;
                pedidoActual = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getPedidosfiltrados().get(AdaptadorPedidos.position);
            }
        }
        if(!filtro){
            pedidoActual = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getPedidos().get(AdaptadorPedidos.position);
        }
        database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        mref = database.getReference("productos");
        mref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    for(int i = 0 ; i < pedidoActual.getProductos().size();i++){
                        if(producto.getKey().equals(String.valueOf(pedidoActual.getProductos().get(i).getId_producto()))){
                                productos.add(producto.child("nombre").getValue(String.class));
                                precios.add(producto.child("precioPVP").getValue(Double.class));
                        }
                    }
                }
                Activity_VerPedido.getInstance().adaptadorPedidos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public String getTitulo(){
        return "Pedido #"+pedidoActual.getId();
    }
    public String getFecha(){
        return pedidoActual.getFecharealizado();
    }
    public Double getTotal(){ return pedidoActual.getPrecioTotal();}
    public String getEstado(){
        String[] estado = Activity_VerPedido.getInstance().getResources().getStringArray(R.array.estados);
        return "Estado: " + estado[pedidoActual.getEstado()];
    }
    public String getCliente(){
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId() == pedidoActual.getId_cliente()){
                return Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getNombre();
            }
        }
        return null;
    }
    public int getId_cliente(){
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId() == pedidoActual.getId_cliente()){
                return Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId();
            }
        }
        return Integer.parseInt(null);
    }
    public String getDireccionCliente(){
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId() == pedidoActual.getId_cliente()){
                return Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getDireccion();
            }
        }
        return null;
    }
    public String getCiudadCliente(){
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId() == pedidoActual.getId_cliente()){
                return Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getCiudad();
            }
        }
        return null;
    }

    public int getTelefonoCliente(){
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId() == pedidoActual.getId_cliente()){
                return Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getNum_telefono();
            }
        }
        return Integer.parseInt(null);
    }


    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public void setPedidoActual(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    public ArrayList<String> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<String> productos) {
        this.productos = productos;
    }
}
