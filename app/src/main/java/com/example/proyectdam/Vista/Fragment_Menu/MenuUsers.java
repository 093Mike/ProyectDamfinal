package com.example.proyectdam.Vista.Fragment_Menu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectdam.Vista.MainActivity;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.R;
import com.example.proyectdam.Controlador.Users.Admin.C_PermisoAdmin;
import com.example.proyectdam.Controlador.Users.Encagado.C_PermisoEncargado;
import com.example.proyectdam.Controlador.Users.SuperAdmin.C_PermisoSuperAdmin;
import com.example.proyectdam.Controlador.Users.Trabajador.C_PermisoTrabajador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuUsers  extends AppCompatActivity  {

    private Button adduser;
    public Button[] options = new Button[] {adduser};
    private int[] id_buttons = new int[]{R.id.b_menu_adduser};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_gestiousers);
        for (int i=0;i<id_buttons.length;i++){
            options[i] = findViewById(id_buttons[i]);
            options[i].setVisibility(View.INVISIBLE);
        }
        leerUsuario();
    }


    public void leerUsuario() {
        DatabaseReference mref;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mref = database.getReference("users/"+ MainActivity.getInstance().getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()){
                     MenuPrincipal.getInstance().permisos = user.child("Permisos").getValue(String.class);
                    if(MenuPrincipal.getInstance().permisos.equals("Encargado") || MenuPrincipal.getInstance().permisos.equals("Trabajador")){
                        MenuPrincipal.getInstance().tipo_encargo = user.child("Encargo").getValue(String.class);
                    }
                    controlPermisos();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void controlPermisos() {
            switch (MenuPrincipal.getInstance().permisos){
                case "Super":
                    C_PermisoSuperAdmin permisoSuperAdmin = new C_PermisoSuperAdmin();
                    permisoSuperAdmin.permisosMenuUsers();
                    break;
                case "Admin":
                    C_PermisoAdmin RPermisoAdmin = new C_PermisoAdmin();
                    RPermisoAdmin.permisosMenuUsers();
                    break;
                case "Encargado":
                    C_PermisoEncargado permisoEncargado = new C_PermisoEncargado(MenuPrincipal.getInstance().tipo_encargo);
                    permisoEncargado.permisosMenuUsers();
                    break;
                case "Trabajador":
                    C_PermisoTrabajador CPermisosTrabajador = new C_PermisoTrabajador(MenuPrincipal.getInstance().tipo_encargo);
                    CPermisosTrabajador.permisosMenuUsers();
                    break;
            }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonesMenu(View view) {
        int id = view.getId();
        String texto_boton="";
        for (int i = 0 ; i < id_buttons.length;i++){
            if(id_buttons[i] == id){
                texto_boton = options[i].getText().toString();
            }
        }
        if(MenuPrincipal.getInstance().permisos.equals("Encargado") ||
           MenuPrincipal.getInstance().permisos.equals("Trabajador") ||
           MenuPrincipal.getInstance().permisos.equals("Admin")){
            Toast.makeText(myContext, "No tienes permisos suficientes.", Toast.LENGTH_SHORT).show();
        }
        else{
            IntentsMenu intentsMenu = new IntentsMenu();
            startActivity(intentsMenu.gestioIntent_MenuUsers(texto_boton));
        }

    }

    private static MenuUsers myContext;
    public MenuUsers() { myContext = this; }
    public static MenuUsers getInstance() { return myContext; }
}
