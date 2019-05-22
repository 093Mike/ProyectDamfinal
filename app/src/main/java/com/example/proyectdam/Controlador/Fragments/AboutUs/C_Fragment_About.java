package com.example.proyectdam.Controlador.Fragments.AboutUs;

import android.support.annotation.NonNull;

import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class C_Fragment_About {
    DatabaseReference mref;
    FirebaseDatabase database;
    private String nombre;
    private int pos;
    private String[] plan = new String[]{"Topacio","LapisLazuli","Firestone"};

    public void initialite() {
        database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        mref = database.getReference("info");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot empresa : dataSnapshot.getChildren()){
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public String getNombreEmpresa(){
        return nombre;
    }
    public String getPlanEmpresa(){
        return plan[pos];
    }
}
