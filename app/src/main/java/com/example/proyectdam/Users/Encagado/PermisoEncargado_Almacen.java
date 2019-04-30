package com.example.proyectdam.Users.Encagado;

import android.view.View;

import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.Users.Permiso;

public class PermisoEncargado_Almacen implements Permiso {

    int[] permisos_Menu = new int[]{0};

    @Override
    public void permisosMenu() {
        for (int i = 0; i < permisos_Menu.length; i++) {
            MenuPrincipal.getInstance().options[permisos_Menu[i]].setVisibility(View.VISIBLE);
        }
    }
}
