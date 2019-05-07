package com.example.proyectdam.Controlador.Activitys.GestioUser;

import android.app.Activity;

import com.example.proyectdam.Controlador.Fragments.Users.AdaptadorUsers;
import com.example.proyectdam.Model.User;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Menu.GestioUsers.Fragment_GestioUsuarios;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class C_Activity_GestioUserModVer extends Activity {

    int pos;
    ArrayList<User> users;

    public void inicialite(){
        pos = AdaptadorUsers.position;
        users = Fragment_GestioUsuarios.getInstance().c_fragment_gestioUsuarios.getUsers();
    }
    public String recibirNombre(){
        return users.get(pos).getNombre();
    }
    public String recibirPermisos(){
        return users.get(pos).getPermisos();
    }

    public void modificar(String permisos){
        String uid = users.get(pos).getUid();
        DatabaseReference myRef = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("users/" + uid + "/user");
        myRef.child("Permisos").setValue(permisos);
        }
    }



