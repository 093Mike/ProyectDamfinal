package com.example.proyectdam.Model.Users;

import com.example.proyectdam.Vista.Fragment_Menu.Activity_Menu;

public class M_Permisos {

    public int[] controlPermisos_menu() {
        switch (Activity_Menu.getInstance().permisos) {
            case "Super":
                M_PermisosSuperAdmin MpermisoSuperAdmin = new M_PermisosSuperAdmin();
                return MpermisoSuperAdmin.gestionBotones_Fragment();
            case "Admin":
                M_PermisosAdmin MPermisoAdmin = new M_PermisosAdmin();
                return MPermisoAdmin.gestionBotones_Fragment();
            case "Encargado":
                M_PermisosEncargado m_permisoEncargado= new M_PermisosEncargado();
                return m_permisoEncargado.gestionBotones_Fragment();
            case "Trabajador":
                M_PermisosTrabajador m_permisosTrabajador= new M_PermisosTrabajador();
                return m_permisosTrabajador.gestionBotones_Fragment();

        }
        return null;
    }

}
