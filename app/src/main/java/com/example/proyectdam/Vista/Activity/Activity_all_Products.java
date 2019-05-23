package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.proyectdam.Controlador.Activitys.Pedidos.Adapter_Todos_Productos;
import com.example.proyectdam.R;

public class Activity_all_Products extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_Todos_Productos adapter_todos_productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_prodcutos);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.reciclerall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter_todos_productos = new Adapter_Todos_Productos(R.layout.item_pedidos_mod, Activity_ModPedido.getInstance().c_activity_pedidos_mod.all_productos);
        recyclerView.setAdapter(adapter_todos_productos);
    }
    private static Activity_all_Products myContext;
    public Activity_all_Products() { myContext = this; }
    public static Activity_all_Products getInstance() { return myContext; }
}
