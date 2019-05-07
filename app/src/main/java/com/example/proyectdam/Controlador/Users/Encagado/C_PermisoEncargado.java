package com.example.proyectdam.Controlador.Users.Encagado;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoEncargado implements Permiso {

    private String tipo_Encargo;
    public C_PermisoEncargado(String tipo_Encargo){
        this.tipo_Encargo = tipo_Encargo;
    }

    @Override
    public int[] permisosMenu() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosMenu();

        }
    return null;
    }

    @Override
    public int[] permisosMenuUsers() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosMenuUsers();

        }
        return null;
    }
}
