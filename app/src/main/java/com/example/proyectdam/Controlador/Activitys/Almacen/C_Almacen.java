package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Model.Almacen;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Almacen {
    public static ArrayList<Almacen> almacenes = new ArrayList<>();
    public static ArrayList<Categoria> categoriasProductos = new ArrayList<>();
    public static ArrayList<Producto> productos = new ArrayList<>();
    ;
    private static Almacen almacenActual = null;

    public void cargarAlmacenes() {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("almacenes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                almacenes.clear();
                for (DataSnapshot almacen : dataSnapshot.getChildren()) {
                    Almacen almacenApp = new Almacen(almacen.getKey(), almacen.child("direccion").getValue(String.class));
                    for (DataSnapshot pasillo : almacen.child("pasillos").getChildren()){
                        almacenApp.setPasillos(pasillo.getValue(String.class));
                    }
                    for (DataSnapshot estanteria : almacen.child("estanterias").getChildren()){
                        almacenApp.setEstanterias(estanteria.getValue(String.class));
                    }
                    almacenes.add(almacenApp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void cargarCategorias(final RecyclerView.Adapter adapter) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("categorias");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categoriasProductos.clear();
                for (DataSnapshot categoria : dataSnapshot.getChildren()) {
                    Categoria categoriaApp = new Categoria(categoria.child("nombre").getValue(String.class));
                    for (DataSnapshot almacenCategoria : categoria.child("almacen").getChildren()){
                        categoriaApp.setIdAlmacenes(almacenCategoria.getValue(String.class));
                    }
                    if (categoriaApp.getIdAlmacenes().contains(almacenActual.getId())){
                        categoriasProductos.add(categoriaApp);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void cargarProductos(final RecyclerView.Adapter adapter, final Categoria categoriaSeleccionada) {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("productos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productos.clear();
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    for (DataSnapshot categoria : producto.child("categoria").getChildren()) {
                        if (categoria.getValue(String.class).equals(categoriaSeleccionada.getId()) &&
                            producto.child("almacen").child("id").getValue(String.class).equals(almacenActual.getId())) {
                                productos.add(new Producto(producto.child("id").getValue(String.class),
                                        producto.child("nombre").getValue(String.class),
                                        producto.child("descripcion").getValue(String.class),
                                        new Categoria(producto.child("categoria").child("nombre").getValue(String.class)),
                                        buscaAlmacen(producto.child("almacen").child("id").getValue(String.class)),
                                        producto.child("ubicacion").getValue(String.class),
                                        producto.child("cantidad").getValue(Double.class),
                                        producto.child("proveedor").getValue(String.class),
                                        producto.child("precioProveedor").getValue(Double.class),
                                        producto.child("precioPVP").getValue(Double.class)));
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void alertDialog_escogeAlmacen(View v) {
        final String tag_escogido = ((String) v.getTag()).toUpperCase();

        String[] almacenesAlertDialog = new String[almacenes.size()];
        for (int i = 0; i < almacenes.size(); i++) {
            almacenesAlertDialog[i] = almacenes.get(i).getId()
                    + " - " + almacenes.get(i).getDireccion();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Menu.getInstance());
        builder.setTitle("Escoge un almacén:")
                .setItems(almacenesAlertDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        almacenActual = almacenes.get(which);
                        FragmentTransaction ft = Activity_Menu.getInstance().getSupportFragmentManager().beginTransaction();
                        IntentsMenu intentsMenu = new IntentsMenu();
                        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(tag_escogido), "fragment_meters");
                        ft.commit();
                    }
                });
        builder.show();

    }

    public void siguienteId_almacen() {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference();
        Query query = reference.child("almacenes").orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String idString = dataSnapshot.getKey();
                Almacen.numeroAlmacen = Integer.parseInt(idString.substring(idString.lastIndexOf("M") + 1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void siguienteId_producto() {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference();
        Query query = reference.child("productos").orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Producto.nextId = dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Almacen getAlmacenActual() {
        return almacenActual;
    }

    public void setAlmacenActual(Almacen almacenActual) {
        this.almacenActual = almacenActual;
    }

    public Almacen buscaAlmacen(String id) {
        for (Almacen almacen : almacenes) {
            if (almacen.getId().equals(id)) {
                return almacen;
            }
        }
        return null;
    }
}
