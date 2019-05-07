package com.example.proyectdam.Vista.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Controlador.Fragments.C_Fragment_Menu;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Model.Almacen;
import com.example.proyectdam.R;

import java.util.ArrayList;

public class Activity_Menu extends AppCompatActivity {
    public C_Activity_Menu c_activity_menu;
    public C_Fragment_Menu c_fragment_menu;
    public C_Almacen c_almacen;
    public String tag_escogido;
    private ArrayList<Almacen> almacenes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        c_almacen = new C_Almacen();
        c_activity_menu = new C_Activity_Menu();
        c_fragment_menu = new C_Fragment_Menu();
        c_activity_menu.initialite();
        almacenes = new C_Almacen().getAlmacenesFirebase();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.getInstance().c_activityMain.setControl(false);
    }


    public void gestioMenu(View view) {
        tag_escogido = ((String) view.getTag()).toUpperCase();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        IntentsMenu intentsMenu = new IntentsMenu();
        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(tag_escogido), "fragment_meters");
        ft.commit();
    }

    public void menuAlmacen(View v) {
        String[] almacenesAlertDialog = new String[almacenes.size()];
        for (int i = 0; i < almacenes.size(); i++) {
            almacenesAlertDialog[i] = almacenes.get(i).getId()
                    + " - " + almacenes.get(i).getDireccion();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Menu.this);
        builder.setTitle("Escoge un almacén:")
                .setItems(almacenesAlertDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        IntentsMenu intentsMenu = new IntentsMenu();
                        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu("ALMACEN"), "fragment_meters");
                        ft.commit();
                    }
                });
        builder.show();

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

    private static Activity_Menu myContext;

    public Activity_Menu() {
        myContext = this;
    }

    public static Activity_Menu getInstance() {
        return myContext;
    }

}
