package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.R;
import com.example.proyectdam.Controlador.Activitys.Almacen.AdapterProductos;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Model.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaProductos extends AppCompatActivity {
    private C_Almacen c_almacen;
    private RecyclerView recyclerView_listaProductos;
    private AdapterProductos adapter;
    private static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        getSupportActionBar().hide();

        myContext = this;
        c_almacen = new C_Almacen();
        recyclerView_listaProductos = findViewById(R.id.recyclerView_productos);
        adapter = new AdapterProductos(C_Almacen.productos);
        recyclerView_listaProductos.setAdapter(adapter);
        recyclerView_listaProductos.setLayoutManager(new LinearLayoutManager(this));
        c_almacen.cargarProductos(adapter, (Categoria) getIntent().getSerializableExtra("categoria"));
    }

    public static Context getInstance() { return myContext; }
}
