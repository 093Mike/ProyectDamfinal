package com.example.proyectdam.Vista.Activity;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.proyectdam.R;

public class ImagenProducto extends AppCompatActivity {
    // TODO: recibir imagen del otro activity
    private ImageView imagenProductoAmpliada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_producto);
//        imagenProductoAmpliada = findViewById(R.id.imageView_imagenProductoAmpliada);
//        byte[] imagen = getIntent().getByteArrayExtra("imagen");
//        imagenProductoAmpliada.setImageBitmap(BitmapFactory.decodeByteArray(imagen, 0, imagen.length));
    }
}
