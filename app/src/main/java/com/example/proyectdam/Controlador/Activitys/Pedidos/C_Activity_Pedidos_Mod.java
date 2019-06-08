package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.proyectdam.Controlador.Fragments.Pedidos.AdaptadorPedidos;
import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.Model.Prodcuto_en_Pedido;
import com.example.proyectdam.Model.Producto_para_Pedidos;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.Activity_ModPedido;
import com.example.proyectdam.Vista.Activity.Clientes.Activity_all_Clientes;
import com.example.proyectdam.Vista.Activity.Activity_all_Products;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class C_Activity_Pedidos_Mod extends Activity {
    DatabaseReference mref;
    FirebaseDatabase database;
    Pedido pedidoActual;
    public ArrayList<Integer> id_productos;
    ArrayList<String> productos;
    ArrayList<Double> catidadtotales;
    ArrayList<Double> precios;
    public ArrayList<Producto_para_Pedidos> all_productos;

    public void initialize() {
        productos = new ArrayList<>();
        id_productos = new ArrayList<>();
        precios = new ArrayList<>();
        catidadtotales = new ArrayList<>();
        all_productos = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos();

        boolean filtro = false;
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getCheck().length; i++) {
            if (!Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getCheck()[i]) {
                filtro = true;
                pedidoActual = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getPedidosfiltrados().get(AdaptadorPedidos.position);
            }
        }
        if (!filtro) {
            pedidoActual = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getPedidos().get(AdaptadorPedidos.position);
        }
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("productos");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                id_productos.clear();
                productos.clear();
                catidadtotales.clear();
                precios.clear();
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    for(int i = 0 ; i < pedidoActual.getProductos().size();i++){
                        if(producto.getKey().equals(String.valueOf(pedidoActual.getProductos().get(i).getId_producto()))){
                            id_productos.add(Integer.parseInt(producto.child("id").getValue(String.class)));
                            productos.add(producto.child("nombre").getValue(String.class));
                            catidadtotales.add(producto.child("cantidad").getValue(Double.class));
                            precios.add(producto.child("precioPVP").getValue(Double.class));
                        }
                    }
                }


                Activity_ModPedido.getInstance().adaptadorPedidos.notifyDataSetChanged();
                Activity_ModPedido.getInstance().total.setText(actualizarPrecios()+"€");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Double actualizarPrecios(){
        double total=0.0;
        try{
            for (int i=0;i< Activity_ModPedido.getInstance().c_activity_pedidos_mod.getPedidoActual().getProductos().size();i++){
                double precio = Activity_ModPedido.getInstance().c_activity_pedidos_mod.precios.get(i);
                precio= precio * Activity_ModPedido.getInstance().c_activity_pedidos_mod.pedidoActual.getProductos().get(i).getCantidad();
                total+=precio;
            }
        }
        catch (Exception e){
         total=0;
        }
        return total;

    }
    public void anadirProducto(){
        id_productos.add(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(Adapter_Todos_Productos.position).getId());
        productos.add(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(Adapter_Todos_Productos.position).getNombre());
        catidadtotales.add(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(Adapter_Todos_Productos.position).getCantidad());
        precios.add(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(Adapter_Todos_Productos.position).getPrecioPVP());
        Activity_all_Products.getInstance().finish();
        Activity_ModPedido.getInstance().c_activity_pedidos_mod.pedidoActual.getProductos().add(new Prodcuto_en_Pedido(
                1,
                Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(Adapter_Todos_Productos.position).getId())
        );

        Activity_ModPedido.getInstance().adaptadorPedidos.notifyDataSetChanged();
        Activity_ModPedido.getInstance().total.setText(actualizarPrecios()+"€");
    }


    public void guardarDatos(){
        String productos="", cantidades="";
        int estado=Activity_ModPedido.getInstance().estado.getSelectedItemPosition();
        for (int i = 0 ;i< pedidoActual.getProductos().size();i++){
            productos += pedidoActual.getProductos().get(i).getId_producto()+",";
            cantidades += pedidoActual.getProductos().get(i).getCantidad()+",";
            if(estado==3){
                DatabaseReference myRef = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("productos/" + pedidoActual.getProductos().get(i).getId_producto());
                Double all_cantidades = catidadtotales.get(i)-pedidoActual.getProductos().get(i).getCantidad();
                myRef.child("cantidad").setValue(all_cantidades);
            }
        }
        double precio=0.0;
        if(productos.length()!=0 && cantidades.length()!=0) {
            productos = productos.substring(0, productos.length() - 1);
            cantidades = cantidades.substring(0, cantidades.length() - 1);
            precio = Double.parseDouble(Activity_ModPedido.getInstance().total.getText().toString().replaceAll("€", ""));
        }

        DatabaseReference myRef =FirebaseDatabase.getInstance().getReference("pedidos/" + pedidoActual.getId());
        myRef.child("estado").setValue(estado);
        myRef.child("cantidades").setValue(cantidades);
        myRef.child("productos").setValue(productos);
        myRef.child("preciototal").setValue(precio);
        Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.todosproductos();
        if(estado==3 && productos.split(",").length!=0) {
            DatabaseReference myRef2;
            for (int k = 0; k < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().size(); k++) {
                int id_producto = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(k).getId();
                String id_almacen = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(k).getAlmacen();
                for (int j = 0; j < productos.split(",").length; j++) {
                    if (id_producto == Integer.parseInt(productos.split(",")[j])) {
                        myRef2 = FirebaseDatabase.getInstance().getReference("almacenes/" + id_almacen +
                                "/movimientos/" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
                        myRef2.child("idproducto").setValue(id_producto);
                        double cantidad_total = Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(k).getCantidad();
                        myRef2.child("descripcion").setValue("Se ha retirado el producto: "+
                                Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getAll_prodcutos().get(k).getNombre() +
                                " "+
                                cantidades.split(",")[j] +
                                " unidades del inventario. Faltan: " +
                                cantidad_total +
                                " unidades de este producto.");
                        myRef2.child("tipo").setValue(2);
                    }
                }
            }
        }

    }

    public void setCliente(int pos){
        for (int i = 0 ; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(i==pos){
                DatabaseReference myRef = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("pedidos/" + pedidoActual.getId());
                myRef.child("id_cliente").setValue(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId());
                Activity_all_Clientes.getInstance().finish();
                Activity_ModPedido.getInstance().cliente.setText(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getNombre());
            }
        }
    }

    public String getTitulo(){
        return "Pedido #"+pedidoActual.getId();
    }
    public String getFecha(){
        return pedidoActual.getFecharealizado();
    }
    public Double getTotal(){
        return pedidoActual.getPrecioTotal();
    }
    public int getEstado(){
        return pedidoActual.getEstado();
    }
    public String getCliente(){
        for (int i = 0; i < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size();i++){
            if(Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getId() == pedidoActual.getId_cliente()){
                return Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(i).getNombre();
            }
        }
        return null;
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

    public ArrayList<Double> getPrecios() {
        return precios;
    }

    public void setPrecios(ArrayList<Double> precios) {
        this.precios = precios;
    }

    public ArrayList<Double> getCatidadtotales() {
        return catidadtotales;
    }

    public void setCatidadtotales(ArrayList<Double> catidadtotales) {
        this.catidadtotales = catidadtotales;
    }

}
