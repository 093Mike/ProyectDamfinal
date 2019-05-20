package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Model.Almacen;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Model.Incidencia;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.Model.Proveedor;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.AddProducto;
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
    public static ArrayList<Proveedor> proveedores = new ArrayList<>();
    public static ArrayList<Incidencia> incidencias = new ArrayList<>();
    private static Almacen almacenActual = null;

    public void cargarAlmacenesApp() {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("almacenes");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("proyecto", "onDataChange: Thread=" + Thread.currentThread().getName());
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
                String nextId = almacenes.get(almacenes.size() - 1).getId();
                Almacen.numeroAlmacen = Integer.parseInt(nextId.substring(nextId.lastIndexOf("M") + 1)) + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void cargarCategoriasApp(){
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
                    //categoriasProductos.add(categoriaApp);
                    if (categoriaApp.getIdAlmacenes().contains(almacenActual.getId())){
                        categoriasProductos.add(categoriaApp);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void cargarProductosApp(){
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("productos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Producto.nextId = "-1";
                productos.clear();
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    for (DataSnapshot categoria : producto.child("categoria").getChildren()) {
                        if (producto.child("almacen").child("id").getValue(String.class).equals(almacenActual.getId())) {
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
                            break;
                        }
                        if (Integer.parseInt(producto.child("id").getValue(String.class)) > Integer.parseInt(Producto.nextId)){
                            Producto.nextId = String.valueOf(Integer.parseInt(producto.child("id").getValue(String.class)) + 1);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void cargarProveedoresApp() {
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("proveedores");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                proveedores.clear();
                for (DataSnapshot proveedor : dataSnapshot.getChildren()) {
                    proveedores.add(new Proveedor(proveedor.getKey(),
                            proveedor.child("nombre").getValue(String.class),
                            proveedor.child("telefonoContacto").getValue(String.class),
                            proveedor.child("correoContacto").getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void cargarIncidenciasApp(){
        FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference("incidencias");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("proyecto", "onDataChange: Thread=" + Thread.currentThread().getName());
                incidencias.clear();
                Incidencia.nextId = "-1";
                for (DataSnapshot incidencia : dataSnapshot.getChildren()) {
                    if (incidencia.child("idAlmacen").getValue(String.class).equals(almacenActual.getId())){
                        incidencias.add(new Incidencia(incidencia.child("idIncidencia").getValue(String.class),
                                incidencia.child("idAlmacen").getValue(String.class),
                                incidencia.child("idProducto").getValue(String.class),
                                incidencia.child("cantidad").getValue(String.class),
                                incidencia.child("motivo").getValue(String.class),
                                incidencia.child("detalles").getValue(String.class)));
                    }
                    if (Integer.parseInt(incidencia.child("idIncidencia").getValue(String.class)) >= Integer.parseInt(Incidencia.nextId)){
                        Incidencia.nextId = String.valueOf(Integer.parseInt(incidencia.child("idIncidencia").getValue(String.class)) + 1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public ArrayList adapter_cargarProductos(Categoria categoria){
        ArrayList<Producto> productosCategoria = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().getId().equals(categoria.getId())){
                productosCategoria.add(p);
            }
        }
        return productosCategoria;
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
                        cargarCategoriasApp();
                        cargarProductosApp();
                        cargarIncidenciasApp();
                    }
                });
        builder.setCancelable(false);
        builder.show();
    }

    public void alertDialog_setImagenProductoNuevo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddProducto.getInstance());
        builder.setTitle("Imagen del producto")
                .setItems(new String[]{"Abrir cámara...", "Abrir galería..."}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: IMPLEMENTAR CLASE INTENTSMENU PARA ABRIR INTENTS DESDE ESTE METODO + PERMISOS CAMARA Y ALMACENAMIENTO INTERNO (?)
                        if (which == 0){
                            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            ((Activity)AddProducto.getInstance()).startActivityForResult(i,0);
                        } else if (which == 1) {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            ((Activity)AddProducto.getInstance()).startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"),1);
                        }
                    }
                });
        builder.show();
    }

    public void alertDialog_escogeProveedor(final View v){
        String[] proveedoresAlertDialog = new String[proveedores.size()];
        for (int i = 0; i < proveedores.size(); i++) {
            proveedoresAlertDialog[i] = proveedores.get(i).getNombre();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(AddProducto.getInstance());
        builder.setTitle("Escoge un proveedor:")
                .setSingleChoiceItems(proveedoresAlertDialog, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((EditText)v).setText(proveedores.get(which).getNombre());
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public String[] spinner_cargarCategorias(){
        String[] categorias = new String[categoriasProductos.size()];
        for (int i = 0; i < categoriasProductos.size(); i++) {
            categorias[i] = categoriasProductos.get(i).getId();
        }
        return categorias;
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
        final FirebaseDatabase database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        DatabaseReference reference = database.getReference();
        Query query = reference.child("productos").orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot producto : dataSnapshot.getChildren()) {
                    Producto.nextId = producto.getKey();
                    break;
                }
                //Producto.nextId = dataSnapshot.getValue(Producto.class).getId();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Almacen buscaAlmacen(String id) {
        for (Almacen almacen : almacenes) {
            if (almacen.getId().equals(id)) {
                return almacen;
            }
        }
        return null;
    }

    public Categoria buscaCategoria(String id){
        for (Categoria categoria : categoriasProductos) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    public Proveedor buscaProveedor(String nombre){
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getNombre().equals(nombre)) {
                return proveedor;
            }
        }
        return null;
    }

    public Producto buscaProducto(String id){
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    public Almacen getAlmacenActual() {
        return almacenActual;
    }

    public void setAlmacenActual(Almacen almacenActual) {
        this.almacenActual = almacenActual;
    }
}
