package com.example.proyectdam.Controlador.Fragments;

import com.example.proyectdam.Vista.Activity.Activity_Menu;

public class C_Fragment_Menu {


    public boolean entrarAddUsers(){
        return Activity_Menu.getInstance().c_activity_menu.user.getPermisos().equals("Super");
    }
    public boolean verUsers(){
        return Activity_Menu.getInstance().c_activity_menu.user.getPermisos().equals("Admins");
    }
}
