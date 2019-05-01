package com.example.proyectdam.Users.Encagado;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.proyectdam.Users.Permiso;

public class PermisoEncargado implements Permiso {

    private String tipo_Encargo;
    public PermisoEncargado(String tipo_Encargo){
        this.tipo_Encargo = tipo_Encargo;
    }

    @Override
    public void permisosMenu() {
        switch (tipo_Encargo){
            case "Almacen":
                PermisoEncargado_Almacen pe_Almacen = new PermisoEncargado_Almacen();
                pe_Almacen.permisosMenu();
            break;

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void permisosMenuUsers() {
        switch (tipo_Encargo){
            case "Almacen":
                PermisoEncargado_Almacen pe_Almacen = new PermisoEncargado_Almacen();
                pe_Almacen.permisosMenuUsers();
                break;

        }
    }
}
