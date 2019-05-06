package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.proyectdam.R;

public class Activity_GestioUserModVer extends AppCompatActivity {

    TextView titulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menugestiomod_veruser);
        getSupportActionBar().hide();

    }


}
