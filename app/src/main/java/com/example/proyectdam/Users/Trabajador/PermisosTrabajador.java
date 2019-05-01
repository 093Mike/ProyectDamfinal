package com.example.proyectdam.Users.Trabajador;

import com.example.proyectdam.Users.Permiso;

public class PermisosTrabajador implements Permiso {

    private String tipo_Encargo;
    public PermisosTrabajador(String tipo_Encargo){
        this.tipo_Encargo = tipo_Encargo;
    }

    @Override
    public void permisosMenu() {
        switch (tipo_Encargo){
                case "Almacen":
                    PermisosTrabajador_Almacen pe_Almacen = new PermisosTrabajador_Almacen();
                    pe_Almacen.permisosMenu();
                    break;

            }
    }

    @Override
    public void permisosMenuUsers() {

    }
}
