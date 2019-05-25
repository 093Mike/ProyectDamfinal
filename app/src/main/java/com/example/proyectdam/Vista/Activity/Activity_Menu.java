package com.example.proyectdam.Vista.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Controlador.Fragments.C_Fragment_Menu;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Menu.Fragment_Menu;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.example.proyectdam.Vista.MainActivity;

public class Activity_Menu extends AppCompatActivity {
    public C_Activity_Menu c_activity_menu;
    public C_Fragment_Menu c_fragment_menu;
    public C_Almacen c_almacen;
    public String tag_escogido;

<<<<<<< HEAD
=======
>>>>>>> master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
<<<<<<< HEAD
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        c_activity_menu = new C_Activity_Menu();
        c_fragment_menu = new C_Fragment_Menu();
        c_activity_menu.initialite();
=======

>>>>>>> master
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

    public void gestioaddUsers(View view) {
        if (c_fragment_menu.entrarAddUsers()) {
            String tag = (String) view.getTag();
            IntentsMenu intentsMenu = new IntentsMenu();
            startActivity(new Intent(this, MenuUser_add.class));
        } else {
            Toast.makeText(myContext, "No tienes permisos para entrar a ese sitio.", Toast.LENGTH_SHORT).show();
        }
    }

    public void powerOff(View view) {
        MainActivity.getInstance().c_activityMain.getmAuth().signOut();
        MainActivity.getInstance().c_activityMain.control = false;
//        MainActivity.getInstance().c_activityMain.setmAuth(null);
        finish();
    }

    public void gestioFiltro(View view) {
        Fragment_MenuPedidos.getInstance().gestioFiltro(view);
    }

    public void gestionAddPedido(View view) {
        Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.anadirPedido();
    }

<<<<<<< HEAD
    private static Activity_Menu myContext;

=======
>>>>>>> master
    public Activity_Menu() {
        myContext = this;
    }
    public static Activity_Menu getInstance() {
        return myContext;
    }
<<<<<<< HEAD
=======
}
>>>>>>> master
