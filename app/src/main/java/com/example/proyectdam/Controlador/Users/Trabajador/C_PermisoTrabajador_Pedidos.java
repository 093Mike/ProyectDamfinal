package com.example.proyectdam.Controlador.Users.Trabajador;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoTrabajador_Pedidos implements Permiso {

    @Override
    public int[] permisosMenu() { return new int[]{0,1,2,3,4,5}; }

    @Override
    public int[] permisosMenuUsers() { return new int[]{0}; }
}