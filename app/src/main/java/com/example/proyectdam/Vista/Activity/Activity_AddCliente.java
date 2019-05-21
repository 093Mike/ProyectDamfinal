package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectdam.Model.Cliente;
import com.example.proyectdam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_AddCliente extends AppCompatActivity {

    Button guardar;
    EditText nombre,telefono,direccion,ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertdialog_nuevocliente);
        setTitle("Añadir Cliente");
        guardar = findViewById(R.id.button7);
        nombre = findViewById(R.id.editText9);
        direccion = findViewById(R.id.editText10);
        telefono = findViewById(R.id.editText11);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().length()>0 &&
                   direccion.getText().toString().length()>0 &&
                   ciudad.getText().toString().length()>0 &&
                   telefono.getText().toString().length()>0) {
                    Cliente cliente = new Cliente(Cliente.id_proximo,
                            nombre.getText().toString(), Integer.parseInt(telefono.getText().toString()),
                            direccion.getText().toString(), ciudad.getText().toString());
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("clientes/" + cliente.getId());
                    reference.setValue(cliente);
                    finish();
                }
            }
        });
    }



}
