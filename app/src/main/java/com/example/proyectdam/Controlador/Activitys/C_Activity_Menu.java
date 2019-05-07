package com.example.proyectdam.Controlador.Activitys;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Controlador.Users.C_Permisos;
import com.example.proyectdam.Model.User;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.MainActivity;
import com.example.proyectdam.Vista.Fragment_Menu.Fragment_Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class C_Activity_Menu extends Activity {
    DatabaseReference mref;
    FirebaseDatabase database;
    public User user;

    public void initialite(){
        database = FirebaseDatabase.getInstance();
        leerUsuario();
    }

    public void leerUsuario() {
        mref = database.getReference("users/" + MainActivity.getInstance().c_activityMain.getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    String nombre = user.child("Nombre").getValue(String.class);
                    String permisos = user.child("Permisos").getValue(String.class);
                    if (permisos.equals("Encargado") || permisos.equals("Trabajador")) {
                        String tipo_encargo = user.child("Encargo").getValue(String.class);
                        C_Activity_Menu.this.user = new User(nombre,permisos,tipo_encargo, MainActivity.getInstance().c_activityMain.getUser().getUid());
                    }
                    else{
                        C_Activity_Menu.this.user = new User(nombre,permisos,MainActivity.getInstance().c_activityMain.getUser().getUid());
                    }
                    C_Permisos c_permisos = new C_Permisos();
                    Fragment_Menu fragment_menu = new Fragment_Menu();
                    fragment_menu.actualiza();
                    Fragment_Menu.asignarBotones(c_permisos.permisosMenu());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void selecionarUser(View view){
        IntentsMenu intentsMenu = new IntentsMenu();
        Activity_Menu.getInstance().startActivity(intentsMenu.gestioIntent("MV_User"));
    }



    public DatabaseReference getMref() {
        return mref;
    }

    public void setMref(DatabaseReference mref) {
        this.mref = mref;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

