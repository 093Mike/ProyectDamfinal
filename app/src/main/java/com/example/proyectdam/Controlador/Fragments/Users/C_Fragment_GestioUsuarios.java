package com.example.proyectdam.Controlador.Fragments.Users;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.example.proyectdam.Controlador.Users.C_Permisos;
import com.example.proyectdam.Model.User;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_GestioUsers.Fragment_GestioUsuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Fragment_GestioUsuarios extends Activity {

    DatabaseReference mref;
    FirebaseDatabase database;
    ArrayList<User> users = new ArrayList<>();
    C_Permisos c_permisos;

    public void creaListaUsuarios() {
        database = Activity_Menu.getInstance().c_activity_menu.getDatabase();
        mref = database.getReference("users");
        mref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                c_permisos = new C_Permisos();
                Fragment_GestioUsuarios.getInstance().asignarBotones(c_permisos.permisosMenuUsers());
                users.clear();
                User userActual = Activity_Menu.getInstance().c_activity_menu.user;
                users.add(userActual);
                for (DataSnapshot uiduser : dataSnapshot.getChildren()) {
                    String uid = uiduser.getKey();
                    assert uid != null;
                    if (!uid.equals(userActual.getUid())) {
                        String nombre, rol, encargo;
                        if (!userActual.getPermisos().equals("Trabajador")) {
                            if(userActual.getPermisos().equals("Encargado")){
                                for (DataSnapshot user : uiduser.getChildren()) {
                                    nombre = user.child("Nombre").getValue(String.class);
                                    rol = user.child("Permisos").getValue(String.class);
                                    encargo = user.child("Encargo").getValue(String.class);
                                    if (encargo != null) {
                                        if(encargo.equals(userActual.getTipo_permisos())){
                                            users.add(new User(nombre, rol, encargo, uid));
                                        }
                                    }
                                }
                            }
                            else {
                                for (DataSnapshot user : uiduser.getChildren()) {
                                    nombre = user.child("Nombre").getValue(String.class);
                                    rol = user.child("Permisos").getValue(String.class);
                                    encargo = user.child("Encargo").getValue(String.class);
                                    if (encargo != null) {
                                        users.add(new User(nombre, rol, encargo, uid));
                                    } else {
                                        users.add(new User(nombre, rol, uid));
                                    }
                                }
                            }

                        }
                    }
                }
                Fragment_GestioUsuarios.getInstance().recyclerView.setAdapter(Fragment_GestioUsuarios.getInstance().adaptadorUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
