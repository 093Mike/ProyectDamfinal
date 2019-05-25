package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Activity_AddProducto extends AppCompatActivity {
    private C_Almacen c_almacen;
    private ImageView imageView_imagenProducto;
    private EditText editText_nombreProducto,
            editText_descripcion,
            editText_proveedor,
            editText_stock,
            editText_precioProveedor,
            editText_pvp;
    private Spinner spinner_categoria,
            spinner_pasillo,
            spinner_estanteria;
    private Button button_subirProducto;
    private Bitmap bitmap_imagenProducto;

    private Producto productoModificar = null;
    private static Context myContext;
    private String pasilloSeleccionado,
            estanteriaSeleccionada,
            categoriaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myContext = this;
        c_almacen = new C_Almacen();

        productoModificar = (Producto) getIntent().getSerializableExtra("productoModificar");

        imageView_imagenProducto = findViewById(R.id.imageView_prod);
        editText_nombreProducto = findViewById(R.id.editText_nombreProducto);
        editText_descripcion = findViewById(R.id.editText_descripcion);
        editText_proveedor = findViewById(R.id.editText_proveedor);
        editText_stock = findViewById(R.id.editText_stock);
        editText_precioProveedor = findViewById(R.id.editText_precioProveedor);
        editText_pvp = findViewById(R.id.editText_precioPVP);
        spinner_categoria = findViewById(R.id.spinner_categorias);
        spinner_pasillo = findViewById(R.id.spinner_ubicacionPasillo);
        spinner_estanteria = findViewById(R.id.spinner_ubicacionEstanteria);
        button_subirProducto = findViewById(R.id.button_subirProducto);

        spinner_categoria.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, c_almacen.spinner_cargarCategorias()));
        spinner_pasillo.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, c_almacen.getAlmacenActual().getPasillos()));
        spinner_estanteria.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, c_almacen.getAlmacenActual().getEstanterias()));

        if (productoModificar != null){
            imageView_imagenProducto.setImageBitmap( BitmapFactory.decodeFile(getIntent().getStringExtra("rutaImagen")));
            editText_nombreProducto.setText(productoModificar.getNombre());
            editText_descripcion.setText(productoModificar.getDescripcion());
            editText_proveedor.setText(productoModificar.getProveedor());
            editText_stock.setText(Double.toString(productoModificar.getCantidad()));
            editText_precioProveedor.setText(Double.toString(productoModificar.getPrecioProveedor()));
            editText_pvp.setText(Double.toString(productoModificar.getPrecioPVP()));

            for (int i = 0; i < C_Almacen.categoriasProductos.size(); i++) {
                if (productoModificar.getCategoria().getId().equals(C_Almacen.categoriasProductos.get(i).getId())){
                    spinner_categoria.setSelection(i);
                }
            }

            String[] ubicacionProductoModificar = productoModificar.getUbicacion().split(" - ");
            for (int i = 0; i < c_almacen.getAlmacenActual().getPasillos().size(); i++) {
                if (c_almacen.getAlmacenActual().getPasillos().get(i).equals(ubicacionProductoModificar[0])){
                    spinner_pasillo.setSelection(i);
                }
            }

            for (int i = 0; i < c_almacen.getAlmacenActual().getEstanterias().size(); i++) {
                if (c_almacen.getAlmacenActual().getEstanterias().get(i).equals(ubicacionProductoModificar[1])){
                    spinner_estanteria.setSelection(i);
                }
            }
        } else {
            imageView_imagenProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c_almacen.alertDialog_setImagenProductoNuevo();
                }
            });
        }

        spinner_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSeleccionada = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_pasillo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pasilloSeleccionado = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_estanteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                estanteriaSeleccionada = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText_proveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_almacen.alertDialog_escogeProveedor(v);
            }
        });



        button_subirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Modificar imagen
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                if (productoModificar == null) {
                    Producto p = new Producto(editText_nombreProducto.getText().toString().trim(),
                            editText_descripcion.getText().toString().trim(),
                            new Categoria(c_almacen.buscaCategoria(categoriaSeleccionada).getNombre()),
                            c_almacen.getAlmacenActual(),
                            pasilloSeleccionado + " - " + estanteriaSeleccionada,
                            Double.parseDouble(editText_stock.getText().toString().trim()),
                            editText_proveedor.getText().toString().trim(),
                            Double.parseDouble(editText_precioProveedor.getText().toString().trim()),
                            Double.parseDouble(editText_pvp.getText().toString().trim()));

                    c_almacen.addProducto_guardarImagenCamara(Activity_AddProducto.this, bitmap_imagenProducto, p);
                    DatabaseReference reference = database.getReference("productos/" + p.getId());
                    reference.setValue(p);
                } else {
                    DatabaseReference reference = database.getReference("productos/" + productoModificar.getId());
                    productoModificar.setNombre(editText_nombreProducto.getText().toString());
                    productoModificar.setDescripcion(editText_descripcion.getText().toString());
                    productoModificar.setCategoria(new Categoria(c_almacen.buscaCategoria(categoriaSeleccionada).getNombre()));
                    productoModificar.setAlmacen(c_almacen.getAlmacenActual());
                    productoModificar.setUbicacion(pasilloSeleccionado + " - " + estanteriaSeleccionada);
                    productoModificar.setCantidad(Double.parseDouble(editText_stock.getText().toString().trim()));
                    productoModificar.setProveedor(c_almacen.buscaProveedor(editText_proveedor.getText().toString().trim()).getIdProveedor());
                    productoModificar.setPrecioProveedor(Double.parseDouble(editText_precioProveedor.getText().toString().trim()));
                    productoModificar.setPrecioPVP(Double.parseDouble(editText_pvp.getText().toString().trim()));
                    reference.setValue(productoModificar);

                    reference = FirebaseDatabase.getInstance().getReference("almacenes/" + c_almacen.getAlmacenActual().getId() + "/movimientos/" +
                            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
                    reference.child("idproducto").setValue(Integer.parseInt(productoModificar.getId()));
                    double cantidad_total = Double.parseDouble(editText_stock.getText().toString().trim());
                    reference.child("descripcion").setValue("Se ha modificado un producto : " +
                            editText_nombreProducto.getText().toString().trim() + "." +
                            " Se dispone de " + cantidad_total + " unidades de este producto.");
                    reference.child("tipo").setValue(1);
                }

                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK){ // IMAGEN DE LA CAMARA
            bitmap_imagenProducto = data.getParcelableExtra("data");
            imageView_imagenProducto.setImageBitmap(bitmap_imagenProducto);
        } else if (requestCode == 1 && resultCode == RESULT_OK){ // IMAGEN DE LA GALERIA
            Uri selectedImage = data.getData();
            //String pathImagen = selectedImage.getPath();
            try {
                bitmap_imagenProducto = data.getParcelableExtra("data");
                imageView_imagenProducto.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private static Activity_AddProducto myContext2;
    public Activity_AddProducto() {
        myContext2 = this;
    }
    public static Activity_AddProducto getInstance() {
        return myContext2;
    }

}