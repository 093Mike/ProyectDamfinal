package com.example.proyectdam.Model.Users;

import com.example.proyectdam.Controlador.Users.Trabajador.C_PermisosTrabajador;
import com.example.proyectdam.Vista.Fragment_Menu.Activity_Menu;

public class M_PermisosTrabajador implements Permiso_Model {
    @Override
    public int[] gestionBotones_Fragment() {
        C_PermisosTrabajador c_permisosTrabajador = new C_PermisosTrabajador(Activity_Menu.getInstance().tipo_encargo);
        return c_permisosTrabajador.permisosMenu();

    }
}
