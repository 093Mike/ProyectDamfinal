package com.example.proyectdam.Vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.proyectdam.R;

public class PerdidoContrasenya extends AppCompatActivity {
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvidar);
        email = findViewById(R.id.editText6);
    }

    public void olvidarContrasenya(View view) {
        MainActivity.getInstance().c_activityMain.getmAuth().sendPasswordResetEmail(email.getText().toString());
    }
}
