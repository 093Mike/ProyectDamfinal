package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AddProducto extends AppCompatActivity {
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

    private static Context myContext;
    private String pasilloSeleccionado,
            estanteriaSeleccionada,
            categoriaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        myContext = this;
        c_almacen = new C_Almacen();

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
        spinner_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSeleccionada = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_pasillo.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, c_almacen.getAlmacenActual().getPasillos()));
        spinner_pasillo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pasilloSeleccionado = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_estanteria.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, c_almacen.getAlmacenActual().getEstanterias()));
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

        imageView_imagenProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_almacen.alertDialog_setImagenProductoNuevo();
            }
        });

        button_subirProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = new Producto(editText_nombreProducto.getText().toString().trim(),
                        editText_descripcion.getText().toString().trim(),
                        new Categoria(categoriaSeleccionada),
                        c_almacen.getAlmacenActual(),
                        pasilloSeleccionado + " - " + estanteriaSeleccionada,
                        Double.parseDouble(editText_stock.getText().toString().trim()),
                        editText_proveedor.getText().toString().trim(),
                        Double.parseDouble(editText_precioProveedor.getText().toString().trim()),
                        Double.parseDouble(editText_pvp.getText().toString().trim()));

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("productos/" + p.getId());
                reference.setValue(p);
            }
        });
    }

    public static Context getInstance() {
        return myContext;
    }
}
