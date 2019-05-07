package com.example.proyectdam.Vista.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Controlador.Fragments.C_Fragment_Menu;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Activity_Menu extends AppCompatActivity {
    public C_Activity_Menu c_activity_menu;
    public C_Fragment_Menu c_fragment_menu;
    public String tag_escogido;

    static final String HOST = "192.168.43.95";
    static final int PUERTO = 5000;
    InputStream is;
    OutputStream os;
    DataOutputStream envia;
    ObjectInputStream recibir;
    private HashMap radiocontrol = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        c_activity_menu =  new C_Activity_Menu();
        c_fragment_menu = new C_Fragment_Menu();
        c_activity_menu.initialite();

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        MainActivity.getInstance().c_activityMain.setControl(false);
    }


    public void gestioMenu(View view){
        tag_escogido = ((String) view.getTag()).toUpperCase();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        IntentsMenu intentsMenu = new IntentsMenu();
        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(tag_escogido) , "fragment_meters" );
        ft.commit();
    }

    public void gestioaddUsers(View view){
        if(c_fragment_menu.entrarAddUsers()) {
            String tag = (String) view.getTag();
            IntentsMenu intentsMenu = new IntentsMenu();
            startActivity(new Intent(this, MenuUser_add.class));
        }
        else{
            Toast.makeText(myContext, "No tienes permisos para entrar a ese sitio.", Toast.LENGTH_SHORT).show();
        }
    }

    public void ActualizarImagenes(View vista){

        Socket skCliente = null;


        try {
            skCliente = new Socket(HOST,PUERTO);
            is =  skCliente.getInputStream();
            recibir =  new ObjectInputStream(is);
            os =skCliente.getOutputStream();

          //recibimos el numero de categorias que recibiremos.
            int numCategorias = recibir.readInt();

            for (int i=0;i<numCategorias;i++) {
                String categoria = recibir.readUTF();

                //creamos el file con la ruta donde se pondran las categorias y los archivos.
                File rutaCategoria = new File(getApplicationContext().getFilesDir().getPath()+"\\Productos\\"+categoria);
                 //creo las carpetas correspondientes;
                  rutaCategoria.mkdirs();
                radiocontrol= (HashMap) recibir.readObject();

                //teno que hacer  fileoutputstram con la ruta qu ele corresponde para guardar las imagenes
              //  FileOutputStream guardar = new FileOutputStream(DirProductos + "\\" + categoria);
              //  guardar.write(imagen);

                //metemos las imagenes del hashmap en la carpeta.

                for(int j=0; j<radiocontrol.size();j++){





                }


            }



        } catch (IOException e ) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private static Activity_Menu myContext;
    public Activity_Menu() { myContext = this; }
    public static Activity_Menu getInstance() { return myContext; }

}
