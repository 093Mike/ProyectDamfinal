package com.example.proyectdam.Controlador;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.proyectdam.Vista.Activity.ListaCategorias;
import com.example.proyectdam.Vista.Fragment_Almacen.MenuAlmacen;
import com.example.proyectdam.Vista.Activity.Activity_GestioUserModVer;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.MenuUser_add;
import com.example.proyectdam.Vista.Fragment_Menu.GestioUsers.Fragment_GestioUsuarios;

public class IntentsMenu {

    public Fragment gestioIntent_Menu(String text_button){

        Fragment intent = null;
        switch (text_button){
            case "ALMACEN":
                intent = new MenuAlmacen();
                break;
            case "USUARIOS":
                intent = new Fragment_GestioUsuarios();
                break;
        }
        return intent;
    }

    public Intent gestioIntent(String text_button){
        Intent intent = null;
        switch (text_button){
            // USUARIOS
            case "AÑADIR USUARIO":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), MenuUser_add.class);
                break;
            // ALMACEN
            case "INVENTARIO ALMACEN":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), ListaCategorias.class);
                break;
            case "MV_User":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_GestioUserModVer.class);
                break;
        }
        return intent;
    }
}
