package com.example.proyectdam.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.proyectdam.R;
import com.example.proyectdam.Model.Almacen;
import com.example.proyectdam.Vista.Activity.ListaCategorias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class EscogeAlmacen extends AppCompatActivity {
    private ArrayList<Almacen> almacenes;
    public static Almacen almacenActual;
    private Button button_selectAlmacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoge_almacen);
        almacenes = new ArrayList<>();
        cargarAlmacenes();
        button_selectAlmacen = findViewById(R.id.button_almacen);
        button_selectAlmacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (almacenActual == null){
                    String[] almacenesAlertDialog = new String[almacenes.size()];
                    for (int i = 0; i < almacenes.size() ; i++) {
                        almacenesAlertDialog[i] = almacenes.get(i).getId() + " - " + almacenes.get(i).getDireccion();
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(EscogeAlmacen.this);
                    builder.setTitle("Escoge un almacén:")
                            .setItems(almacenesAlertDialog, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    almacenActual = almacenes.get(which);
                                    startActivity(new Intent(EscogeAlmacen.this, ListaCategorias.class));
                                }
                            });
                    builder.show();
                } else {
                    startActivity(new Intent(EscogeAlmacen.this, ListaCategorias.class));
                }
            }
        });
    }

    public void cargarAlmacenes(){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://proyectdamfinal.firebaseio.com/");
        DatabaseReference reference = database.getReference("almacenes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                almacenes.clear();
                for (DataSnapshot almacen : dataSnapshot.getChildren()) {
                    almacenes.add(new Almacen(almacen.child("direccion").getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}