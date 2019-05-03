package com.example.proyectdam.controllers;

import android.util.Log;

import com.example.proyectdam.models.almacen.Almacen;
import com.example.proyectdam.models.almacen.Categoria;
import com.example.proyectdam.models.almacen.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Data {
    private FirebaseDatabase database;
    private static ArrayList<Producto> productos;
    private static ArrayList<Almacen> almacenes;
    private static ArrayList<Categoria> categorias;

    public Data(){
        productos = new ArrayList<>();
        almacenes = new ArrayList<>();
        categorias = new ArrayList<>();
        cargarDatos();
    }

    public void cargarDatos(){
        database = FirebaseDatabase.getInstance("https://proyectdamfinal.firebaseio.com/");
        DatabaseReference ref = database.getReference("/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    switch (data.getKey()){
                        case "almacenes":
                            for (DataSnapshot almacen : data.getChildren() ) {
                                almacenes.add(new Almacen(almacen.child("direccion").getValue(String.class)));
                            }
                            break;
                        case "productos":
                            for (DataSnapshot producto : data.getChildren()) {
                                productos.add(new Producto(producto.child("nombre").getValue(String.class),
                                        producto.child("descripcion").getValue(String.class),
                                        new Categoria(producto.child("categoria").child("nombre").getValue(String.class)),
                                        producto.child("cantidad").getValue(Double.class),
                                        producto.child("proveedor").getValue(String.class),
                                        producto.child("precioProveedor").getValue(Double.class),
                                        producto.child("precioPVP").getValue(Double.class)
                                ));
                            }
                            break;
                        case "categorias":
                            for ( DataSnapshot categoria : data.getChildren() ) {
                                categorias.add(new Categoria(categoria.child("nombre").getValue(String.class)));
                            }
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("proyecto", "Failed to read value.", error.toException());
            }
        });
    }

    public static ArrayList<Producto> getProductos() {
        return productos;
    }

    public static void setProductos(ArrayList<Producto> productos) {
        Data.productos = productos;
    }

    public static ArrayList<Almacen> getAlmacenes() {
        return almacenes;
    }

    public static void setAlmacenes(ArrayList<Almacen> almacenes) {
        Data.almacenes = almacenes;
    }

    public static ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public static void setCategorias(ArrayList<Categoria> categorias) {
        Data.categorias = categorias;
    }
}