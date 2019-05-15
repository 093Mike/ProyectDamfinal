package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.R;
import com.example.proyectdam.Controlador.Activitys.Almacen.AdapterCategorias;
import com.example.proyectdam.Model.Categoria;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ListaCategorias extends AppCompatActivity {
    private C_Almacen c_almacen;
    private RecyclerView recyclerView_categorias;
    private AdapterCategorias adapter;
    private static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_almacen);
        getSupportActionBar().hide();

        myContext = this;
        c_almacen = new C_Almacen();
        recyclerView_categorias = findViewById(R.id.recyclerView_categorias);
        adapter = new AdapterCategorias(C_Almacen.categoriasProductos);
        recyclerView_categorias.setAdapter(adapter);
        recyclerView_categorias.setLayoutManager(new LinearLayoutManager(this));
        c_almacen.cargarCategorias(adapter);
    }

    public static Context getInstance() { return myContext; }
}