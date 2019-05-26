package com.example.proyectdam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.proyectdam.Vista.MainActivity;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        startActivity(new Intent(Start.this , MainActivity.class));
        finish();
    }
}
