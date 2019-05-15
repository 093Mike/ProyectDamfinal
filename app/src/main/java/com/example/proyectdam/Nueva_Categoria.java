package com.example.proyectdam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Nueva_Categoria extends AppCompatActivity {

    EditText eTNuevaCategoria;
    Button btCrear;
    Button btSeleccionarImagen;
    static final String HOST = "192.168.43.95";
    static final int PUERTO = 5000;
    byte[] imagenBytes;
    String nombreImagen;
    InputStream is;
    OutputStream os;
    DataOutputStream envias;
    ObjectOutputStream enviaObject;
    DataInputStream recibir;
    FileOutputStream guardar;
    private static final int SELECT_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva__categoria);

        eTNuevaCategoria = findViewById(R.id.tvCrearC);
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
                            is = skCliente.getInputStream();
                            recibir = new DataInputStream(is);
                            envias = new DataOutputStream(os);

                            //enviamos peticion
                            envias.writeUTF("CREAR_CATEGORIA");

                            // enviamos el nombre de la categoria nueva

                            envias.writeUTF(eTNuevaCategoria.getText().toString());

                            //creamos el file con la ruta donde se pondran las categorias y los archivos.
                            File rutaNuevaCategoria = new File(getApplicationContext().getFilesDir()
                                    .getPath() + "/Productos/" + eTNuevaCategoria.getText().toString());
                            //creo las carpetas correspondientes;
                            rutaNuevaCategoria.mkdirs();

                            File dirImagenesCat = new File(getApplicationContext().getFilesDir()
                                    .getPath() + "/Productos/Imagenes Categoria");
                                dirImagenesCat.mkdirs();


                            //guardamos  la imagen en la aplicacion del movil
                            guardar = new FileOutputStream(getApplicationContext().getFilesDir()
                                    .getPath() + "/Productos/Imagenes Categoria/" +eTNuevaCategoria.getText().toString() + ".JPEG");
                            guardar.write(imagenBytes);

                            // String extension = nombreImagen.substring(nombreImagen.lastIndexOf("."));
                            //nombre de la imagen de la categoria con su extension (cada imagen se llamara como su categoria)
                            //  String img = btCrear.getText().toString() + extension;
                            //enviamos el nombre de la imagen que equivale a las 3 primeras letras de la categoria en mayusculas
                            String img = (eTNuevaCategoria.getText().toString().substring(0,3)).toUpperCase()+".JPEG";

                            //envio en nombre de la imagen
                            envias.writeUTF(img);
                            //enviamos la longitud array de bites y el array de bytes.
                            envias.writeInt(imagenBytes.length);
                            envias.write(imagenBytes);

                         //FALTA PONER REGISTRO EN LA BASE DE DATOS CON LA NUEVA CATEGORIA.



                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Nueva_Categoria.this, "Categoria creada correctamente", Toast.LENGTH_LONG).show();
                                    }
                                });
                                finish();


                        } catch (IOException e) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Nueva_Categoria.this, "Error al crear la categoria", Toast.LENGTH_LONG).show();

                                }
                            });
                            finish();
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

    public void abrirGaleria(View v) {
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
                    nombreImagen = selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {

                        if (nombreImagen != null) {
                            InputStream imageStream = null;
                            try {

                                imageStream = getContentResolver().openInputStream(
                                        selectedImage);
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
                            //    ImageView mImg = (ImageView) findViewById(R.id.ivImagen);
                            //    mImg.setImageBitmap(bmp);


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


    public byte[] recibirArraB(DataInputStream recibe) throws IOException {
         int longitud = recibe.readInt();

            byte[] imagen = new byte[longitud];
            int leido=0;

            while(leido<longitud){

               int nl= recibe.read(imagen,leido,longitud-leido) ;

            }
            return imagen;

    }
}
