package com.example.proyectdam.Model.Users;

import com.example.proyectdam.Controlador.Users.Encagado.C_PermisoEncargado;
import com.example.proyectdam.Vista.Fragment_Menu.Activity_Menu;

public class M_PermisosEncargado implements Permiso_Model {
    @Override
    public int[] gestionBotones_Fragment() {
        C_PermisoEncargado c_permisoEncargado = new C_PermisoEncargado(Activity_Menu.getInstance().tipo_encargo);
        return c_permisoEncargado.permisosMenu();
    }
}
