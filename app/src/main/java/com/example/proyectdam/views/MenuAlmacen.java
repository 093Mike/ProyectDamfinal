package com.example.proyectdam.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.proyectdam.R;
import com.example.proyectdam.controllers.AdapterCategorias;
import com.example.proyectdam.models.almacen.Categoria;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MenuAlmacen extends AppCompatActivity {
    private ArrayList<Categoria> categoriasProductos;
    private RecyclerView recyclerView_categorias;
    private AdapterCategorias adapter;
    public static Categoria categoriaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_almacen);
        getSupportActionBar().hide();
        categoriasProductos = new ArrayList<>();
        recyclerView_categorias = findViewById(R.id.recyclerView_categorias);

        adapter = new AdapterCategorias(categoriasProductos);
        recyclerView_categorias.setAdapter(adapter);
        recyclerView_categorias.setLayoutManager(new LinearLayoutManager(this));

        extraerCategoriasFirebase();
    }

    public void extraerCategoriasFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://proyectdamfinal.firebaseio.com/");
        DatabaseReference reference = database.getReference("categorias");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categoriasProductos.clear();
                Log.d("proyecto", "onDataChange");
                for (DataSnapshot categoria : dataSnapshot.getChildren()){
                    Log.d("proyecto", "añade categoria al adapter");
                    categoriasProductos.add(new Categoria(categoria.child("nombre").getValue(String.class)));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}