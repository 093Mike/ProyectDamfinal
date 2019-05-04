package com.example.proyectdam.Controlador.Activitys.GestioUser;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.proyectdam.Model.User;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Fragment_Menu.GestioUsers.Fragment_GestioUsuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class C_Activity_MenuUsers extends Activity {

    DatabaseReference mref;
    FirebaseDatabase database;
    ArrayList<User> users = new ArrayList<>();

    public void creaListaUsuarios(){

        database =  Activity_Menu.getInstance().c_activity_menu.getDatabase();
        mref = database.getReference("users");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                users.add(Activity_Menu.getInstance().c_activity_menu.user);
                for (DataSnapshot uiduser : dataSnapshot.getChildren()) {
                    String uid = uiduser.getKey();
                    if (!uid.equals(Activity_Menu.getInstance().c_activity_menu.user.getUid())) {
                        String nombre, rol, encargo;
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
                Fragment_GestioUsuarios.getInstance().recyclerView.setAdapter(Fragment_GestioUsuarios.getInstance().adaptadorUsers



                );
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
