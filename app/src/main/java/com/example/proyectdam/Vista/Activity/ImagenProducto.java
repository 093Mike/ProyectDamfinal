package com.example.proyectdam.Vista.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectdam.R;

public class ImagenProducto extends AppCompatActivity {
    private ImageView imagenProductoAmpliada;
    private TextView textView_infoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_producto);
        imagenProductoAmpliada = findViewById(R.id.imageView_imagenProductoAmpliada);
        textView_infoProducto = findViewById(R.id.textView_infoProducto);

        Bitmap bmp = BitmapFactory.decodeFile(getIntent().getStringExtra("rutaImagen"));
        imagenProductoAmpliada.setImageBitmap(bmp);
        textView_infoProducto.setText((String)getIntent().getStringExtra("productoString"));
    }
}
