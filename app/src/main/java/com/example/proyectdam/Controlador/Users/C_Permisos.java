package com.example.proyectdam.Controlador.Users;

import com.example.proyectdam.Controlador.Users.Admin.C_PermisoAdmin;
import com.example.proyectdam.Controlador.Users.Encagado.C_PermisoEncargado;
import com.example.proyectdam.Controlador.Users.SuperAdmin.C_PermisoSuperAdmin;
import com.example.proyectdam.Controlador.Users.Trabajador.C_PermisoTrabajador;
import com.example.proyectdam.Vista.Activity.Activity_Menu;

public class C_Permisos implements Permiso {

    public int[] permisosMenu() {
        switch (Activity_Menu.getInstance().c_activity_menu.user.getPermisos()) {
            case "Super":
                C_PermisoSuperAdmin c_PermisoSuperAdmin = new C_PermisoSuperAdmin();
                return c_PermisoSuperAdmin.permisosMenu();
            case "Admin":
                C_PermisoAdmin c_PermisoAdmin = new C_PermisoAdmin();
                return c_PermisoAdmin.permisosMenu();
            case "Encargado":
                C_PermisoEncargado C_permisoEncargado= new C_PermisoEncargado(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return C_permisoEncargado.permisosMenu();
            case "Trabajador":
                C_PermisoTrabajador m_permisosTrabajador= new C_PermisoTrabajador(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return m_permisosTrabajador.permisosMenu();
        }
        return null;
    }

    public int[] permisosMenuUsers(){
        switch (Activity_Menu.getInstance().c_activity_menu.user.getPermisos()) {
            case "Super":
                C_PermisoSuperAdmin c_PermisoSuperAdmin = new C_PermisoSuperAdmin();
                return c_PermisoSuperAdmin.permisosMenuUsers();
            case "Admin":
                C_PermisoAdmin c_PermisoAdmin = new C_PermisoAdmin();
                return c_PermisoAdmin.permisosMenuUsers();
            case "Encargado":
                C_PermisoEncargado C_permisoEncargado= new C_PermisoEncargado(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return C_permisoEncargado.permisosMenuUsers();
            case "Trabajador":
                C_PermisoTrabajador m_permisosTrabajador= new C_PermisoTrabajador(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return m_permisosTrabajador.permisosMenuUsers();
        }

        return null;
    }

    @Override
    public int[] permisosAlmacen() {
        switch (Activity_Menu.getInstance().c_activity_menu.user.getPermisos()) {
            case "Super":
                C_PermisoSuperAdmin c_PermisoSuperAdmin = new C_PermisoSuperAdmin();
                return c_PermisoSuperAdmin.permisosAlmacen();
            case "Admin":
                C_PermisoAdmin c_PermisoAdmin = new C_PermisoAdmin();
                return c_PermisoAdmin.permisosAlmacen();
            case "Encargado":
                C_PermisoEncargado C_permisoEncargado= new C_PermisoEncargado(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return C_permisoEncargado.permisosAlmacen();
            case "Trabajador":
                C_PermisoTrabajador m_permisosTrabajador= new C_PermisoTrabajador(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return m_permisosTrabajador.permisosAlmacen();
        }
        return null;
    }

    @Override
    public boolean permisosAlmacen_modificarProducto() {
        switch (Activity_Menu.getInstance().c_activity_menu.user.getPermisos()) {
            case "Super":
                C_PermisoSuperAdmin c_PermisoSuperAdmin = new C_PermisoSuperAdmin();
                return c_PermisoSuperAdmin.permisosAlmacen_modificarProducto();
            case "Admin":
                C_PermisoAdmin c_PermisoAdmin = new C_PermisoAdmin();
                return c_PermisoAdmin.permisosAlmacen_modificarProducto();
            case "Encargado":
                C_PermisoEncargado C_permisoEncargado= new C_PermisoEncargado(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return C_permisoEncargado.permisosAlmacen_modificarProducto();
            case "Trabajador":
                C_PermisoTrabajador m_permisosTrabajador= new C_PermisoTrabajador(Activity_Menu.getInstance().c_activity_menu.user.getTipo_permisos());
                return m_permisosTrabajador.permisosAlmacen_modificarProducto();
        }
        return false;
    }

}
