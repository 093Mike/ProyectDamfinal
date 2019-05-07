package com.example.proyectdam.Controlador.Users.Encagado;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoEncargado_Almacen implements Permiso {

    private int[] permisos_Menu = new int[]{0,2};
    private int[] permisos_MenuUsers = new int[]{0};


    @Override
    public int[] permisosMenu() {
        return permisos_Menu;
    }

    @Override
    public int[] permisosMenuUsers() { return permisos_MenuUsers; }
}
