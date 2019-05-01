package com.example.proyectdam.Menu.GestioUsers;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectdam.Menu.IntentsMenu;
import com.example.proyectdam.Menu.MenuPrincipal;
import com.example.proyectdam.R;
import com.example.proyectdam.Users.Admin.PermisoAdmin;
import com.example.proyectdam.Users.Encagado.PermisoEncargado;
import com.example.proyectdam.Users.SuperAdmin.PermisoSuperAdmin;
import com.example.proyectdam.Users.Trabajador.PermisosTrabajador;

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
        controlPermisos();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void controlPermisos() {
            switch (MenuPrincipal.getInstance().permisos){
                case "Super":
                    PermisoSuperAdmin permisoSuperAdmin = new PermisoSuperAdmin();
                    permisoSuperAdmin.permisosMenuUsers();
                    break;
                case "Admin":
                    PermisoAdmin permisoAdmin = new PermisoAdmin();
                    permisoAdmin.permisosMenuUsers();
                case "Encargado":
                    PermisoEncargado permisoEncargado = new PermisoEncargado(MenuPrincipal.getInstance().tipo_encargo);
                    permisoEncargado.permisosMenuUsers();
                    break;
                case "Trabajador":
                    PermisosTrabajador permisosTrabajador = new PermisosTrabajador(MenuPrincipal.getInstance().tipo_encargo);
                    permisosTrabajador.permisosMenuUsers();
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
