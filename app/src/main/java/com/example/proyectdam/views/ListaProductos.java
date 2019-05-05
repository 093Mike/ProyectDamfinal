package com.example.proyectdam.views;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.proyectdam.R;
import com.example.proyectdam.controllers.AdapterProductos;
import com.example.proyectdam.controllers.Data;
import com.example.proyectdam.models.almacen.Categoria;
import com.example.proyectdam.models.almacen.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity {
    private ArrayList<Producto> productosAlmacen;
    private RecyclerView recyclerView_listaProductos;
    private AdapterProductos adapter;
    private Categoria categoriaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        categoriaSeleccionada = (Categoria) getIntent().getSerializableExtra("categoria");

        productosAlmacen = new ArrayList<>();
        cargarProductos();
        recyclerView_listaProductos = findViewById(R.id.recyclerView_productos);
        adapter = new AdapterProductos(productosAlmacen);
        recyclerView_listaProductos.setAdapter(adapter);
        recyclerView_listaProductos.setLayoutManager(new LinearLayoutManager(this));

    }

    public void cargarProductos(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://proyectdamfinal.firebaseio.com/");
        DatabaseReference reference = database.getReference("productos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productosAlmacen.clear();
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    for (DataSnapshot categoria : producto.child("categoria").getChildren()){
                        if (categoria.getValue(String.class).equals(categoriaSeleccionada.getId())){
                            productosAlmacen.add(new Producto(producto.child("nombre").getValue(String.class),
                                    producto.child("descripcion").getValue(String.class),
                                    new Categoria(producto.child("categoria").child("nombre").getValue(String.class)),
                                    producto.child("cantidad").getValue(Double.class),
                                    producto.child("proveedor").getValue(String.class),
                                    producto.child("precioProveedor").getValue(Double.class),
                                    producto.child("precioPVP").getValue(Double.class)));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
