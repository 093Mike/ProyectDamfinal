package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.proyectdam.Controlador.Activitys.C_Activity_Olvidar;
import com.example.proyectdam.R;

public class PerdidoContrasenya extends AppCompatActivity {
    EditText email;
    C_Activity_Olvidar c_activity_olvidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidar);
        email = findViewById(R.id.editText6);
        c_activity_olvidar = new C_Activity_Olvidar();
    }

    public void olvidarContrasenya(View view) {
        c_activity_olvidar.olvidarContrasenya(email.getText().toString());
    }
}
