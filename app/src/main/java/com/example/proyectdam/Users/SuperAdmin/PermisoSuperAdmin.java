package com.example.proyectdam.Users.SuperAdmin;

import android.view.View;

import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.Users.Permiso;

public class PermisoSuperAdmin implements Permiso {

    @Override
    public void permisosMenu() {
        for (int i = 0; i < MenuPrincipal.getInstance().options.length; i++) {
            MenuPrincipal.getInstance().options[i].setVisibility(View.VISIBLE);
        }
    }

}
