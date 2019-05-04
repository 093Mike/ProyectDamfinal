package com.example.proyectdam.Vista.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Menu.GestioUsers.MenuUser_gestor;

public class Activity_Menu extends AppCompatActivity {
    public C_Activity_Menu c_activity_menu;
    public String tag_escogido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        c_activity_menu =  new C_Activity_Menu();
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
//        String tag = (String) view.getTag();
        IntentsMenu intentsMenu = new IntentsMenu();
        startActivity(new Intent(this, MenuUser_gestor.class));
    }

    private static Activity_Menu myContext;
    public Activity_Menu() { myContext = this; }
    public static Activity_Menu getInstance() { return myContext; }

}
