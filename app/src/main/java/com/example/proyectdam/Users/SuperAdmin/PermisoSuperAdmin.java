package com.example.proyectdam.Users.SuperAdmin;

import android.view.View;

import com.example.proyectdam.Menu.GestioUsers.MenuUsers;
import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.Users.Permiso;

public class PermisoSuperAdmin implements Permiso {

    @Override
    public void permisosMenu() {
        for (int i = MenuPrincipal.getInstance().options.length - 1; i >= 0; i--) {
            MenuPrincipal.getInstance().options[i].setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void permisosMenuUsers() {
        for (int i = 0; i < MenuUsers.getInstance().options.length; i++) {
            MenuUsers.getInstance().options[i].setVisibility(View.VISIBLE);
        }
    }


}
