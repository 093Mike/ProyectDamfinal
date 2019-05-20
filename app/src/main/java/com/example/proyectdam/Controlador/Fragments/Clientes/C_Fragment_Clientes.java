package com.example.proyectdam.Controlador.Fragments.Clientes;

import android.support.annotation.NonNull;

import com.example.proyectdam.Model.Cliente;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Clientes.Fragment_Clientes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Fragment_Clientes {
    private ArrayList<Cliente> clientes;
    private DatabaseReference mref;

    public void initialite() {
        clientes = new ArrayList<>();
        mref = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("clientes");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clientes.clear();
                Cliente.id_proximo = 1000;
                for (DataSnapshot clientes_all : dataSnapshot.getChildren()) {
                    clientes.add(new Cliente(
                            Integer.parseInt(clientes_all.getKey()),
                            clientes_all.child("nombre").getValue(String.class),
                            clientes_all.child("telefono").getValue(Integer.class),
                            clientes_all.child("direccion").getValue(String.class),
                            clientes_all.child("ciudad").getValue(String.class)
                    ));
                }
                Fragment_Clientes.getInstance().adapter_todos_clientes.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

}
