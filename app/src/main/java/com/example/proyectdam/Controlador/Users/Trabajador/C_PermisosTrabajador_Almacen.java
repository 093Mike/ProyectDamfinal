package com.example.proyectdam.Controlador.Users.Trabajador;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.proyectdam.Controlador.Users.Permiso;
import com.example.proyectdam.Vista.Fragment_Menu.MenuUsers;
import com.example.proyectdam.R;

public class C_PermisosTrabajador_Almacen implements Permiso {

    private int[] permisos_Menu = new int[]{1};
    private int[] permisos_MenuUsers = new int[]{0};


    @Override
    public int[] permisosMenu() {
        return permisos_Menu;
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
