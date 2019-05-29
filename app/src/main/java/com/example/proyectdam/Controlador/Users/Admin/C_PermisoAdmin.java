package com.example.proyectdam.Controlador.Users.Admin;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoAdmin implements Permiso {
    @Override
    public int[] permisosMenu() { return new int[]{0,1,2,3,4,5}; }

    @Override
    public int[] permisosMenuUsers() { return new int[]{0}; }

    @Override
    public int[] permisosAlmacen() { return new int[]{0,1,3,5,6}; }

    @Override
    public boolean permisosAlmacen_modificarProducto() { return false; }
}
