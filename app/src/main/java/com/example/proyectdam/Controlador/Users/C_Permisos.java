package com.example.proyectdam.Controlador.Users;

import com.example.proyectdam.Controlador.Users.Admin.C_PermisoAdmin;
import com.example.proyectdam.Controlador.Users.Encagado.C_PermisoEncargado;
import com.example.proyectdam.Controlador.Users.SuperAdmin.C_PermisoSuperAdmin;
import com.example.proyectdam.Controlador.Users.Trabajador.C_PermisoTrabajador;
import com.example.proyectdam.Vista.Fragment_Menu.Activity_Menu;

public class C_Permisos {

    public int[] controlPermisos_menu() {
        switch (Activity_Menu.getInstance().permisos) {
            case "Super":
                C_PermisoSuperAdmin c_PermisoSuperAdmin = new C_PermisoSuperAdmin();
                return c_PermisoSuperAdmin.permisosMenu();
            case "Admin":
                C_PermisoAdmin c_PermisoAdmin = new C_PermisoAdmin();
                return c_PermisoAdmin.permisosMenu();
            case "Encargado":
                C_PermisoEncargado C_permisoEncargado= new C_PermisoEncargado(Activity_Menu.getInstance().tipo_encargo);
                return C_permisoEncargado.permisosMenu();
            case "Trabajador":
                C_PermisoTrabajador m_permisosTrabajador= new C_PermisoTrabajador(Activity_Menu.getInstance().tipo_encargo);
                return m_permisosTrabajador.permisosMenu();
        }
        return null;
    }

}
