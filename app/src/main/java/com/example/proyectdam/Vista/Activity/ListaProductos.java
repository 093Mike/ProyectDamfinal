package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.R;
import com.example.proyectdam.Controlador.Activitys.Almacen.AdapterProductos;
import com.example.proyectdam.Model.Categoria;

public class ListaProductos extends AppCompatActivity {
    private C_Almacen c_almacen;
    private RecyclerView recyclerView_listaProductos;
    private AdapterProductos adapter;
    private static Context myContext;
    private SearchView searchView_buscaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        getSupportActionBar().hide();

        Categoria categoriaSeleccionada = (Categoria) getIntent().getSerializableExtra("categoria");
        myContext = this;

        c_almacen = new C_Almacen();
        adapter = new AdapterProductos(c_almacen.adapter_cargarProductos(categoriaSeleccionada));
//        c_almacen.cargarProductos(adapter,(Categoria) getIntent().getSerializableExtra("categoria"));
        recyclerView_listaProductos = findViewById(R.id.recyclerView_productos);
        recyclerView_listaProductos.setAdapter(adapter);
        recyclerView_listaProductos.setLayoutManager(new LinearLayoutManager(ListaProductos.this));

        searchView_buscaProductos = findViewById(R.id.searchView_buscarProducto);
        searchView_buscaProductos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                recyclerView_listaProductos = findViewById(R.id.recyclerView_productos);
//                adapter = new AdapterProductos();
//                recyclerView_listaProductos.setAdapter(adapter);
//                recyclerView_listaProductos.setLayoutManager(new LinearLayoutManager(ListaProductos.this));
//
//
//                searchView_buscaProductos = findViewById(R.id.searchView_buscarProducto);
//                searchView_buscaProductos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String s) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String s) {
//                        adapter.getFilter().filter(s);
//                        return false;
//                    }
//                });
//            }
//        }).start();

    }

    public static Context getInstance() { return myContext; }
    public AdapterProductos getAdapter() { return adapter; }
}
