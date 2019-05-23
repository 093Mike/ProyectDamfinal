package com.example.proyectdam.Vista.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.R;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Nueva_Categoria extends AppCompatActivity {
    private C_Almacen c_almacen;
    EditText eTNuevaCategoria;
    Button btCrear;
    Button btSeleccionarImagen;

    byte[] imagenBytes;
    String nombreImagen;

    private static final int SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_categoria);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        c_almacen = new C_Almacen();
        eTNuevaCategoria = findViewById(R.id.tvCrearC);
        btCrear = findViewById(R.id.btCrearCat);
        btSeleccionarImagen = findViewById(R.id.btSeleccionarImg);

        btSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria(v);
            }
        });

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eTNuevaCategoria.getText().toString().length() < 4) {
                    Toast.makeText(Nueva_Categoria.this, "Error, el nombre de la categoria debe tener minimo 3 caracteres", Toast.LENGTH_LONG).show();
                } else {
                    c_almacen.menuAlmacen_crearCategoria(Nueva_Categoria.this, eTNuevaCategoria.getText().toString());
                }
            }
        });
    }

    public void abrirGaleria(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), SELECT_FILE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImage;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = imageReturnedIntent.getData();
                    nombreImagen = selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {
                        if (nombreImagen != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                            Bitmap imagenBitMP = redimensionarImagenMaximo(bmp, 640, 240);
                            //creamos flujo de datos
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            //le cambiamos el formato a la imagen
                            imagenBitMP.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            btSeleccionarImagen.setText(nombreImagen);
                            //creamos el array de bytes de la imagen
                            //  imagenBytes= new byte[stream.toByteArray().length];
                            imagenBytes = stream.toByteArray();
                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                                ImageView mImg = (ImageView) findViewById(R.id.imageView_nuevaCategoria_image);
                                mImg.setImageBitmap(bmp);
                        }
                    }
                }
                break;
        }
    }

    public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth) {
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }
}