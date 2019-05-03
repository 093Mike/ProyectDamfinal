package com.example.proyectdam.Controlador;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.proyectdam.Vista.Fragment_Menu.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Menu.MenuUser_gestor;
import com.example.proyectdam.Vista.Fragment_Menu.Fragment_GestioUsuarios;

public class IntentsMenu {

    public Fragment gestioIntent_Menu(String text_button){

        Fragment intent = null;
        switch (text_button){
            case "ALMACEN":

                break;
            case "USUARIOS":
                intent = new Fragment_GestioUsuarios();
                break;
        }
        return intent;
    }

    public Intent gestioIntent_MenuUsers(String text_button){
        Intent intent = null;
        switch (text_button){
            case "AÑADIR USUARIO":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), MenuUser_gestor.class);
                break;
        }
        return intent;
    }

}