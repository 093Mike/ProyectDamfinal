package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.GestioUser.C_Activity_GestioUserModVer;
import com.example.proyectdam.R;

public class Activity_GestioUserModVer extends AppCompatActivity {

    TextView nombre,permisos;
    EditText e_permisos;
    TextView titulo;
    C_Activity_GestioUserModVer c_activity_gestioUserModVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menugestiomod_veruser);
        c_activity_gestioUserModVer = new C_Activity_GestioUserModVer();
        getSupportActionBar().hide();
        nombre = findViewById(R.id.textView2);
        permisos = findViewById(R.id.textView);
        e_permisos = findViewById(R.id.editText7);
        c_activity_gestioUserModVer.inicialite();
        nombre.setText(c_activity_gestioUserModVer.recibirNombre());
        permisos.setText(c_activity_gestioUserModVer.recibirPermisos());

        titulo = findViewById(R.id.textView);
        c_activity_gestioUserModVer.inicialite();
        titulo.setText(c_activity_gestioUserModVer.recibirNombre());

    }


    public void modUser(View view) {
        c_activity_gestioUserModVer.modificar(e_permisos.getText().toString());
    }
}
