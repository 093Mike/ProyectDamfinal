package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.proyectdam.Model.Almacen;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Almacen {
    public static ArrayList<Almacen> almacenesFirebase;
    private ArrayList<Categoria> categoriasProductos;

    public C_Almacen(){
        almacenesFirebase = new ArrayList<>();
        cargarAlmacenes();
    }

    public void cargarAlmacenes(){

        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("almacenes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                almacenesFirebase.clear();
                for (DataSnapshot almacen : dataSnapshot.getChildren()){
                    almacenesFirebase.add(new Almacen(almacen.child("direccion").getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void extraerCategoriasFirebase(final RecyclerView.Adapter adapter){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
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

    public ArrayList<Almacen> getAlmacenesFirebase() {
        return almacenesFirebase;
    }

    public void setAlmacenesFirebase(ArrayList<Almacen> almacenesFirebase) {
        this.almacenesFirebase = almacenesFirebase;
    }
}
