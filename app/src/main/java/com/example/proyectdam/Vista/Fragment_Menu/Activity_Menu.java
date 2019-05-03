package com.example.proyectdam.Vista.Fragment_Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Controlador.Users.C_Permisos;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Menu.GestioUsers.MenuUser_gestor;
import com.example.proyectdam.Vista.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_Menu extends AppCompatActivity {
    DatabaseReference mref;
    FirebaseDatabase database;
    public String permisos;
    public String tipo_encargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        database = FirebaseDatabase.getInstance();
        leerUsuario();
    }

    public void leerUsuario() {
        mref = database.getReference("users/" + MainActivity.getInstance().getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()) {
//                    t_main.setText("Bienvenido "+user.child("Nombre").getValue(String.class));
                    permisos = user.child("Permisos").getValue(String.class);
                    if (permisos.equals("Encargado") || permisos.equals("Trabajador")) {
                        tipo_encargo = user.child("Encargo").getValue(String.class);
                    }

                }
                C_Permisos c_permisos = new C_Permisos();
                Fragment_Menu fragment_menu = new Fragment_Menu();
                fragment_menu.actualiza();
                Fragment_Menu.asignarBotones(c_permisos.controlPermisos_menu());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void gestioMenu(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        IntentsMenu intentsMenu = new IntentsMenu();

        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu(((String) view.getTag()).toUpperCase()) , "fragment_meters" );
        ft.commit();
    }

    public void gestioaddUsers(View view){
//        String tag = (String) view.getTag();
        IntentsMenu intentsMenu = new IntentsMenu();
        startActivity(new Intent(this, MenuUser_gestor.class));
    }

    private static Activity_Menu myContext;
    public Activity_Menu() { myContext = this; }
    public static Activity_Menu getInstance() { return myContext; }


}
