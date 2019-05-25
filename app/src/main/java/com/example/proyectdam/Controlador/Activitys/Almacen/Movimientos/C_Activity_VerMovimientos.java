package com.example.proyectdam.Controlador.Activitys.Almacen.Movimientos;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.proyectdam.Model.Movimientos;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.Activity_VerMovimientos;
import com.example.proyectdam.Vista.Fragment_Almacen.MenuAlmacen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Activity_VerMovimientos extends Activity {
    ArrayList<Movimientos> movimientos;
    DatabaseReference mref;

    public void initialize(){
        movimientos = new ArrayList<>();
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        mref = database.getReference("almacenes/"+ MenuAlmacen.getInstance().c_almacen.getAlmacenActual().getId() +"/movimientos");
        mref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot movimiento : dataSnapshot.getChildren()) {
                    movimientos.add(
                            new Movimientos(
                            movimiento.child("idproducto").getValue(Integer.class),
                            movimiento.getKey(),
                            movimiento.child("descripcion").getValue(String.class),
                            movimiento.child("tipo").getValue(Integer.class)
                            ));
                }
                Activity_VerMovimientos.getInstance().adapter_movimientos.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public ArrayList<Movimientos> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimientos> movimientos) {
        this.movimientos = movimientos;
    }
}
