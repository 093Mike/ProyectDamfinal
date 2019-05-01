package com.example.proyectdam.Users.Encagado;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.proyectdam.Menu.GestioUsers.MenuUsers;
import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.R;
import com.example.proyectdam.Users.Permiso;

public class PermisoEncargado_Almacen implements Permiso {

    private int[] permisos_Menu = new int[]{0,1};
    private int[] permisos_MenuUsers = new int[]{0};


    @Override
    public void permisosMenu() {
        for (int permisos_menu : permisos_Menu) {
            MenuPrincipal.getInstance().options[permisos_menu].setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void permisosMenuUsers() {
        for (int permisos_menuUser : permisos_MenuUsers) {
            MenuUsers.getInstance().options[permisos_menuUser].setVisibility(View.VISIBLE);
            MenuUsers.getInstance().options[permisos_menuUser].setBackgroundTintList(MenuUsers.getInstance().getResources().getColorStateList(R.color.color_denegado));
        }
    }
}
