package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.proyectdam.Controlador.Activitys.Almacen.Movimientos.Adapter_movimientos;
import com.example.proyectdam.Controlador.Activitys.Almacen.Movimientos.C_Activity_VerMovimientos;
import com.example.proyectdam.Model.Almacen;
import com.example.proyectdam.R;

public class Activity_VerMovimientos extends AppCompatActivity {

    RecyclerView recyclerView;
    public Adapter_movimientos adapter_movimientos;
    C_Activity_VerMovimientos c_activity_verMovimientos;
    public Almacen almacenactual;

    public Activity_VerMovimientos(Almacen almacen){
        almacenactual = almacen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen_vermovimientos);
        getSupportActionBar().hide();
        c_activity_verMovimientos = new C_Activity_VerMovimientos();
        c_activity_verMovimientos.initialize();
        recyclerView = findViewById(R.id.vermoviento);
        adapter_movimientos = new Adapter_movimientos(c_activity_verMovimientos.getMovimientos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter_movimientos);


    }
    private static Activity_VerMovimientos myContext;
    public Activity_VerMovimientos() { myContext = this; }
    public static Activity_VerMovimientos getInstance() { return myContext; }

}
