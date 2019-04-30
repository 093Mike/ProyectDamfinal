package com.example.proyectdam.Menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectdam.MainActivity;
import com.example.proyectdam.R;
import com.example.proyectdam.Users.Admin.PermisoAdmin;
import com.example.proyectdam.Users.Encagado.PermisoEncargado;
import com.example.proyectdam.Users.SuperAdmin.PermisoSuperAdmin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends AppCompatActivity {

    DatabaseReference mref;
    FirebaseDatabase database;
    private Button almacen;
    private TextView t_main;
    private Button gestion_usuarios;
    public Button[] options = new Button[] {almacen,gestion_usuarios};
    private int[] id_buttons = new int[]{R.id.boton_menu_principal,R.id.boton_menu_principal2};
    private String permisos;
    private String tipo_encargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        for (int i=0;i<id_buttons.length;i++){
            options[i] = findViewById(id_buttons[i]);
            options[i].setVisibility(View.INVISIBLE);
        }
        t_main = findViewById(R.id.t_menu_bienvenido);
        database = FirebaseDatabase.getInstance();
        leerUsuario();
    }

    private void leerUsuario() {
        mref = database.getReference("users/"+ MainActivity.getInstance().getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()){
                        t_main.setText("Bienvenido "+user.child("Nombre").getValue(String.class));
                        permisos = user.child("Permisos").getValue(String.class);
                        if(permisos.equals("Encargado")){
                            tipo_encargo = user.child("Encargo").getValue(String.class);
                        }
                        controlPermisos();

                }
                mref.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void controlPermisos() {
        switch (permisos){
            case "Super":
                PermisoSuperAdmin permisoSuperAdmin = new PermisoSuperAdmin();
                permisoSuperAdmin.permisosMenu();
                break;
            case "Admin":
                PermisoAdmin permisoAdmin = new PermisoAdmin();
                permisoAdmin.permisosMenu();
            case "Encargado":
                PermisoEncargado permisoEncargado = new PermisoEncargado(tipo_encargo);
                permisoEncargado.permisosMenu();
                break;
        }
    }


    private static MenuPrincipal myContext;
    public MenuPrincipal() { myContext = this; }
    public static MenuPrincipal getInstance() { return myContext; }
}
