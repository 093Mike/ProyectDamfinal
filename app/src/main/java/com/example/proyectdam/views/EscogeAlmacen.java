package com.example.proyectdam.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.proyectdam.R;
import com.example.proyectdam.controllers.Data;
import com.example.proyectdam.models.almacen.Almacen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EscogeAlmacen extends AppCompatActivity {
    private Button button_selectAlmacen;
    //private ArrayList<Almacen> almacenes;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoge_almacen);
        Data data = new Data();
//        almacenes = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://proyectdamfinal.firebaseio.com/");

        //subirAlmacenes();
        //cargarAlmacenes();


        button_selectAlmacen = findViewById(R.id.button_almacen);
        button_selectAlmacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] almacenesAlertDialog = new String[Data.getAlmacenes().size()];
                for (int i = 0; i < Data.getAlmacenes().size(); i++) {
                    almacenesAlertDialog[i] = Data.getAlmacenes().get(i).getId() + " - " + Data.getAlmacenes().get(i).getDireccion();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EscogeAlmacen.this);
                builder.setTitle("Escoge un almacén:")
                        .setItems(almacenesAlertDialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                button_selectAlmacen.setText(String.valueOf(which));
                                startActivity(new Intent(EscogeAlmacen.this, MenuAlmacen.class).putExtra("almacen", which));
                            }
                        });
                builder.show();
            }
        });
    }

//    public void cargarAlmacenes(){
//        DatabaseReference myRef = database.getReference("almacenes");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                almacenes.clear();
//
//                for (DataSnapshot almacen : dataSnapshot.getChildren()) {
//                    //almacenes.add(almacen.child("id").getValue(String.class) + " - " + almacen.child("direccion").getValue(String.class));
//                    almacenes.add(new Almacen(almacen.child("direccion").getValue(String.class)));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.d("proyecto", "Failed to read value.", error.toException());
//            }
//        });
//    }

    public void subirAlmacenes(){
        Almacen[] almacenes = {new Almacen("C/Falsa 123"), new Almacen("C/Falsa 456"), new Almacen("C/Falsa 789")};
        for (int i = 0; i < almacenes.length; i++) {
            DatabaseReference myRef = database.getReference("almacenes/" + almacenes[i].getId());
            myRef.setValue(almacenes[i]);
        }
    }
}