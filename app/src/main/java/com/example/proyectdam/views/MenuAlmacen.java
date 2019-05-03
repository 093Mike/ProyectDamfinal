package com.example.proyectdam.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectdam.R;
import com.example.proyectdam.controllers.Data;
import com.example.proyectdam.models.almacen.Categoria;
import com.example.proyectdam.models.almacen.Producto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuAlmacen extends AppCompatActivity {
//    private Button button_crearItem;
//    private Button button_mostrarProductos;
//    private TextView textView_listaItems;
//    private FirebaseDatabase database;
//    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_almacen);


//        database = FirebaseDatabase.getInstance("https://proyectdamfinal.firebaseio.com/");
//        myRef = database.getReference("productos/");
//
//        textView_listaItems = findViewById(R.id.textView_listaItems);
//        button_crearItem = findViewById(R.id.button_crearItem);
//        button_mostrarProductos = findViewById(R.id.button_mostrarItems);
//
//        button_crearItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Producto p = new Producto("televisor", "sdasd askl dk kasd klaksd kl",  new Categoria("Informatica"), 50.0 , "proveedor1", 300.0, 6000.0);
//                myRef = database.getReference("productos/" + p.getId());
//                myRef.setValue(p);
//            }
//        });
//
//        button_mostrarProductos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = "";
//                for (Producto p : Data.getProductos()) {
//                    text += "ID: " + p.getId()
//                            + "\nNombre: " + p.getNombre()
//                            + "\nDescripción: " + p.getDescripcion()
//                            + "\nCategoría: " + p.getCategoria().getNombre()
//                            + "\nCantidad: " + String.valueOf(p.getCantidad())
//                            + "\nProveedor: " + p.getProveedor()
//                            + "\nPrecio proveedor: " + String.valueOf(p.getPrecioProveedor())
//                            + "\nPVP: " + String.valueOf(p.getPrecioPVP()) + "\n\n";
//                }
//                textView_listaItems.setText(text.trim());
//            }
//        });


    }
}
