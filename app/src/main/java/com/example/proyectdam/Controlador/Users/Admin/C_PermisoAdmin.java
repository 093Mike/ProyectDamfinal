package com.example.proyectdam.Controlador.Users.Admin;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoAdmin implements Permiso {
    @Override
    public int[] permisosMenu() {
        return new int[]{0};
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void permisosMenuUsers() {
//        for (int i = 0; i < MenuUsers.getInstance().options.length; i++) {
//            MenuUsers.getInstance().options[i].setVisibility(View.VISIBLE);
//            MenuUsers.getInstance().options[i].setBackgroundTintList(MenuUsers.getInstance().getResources().getColorStateList(R.color.color_denegado));
//        }
    }
}
