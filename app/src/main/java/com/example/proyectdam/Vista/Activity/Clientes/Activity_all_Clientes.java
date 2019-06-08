package com.example.proyectdam.Vista.Activity.Clientes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.proyectdam.Controlador.Activitys.Pedidos.Adapter_Todos_Clientes;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;

public class Activity_all_Clientes  extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_Todos_Clientes adapter_todos_clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clientes);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.reciclerall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter_todos_clientes = new Adapter_Todos_Clientes(R.layout.item_clientes, Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes());
        recyclerView.setAdapter(adapter_todos_clientes);
    }

    private static Activity_all_Clientes myContext;
    public Activity_all_Clientes() { myContext = this; }
    public static Activity_all_Clientes getInstance() { return myContext; }
}
