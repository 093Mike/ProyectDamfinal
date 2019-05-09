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
    public boolean userActual;
    ArrayList<User> users;

    public void inicialite(){
        userActual = false;
        pos = AdaptadorUsers.position;
        users = Fragment_GestioUsuarios.getInstance().c_fragment_gestioUsuarios.getUsers();
        if(pos == 0){
            userActual = true;
        }
    }
    public boolean isSuper(){ return recibirPermisos().equals("Super");}
    public boolean isAlTra(){ return recibirPermisos().equals("Encargado") || recibirPermisos().equals("Trabajador"); }
    public String recibirNombre(){
        return users.get(pos).getNombre();
    }
    public String recibirPermisos(){
        return users.get(pos).getPermisos();
    }
    public String recibirEncargo(){ return users.get(pos).getTipo_permisos();}

    public void modificar(String permisos, String rol,String encargo ){
        String uid = users.get(pos).getUid();
        DatabaseReference myRef = Activity_Menu.getInstance().c_activity_menu.getDatabase().getReference("users/" + uid + "/user");
        myRef.child("Nombre").setValue(permisos);
        if (rol.equals("SuperAdmin")) {
            myRef.child("Permisos").setValue("Super");
        } else if (rol.equals("Administrador")) {
            myRef.child("Permisos").setValue("Admin");
        } else {
            myRef.child("Permisos").setValue(rol);
            myRef.child("Encargo").setValue(encargo);
        }
    }



}
