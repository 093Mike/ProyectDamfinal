package com.example.proyectdam.Model.Users;

import com.example.proyectdam.Controlador.Users.SuperAdmin.C_PermisoSuperAdmin;

class M_PermisosAdmin implements Permiso_Model{

    public int[] gestionBotones_Fragment() {
        C_PermisoSuperAdmin c_permisoSuperAdmin = new C_PermisoSuperAdmin();
        return c_permisoSuperAdmin.permisosMenu();
    }


}
