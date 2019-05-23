package com.example.proyectdam.Vista.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Model.Incidencia;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class Activity_AddIncidencia extends AppCompatActivity {

    private C_Almacen c_almacen;
    private Spinner spinner_incidencias_productos,
            spinner_incidencias_motivo;
    private EditText editText_incidencias_detalles,
            editText_incidencias_cantidad;
    private Button button_aceptar;

    private String motivoSeleccionado;
    private String productoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_incidencias);

        c_almacen = new C_Almacen();

        spinner_incidencias_productos = findViewById(R.id.spinner_incidencias_productos);
        spinner_incidencias_motivo = findViewById(R.id.spinner_incidencias_motivo);
        editText_incidencias_detalles = findViewById(R.id.editText_incidencias_detalles);
        editText_incidencias_cantidad = findViewById(R.id.editText_incidencias_cantidad);
        button_aceptar = findViewById(R.id.button_incidencias_ok);

        spinner_incidencias_productos.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dataSpinnerProductos()));
        spinner_incidencias_motivo.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, new String[]{"Rotura", "Devolución"}));

        spinner_incidencias_productos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String textSpinner = (String) parent.getItemAtPosition(position);
                productoSeleccionado = textSpinner.split(" - ")[0];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_incidencias_motivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                motivoSeleccionado = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Incidencia incidencia = new Incidencia(c_almacen.getAlmacenActual().getId(),
                        productoSeleccionado,
                        editText_incidencias_cantidad.getText().toString(),
                        motivoSeleccionado,
                        editText_incidencias_detalles.getText().toString());
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("incidencias/" + incidencia.getIdIncidencia());
                reference.setValue(incidencia);
                finish();
            }
        });
    }

    public ArrayList dataSpinnerProductos(){
        ArrayList<String> dataSpinner = new ArrayList<>();
        for (Producto p : C_Almacen.productos) {
            dataSpinner.add(p.getId() + " - " + p.getNombre());
        }
        return dataSpinner;
    }
}