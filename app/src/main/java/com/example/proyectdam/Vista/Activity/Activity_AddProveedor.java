package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyectdam.Model.Proveedor;
import com.example.proyectdam.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_AddProveedor extends AppCompatActivity {
    Button guardar;
    EditText nombre,telefono,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alertdialog_nuevoproveedor);
        guardar = findViewById(R.id.button8);
        nombre = findViewById(R.id.editText12);
        email = findViewById(R.id.editText14);
        telefono = findViewById(R.id.editText15);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().length()>0 &&
                        email.getText().toString().length()>0 &&
                        telefono.getText().toString().length()>0) {
                    Proveedor proveedor = new Proveedor("PROV"+Proveedor.id_proximo,
                            nombre.getText().toString(), telefono.getText().toString(),
                            email.getText().toString());
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("proveedores/" + proveedor.getIdProveedor());
                    reference.setValue(proveedor);
                    finish();
                }
            }
        });


    }
}
