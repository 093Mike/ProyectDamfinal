package com.example.proyectdam.Controlador.Fragments.Proveedores;

import android.support.annotation.NonNull;

import com.example.proyectdam.Model.Proveedor;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Proveedores.Fragment_Proveedores;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Fragment_Proveedor {

    private ArrayList<Proveedor> proveedores = new ArrayList<>();

    public void initialite() {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("proveedores");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Proveedor.id_proximo = 1;
                proveedores.clear();
                for (DataSnapshot proveedor : dataSnapshot.getChildren()) {
                    proveedores.add(new Proveedor(proveedor.getKey(),
                            proveedor.child("nombre").getValue(String.class),
                            proveedor.child("telefonoContacto").getValue(String.class),
                            proveedor.child("correoContacto").getValue(String.class)));
                }
                Fragment_Proveedores.getInstance().proveedores_fragment.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public  ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(ArrayList<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }
}
