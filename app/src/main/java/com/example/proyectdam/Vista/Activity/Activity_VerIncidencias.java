package com.example.proyectdam.Vista.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.proyectdam.Controlador.Activitys.Almacen.AdapterCategorias;
import com.example.proyectdam.Controlador.Activitys.Almacen.AdapterIncidencias;
import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.R;

public class Activity_VerIncidencias extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_incidencias);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        RecyclerView recyclerView_incidencias = findViewById(R.id.recyclerView_incidencias);
        AdapterIncidencias adapter = new AdapterIncidencias(C_Almacen.incidencias);
        recyclerView_incidencias.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_incidencias.setAdapter(adapter);
    }
}