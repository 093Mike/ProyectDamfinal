package com.example.proyectdam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Nueva_Categoria extends AppCompatActivity {

    EditText crear;
    Button btCrear;
    Button btSeleccionarImagen;
    static final String HOST = "192.168.43.95";
    static final int PUERTO = 5000;
    InputStream is;
    OutputStream os;
    DataOutputStream envia;
    DataInputStream recibir;
    private static final int SELECT_FILE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva__categoria);

        crear = findViewById(R.id.tvCrearC);
        btCrear = findViewById(R.id.btCrearCat);
        btSeleccionarImagen = findViewById(R.id.btSeleccionarImg);
        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        Socket skCliente = null;


                        try {
                            skCliente = new Socket(HOST, PUERTO);
                            os = skCliente.getOutputStream();
                            envia = new DataOutputStream(os);
                            is = skCliente.getInputStream();
                            recibir = new DataInputStream(is);


                            //enviamos peticion
                            envia.writeUTF("CREAR_CATEGORIA");
                            envia.writeUTF(btCrear.getText().toString());

                            //creamos el file con la ruta donde se pondran las categorias y los archivos.
                            File rutaNuevaCategoria = new File(getApplicationContext().getFilesDir()
                                    .getPath() + "/Productos/" + btCrear.getText().toString());

                            //creo las carpetas correspondientes;
                            rutaNuevaCategoria.mkdirs();


                            //FALTA PONER REGISTRO EN LA BASE DE DATOS CON LA NUEVA CATEGORIA.


                            int realizado = recibir.readInt();

                            if (realizado == 1) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Nueva_Categoria.this, "Categoria creada correctamente", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();
            }
        });
        btSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 abrirGaleria(v);

            }
        });

    }

    public void abrirGaleria(View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, "Seleccione una imagen"),
                SELECT_FILE);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        Uri selectedImageUri = null;
        Uri selectedImage;

        String filePath = null;
        switch (requestCode) {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {

                    selectedImage = imageReturnedIntent.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {

                                imageStream = getContentResolver().openInputStream(
                                        selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                            //CAMBIARLE EL tamaño al bitmap que hace referencia a la imagen
                            //  CREAR EL HILO PARA ENVIAR LA IMAGEN AL SERVIDOR


                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                        //    ImageView mImg = (ImageView) findViewById(R.id.ivImagen);
                        //    mImg.setImageBitmap(bmp);

                            //crear el hilo
                        }
                    }
                }
                break;
        }
    }
}
