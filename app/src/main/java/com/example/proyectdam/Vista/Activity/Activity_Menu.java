package com.example.proyectdam.Vista.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Controlador.Fragments.C_Fragment_Menu;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Clientes.Activity_AddCliente;
import com.example.proyectdam.Vista.Activity.Proveedores.Activity_AddProveedor;
import com.example.proyectdam.Vista.Fragment_Menu.Fragment_Menu;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.example.proyectdam.Vista.MainActivity;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Activity_Menu extends AppCompatActivity {
    public C_Activity_Menu c_activity_menu;
    public C_Fragment_Menu c_fragment_menu;
    public C_Almacen c_almacen;
    public String tag_escogido;
    static final String HOST = "192.168.1.109";
    static final int PUERTO = 5000;
    InputStream is;
    OutputStream os;
    DataOutputStream envia;
    ObjectInputStream recibir;
    private HashMap<String, byte[]> dirCategoria = new HashMap();
    IntentsMenu intentsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c_activity_menu = new C_Activity_Menu();
        c_fragment_menu = new C_Fragment_Menu();
        c_activity_menu.initialite();
        intentsMenu = new IntentsMenu();
        try {
            setContentView(R.layout.activity_menu);
        }
        catch (Exception e){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            IntentsMenu intentsMenu = new IntentsMenu();
            ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu("VACIO"), "fragment_meters");
            ft.commit();
        }
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        c_almacen = new C_Almacen();
        c_almacen.cargarAlmacenesApp();
        c_almacen.cargarProveedoresApp();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void gestioMenu(View view) {
        tag_escogido = ((String) view.getTag()).toUpperCase();
        for (int i=0 ; i < Fragment_Menu.options.length ; i++){
            Fragment_Menu.options[i].setClickable(true);
            Fragment_Menu.options[i].setBackgroundTintList(getResources().getColorStateList(R.color.color_normal));
        }
        view.setClickable(false);
        view.setBackgroundTintList(getResources().getColorStateList(R.color.color_normal_noclick));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        IntentsMenu intentsMenu = new IntentsMenu();
        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(tag_escogido), "fragment_meters");
        ft.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void menuAlmacen(View v) {
        for (int i=0 ; i < Fragment_Menu.options.length ; i++){
            Fragment_Menu.options[i].setClickable(true);
            Fragment_Menu.options[i].setBackgroundTintList(getResources().getColorStateList(R.color.color_normal));
        }
        v.setClickable(false);
        v.setBackgroundTintList(getResources().getColorStateList(R.color.color_normal_noclick));
        if (c_almacen.getAlmacenActual() == null) {
            c_almacen.alertDialog_escogeAlmacen(v);
        }
        else{
            tag_escogido = ((String) v.getTag()).toUpperCase();
            FragmentTransaction ft = Activity_Menu.getInstance().getSupportFragmentManager().beginTransaction();
            IntentsMenu intentsMenu = new IntentsMenu();
            ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(tag_escogido), "fragment_meters");
            ft.commit();
        }
    }

    public void selecionarPedido(View view){
        int tipo = c_activity_menu.selecionarPedido();
        if(tipo == 1){
            startActivity(intentsMenu.gestioIntent("MV_Pedido"));

        }
        else{
            startActivity(intentsMenu.gestioIntent("MV_Mod_Pedido"));
        }
    }

    public void gestioaddUsers(View view) {
        if (c_fragment_menu.entrarAddUsers()) {
            String tag = (String) view.getTag();
            IntentsMenu intentsMenu = new IntentsMenu();
            startActivity(intentsMenu.gestioIntent(tag));
        } else {
            Toast.makeText(myContext, "No tienes permisos para entrar a ese sitio.", Toast.LENGTH_SHORT).show();
        }
    }

    public void powerOff(View view) {
        MainActivity.getInstance().c_activityMain.getmAuth().signOut();
        MainActivity.getInstance().c_activityMain.control = false;
        finish();
    }

    public void gestioFiltro(View view) {
        Fragment_MenuPedidos.getInstance().gestioFiltro(view);
    }


    public void ActualizarImagenes(View vista) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket skCliente = null;


                try {
                    skCliente = new Socket(HOST, PUERTO);
                    os = skCliente.getOutputStream();
                    envia = new DataOutputStream(os);


                    //enviamos peticion
                    envia.writeUTF("ACTUALIZAR_IMAGENES");
                    //borramos la carpeta productos con todas las categorias dentro
                    Log.d("asd", getApplicationContext().getFilesDir()
                            .getPath());
                    File rutaProductos = new File(getApplicationContext().getFilesDir()
                            .getPath() + "/Productos");
                     rutaProductos.mkdirs();
                    recursiveDelete(rutaProductos);

                    is = skCliente.getInputStream();
                    recibir = new ObjectInputStream(is);


                    //recibimos el numero de categorias que recibiremos.
                    int numCategorias = recibir.readInt();

                    for (int i = 0; i < numCategorias; i++) {
                        String categoria = recibir.readUTF();

                        //creamos el file con la ruta donde se pondran las categorias y los archivos.
                        File rutaCategoria = new File(getApplicationContext().getFilesDir()
                                .getPath() + "/Productos/" + categoria);

                        //creo las carpetas correspondientes;
                        rutaCategoria.mkdirs();
                        dirCategoria = (HashMap) recibir.readObject();


                        //metemos las imagenes del hashmap en la carpeta.
                        for (Map.Entry<String, byte[]> entrada : dirCategoria.entrySet()) {

                            //creamos   fileoutputstram con la ruta qu ele corresponde para guardar las imagenes
                            FileOutputStream guardar = new FileOutputStream(rutaCategoria + "/" + entrada.getKey());
                            guardar.write(entrada.getValue());

                        }
                     String[] a=   rutaCategoria.list();

                        for (int j = 0; j < a.length; j++) {
                            Log.d("aaa",categoria+": "+a[j]);
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Menu.this, "Imagenes actualizadas", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            //funcion que borra_todo lo que haya dentro de un directorio.
            private void recursiveDelete(File file) {
                //to end the recursive loop
                if (!file.exists())
                    return;

                //if directory, go inside and call recursively
                if (file.isDirectory()) {
                    for (File f : file.listFiles()) {
                        //call recursively
                        recursiveDelete(f);
                    }
                }
                //call delete to delete files and empty directory
                file.delete();
                System.out.println("Deleted file/folder: " + file.getAbsolutePath());
            }
        }).start();


    }

    public void NuevaCategoria(View view) {
        IntentsMenu intentsMenu = new IntentsMenu();
        startActivity(intentsMenu.gestioIntent(view.getTag().toString()));
    }

    public void gestioAddCliente(View view) {
        if (c_activity_menu.controlClientes()) {
            startActivity(new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_AddCliente.class));
        } else {
            Toast.makeText(this, "No cumples los requisitos para añadir clientes nuevos", Toast.LENGTH_SHORT).show();
        }
    }

    public void gestioAddProv(View view) {
        if (c_activity_menu.controlProveedores()) {
            startActivity(new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_AddProveedor.class));
        } else {
            Toast.makeText(this, "No cumples los requisitos para añadir proveedores nuevos", Toast.LENGTH_SHORT).show();
        }

    }

    public void gestionAddPedido(View view) {
        if (c_activity_menu.controlAddPerdidos()) {
            Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.anadirPedido();
        } else {
            Toast.makeText(this, "No cumples los requisitos para añadir pedidos nuevos", Toast.LENGTH_SHORT).show();
        }
    }

    private static Activity_Menu myContext;

    public Activity_Menu() {
        myContext = this;
    }
    public static Activity_Menu getInstance() {
        return myContext;
    }

}

