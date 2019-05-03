package com.example.proyectdam.Menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Controlador.Users.Trabajador.C_PermisosTrabajador;
import com.example.proyectdam.Vista.MainActivity;
import com.example.proyectdam.R;
import com.example.proyectdam.Controlador.Users.Admin.C_PermisoAdmin;
import com.example.proyectdam.Controlador.Users.Encagado.C_PermisoEncargado;
import com.example.proyectdam.Controlador.Users.SuperAdmin.C_PermisoSuperAdmin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends AppCompatActivity {

    DatabaseReference mref;
    FirebaseDatabase database;
    private TextView t_main;
    private Button almacen,gestion_usuarios;
    public Button[] options = new Button[] {almacen,gestion_usuarios};
    private int[] id_buttons = new int[]{R.id.boton_1,R.id.boton_menu_principal2};

    public String permisos;
    public String tipo_encargo;


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

    public void leerUsuario() {
        mref = database.getReference("users/"+ MainActivity.getInstance().getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()){
                        t_main.setText("Bienvenido "+user.child("Nombre").getValue(String.class));
                        permisos = user.child("Permisos").getValue(String.class);
                        if(permisos.equals("Encargado") || permisos.equals("Trabajador")){
                            tipo_encargo = user.child("Encargo").getValue(String.class);
                        }
                        controlPermisos();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void controlPermisos() {
        switch (permisos){
            case "Super":
                C_PermisoSuperAdmin permisoSuperAdmin = new C_PermisoSuperAdmin();
                permisoSuperAdmin.permisosMenu();
                break;
            case "Admin":
                C_PermisoAdmin RPermisoAdmin = new C_PermisoAdmin();
                RPermisoAdmin.permisosMenu();
                break;

            case "Encargado":
                C_PermisoEncargado permisoEncargado = new C_PermisoEncargado(tipo_encargo);
                permisoEncargado.permisosMenu();
                break;

            case "Trabajador":
                C_PermisosTrabajador CPermisosTrabajador = new C_PermisosTrabajador(tipo_encargo);
                CPermisosTrabajador.permisosMenu();
                break;

        }
    }

    public void botonesMenu(View view) {
        int id = view.getId();
        String texto_boton="";
        for (int i = 0 ; i < id_buttons.length;i++){
            if(id_buttons[i] == id){
               texto_boton = options[i].getText().toString();
            }
        }
        IntentsMenu intentsMenu = new IntentsMenu();
        //startActivity(intentsMenu.gestioIntent_Menu(texto_boton));
    }



    private static MenuPrincipal myContext;
    public MenuPrincipal() { myContext = this; }
    public static MenuPrincipal getInstance() { return myContext; }


}
