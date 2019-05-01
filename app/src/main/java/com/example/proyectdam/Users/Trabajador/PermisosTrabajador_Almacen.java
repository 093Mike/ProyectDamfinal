package com.example.proyectdam.Users.Trabajador;

import android.view.View;

import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.Users.Permiso;

public class PermisosTrabajador_Almacen implements Permiso {

    private int[] permisos_Menu = new int[]{0,1};
    private int[] permisos_MenuUsers = new int[]{0};


    @Override
    public void permisosMenu() {
        for (int permisos_menu : permisos_Menu) {
            MenuPrincipal.getInstance().options[permisos_menu].setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void permisosMenuUsers() {

    }
}
