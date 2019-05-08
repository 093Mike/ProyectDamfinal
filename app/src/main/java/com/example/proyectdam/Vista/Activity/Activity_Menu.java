package com.example.proyectdam.Vista.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Controlador.Fragments.C_Fragment_Menu;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;

public class Activity_Menu extends AppCompatActivity {
    public C_Activity_Menu c_activity_menu;
    public C_Fragment_Menu c_fragment_menu;
    public C_Almacen c_almacen;
    public String tag_escogido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        c_activity_menu = new C_Activity_Menu();
        c_fragment_menu = new C_Fragment_Menu();
        c_activity_menu.initialite();

        c_almacen = new C_Almacen();
        c_almacen.cargarAlmacenes();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


    public void gestioMenu(View view) {
        tag_escogido = ((String) view.getTag()).toUpperCase();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        IntentsMenu intentsMenu = new IntentsMenu();
        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(tag_escogido), "fragment_meters");
        ft.commit();
    }

    public void menuAlmacen(View v) {
        if (c_almacen.getAlmacenActual() == null) {
            c_almacen.alertDialog_escogeAlmacen(v);
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

    private static Activity_Menu myContext;

    public Activity_Menu() {
        myContext = this;
    }

    public static Activity_Menu getInstance() {
        return myContext;
    }

}
