package com.example.proyectdam.Controlador.Users.SuperAdmin;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.proyectdam.Controlador.Users.Permiso;
import com.example.proyectdam.Vista.Fragment_Menu.MenuUsers;
import com.example.proyectdam.R;

public class C_PermisoSuperAdmin implements Permiso {

    @Override
    public int[] permisosMenu() {
        return new int[]{0,1};
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void permisosMenuUsers() {
        for (int i = 0; i < MenuUsers.getInstance().options.length; i++) {
            MenuUsers.getInstance().options[i].setVisibility(View.VISIBLE);
            MenuUsers.getInstance().options[i].setBackgroundTintList(MenuUsers.getInstance().getResources().getColorStateList(R.color.color_normal));
        }
    }


}
