package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.GestioUser.C_Activity_GestioUserModVer;
import com.example.proyectdam.R;

public class Activity_GestioUserModVer extends AppCompatActivity {

    TextView titulo;
    C_Activity_GestioUserModVer c_activity_gestioUserModVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menugestiomod_veruser);
        c_activity_gestioUserModVer = new C_Activity_GestioUserModVer();
        getSupportActionBar().hide();
        titulo = findViewById(R.id.textView);
        c_activity_gestioUserModVer.inicialite();
        titulo.setText(c_activity_gestioUserModVer.test());

    }


}
