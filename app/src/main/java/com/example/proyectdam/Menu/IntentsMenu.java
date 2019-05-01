package com.example.proyectdam.Menu;

import android.content.Intent;

import com.example.proyectdam.Menu.GestioUsers.MenuAddUser;
import com.example.proyectdam.Menu.GestioUsers.MenuUsers;

public class IntentsMenu {

    Intent gestioIntent_Menu(String text_button){

        Intent intent = null;
        switch (text_button){
            case "ALMACEN":
                break;
            case "USUARIOS":
                intent = new Intent(MenuPrincipal.getInstance().getApplicationContext(), MenuUsers.class);
                break;
        }
        return intent;
    }

    public Intent gestioIntent_MenuUsers(String text_button){
        Intent intent = null;
        switch (text_button){
            case "AÑADIR USUARIO":
                intent = new Intent(MenuUsers.getInstance().getApplicationContext(), MenuAddUser.class);
                break;
        }
        return intent;
    }

}
