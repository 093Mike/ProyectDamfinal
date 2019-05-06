package com.example.proyectdam.Controlador.Activitys.GestioUser;

import android.app.Activity;

import com.example.proyectdam.Controlador.Fragments.Users.AdaptadorUsers;
import com.example.proyectdam.Model.User;
import com.example.proyectdam.Vista.Fragment_Menu.GestioUsers.Fragment_GestioUsuarios;

import java.util.ArrayList;

public class C_Activity_GestioUserModVer extends Activity {

    int pos;
    ArrayList<User> users;

    public void inicialite(){
        pos = AdaptadorUsers.position;
        users = Fragment_GestioUsuarios.getInstance().c_fragment_gestioUsuarios.getUsers();
    }
    public String test(){
        return users.get(pos).getNombre();
    }


}
