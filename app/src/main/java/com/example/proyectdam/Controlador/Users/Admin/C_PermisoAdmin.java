package com.example.proyectdam.Controlador.Users.Admin;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoAdmin implements Permiso {
    @Override
    public int[] permisosMenu() {
        return new int[]{0,1,2};
    }

    @Override
    public int[] permisosMenuUsers() { return new int[]{0}; }
}
