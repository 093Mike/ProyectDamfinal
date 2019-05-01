package com.example.proyectdam.Users.Admin;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.proyectdam.Menu.GestioUsers.MenuUsers;
import com.example.proyectdam.R;
import com.example.proyectdam.Users.Permiso;

public class PermisoAdmin implements Permiso {
    @Override
    public void permisosMenu() {
        for (int i = 0; i < MenuUsers.getInstance().options.length; i++) {
            MenuUsers.getInstance().options[i].setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void permisosMenuUsers() {
        for (int i = 0; i < MenuUsers.getInstance().options.length; i++) {
            MenuUsers.getInstance().options[i].setVisibility(View.VISIBLE);
            MenuUsers.getInstance().options[i].setBackgroundTintList(MenuUsers.getInstance().getResources().getColorStateList(R.color.color_denegado));
        }
    }
}
