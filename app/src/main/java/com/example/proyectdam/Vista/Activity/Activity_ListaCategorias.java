package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.R;
import com.example.proyectdam.Controlador.Activitys.Almacen.AdapterCategorias;

public class Activity_ListaCategorias extends AppCompatActivity {
    private C_Almacen c_almacen;
    private RecyclerView recyclerView_categorias;
    private AdapterCategorias adapter;
    private static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_almacen);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myContext = this;
        c_almacen = new C_Almacen();
        recyclerView_categorias = findViewById(R.id.recyclerView_categorias);
        adapter = new AdapterCategorias(C_Almacen.categoriasProductos);
        recyclerView_categorias.setAdapter(adapter);
        recyclerView_categorias.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Context getInstance() { return myContext; }
}