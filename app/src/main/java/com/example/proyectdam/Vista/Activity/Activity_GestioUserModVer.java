package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
<<<<<<< HEAD
import android.view.WindowManager;
=======
import android.view.Window;
import android.widget.AdapterView;
>>>>>>> master
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.GestioUser.C_Activity_GestioUserModVer;
import com.example.proyectdam.Controlador.Fragments.Users.AdaptadorUsers;
import com.example.proyectdam.R;

public class Activity_GestioUserModVer extends AppCompatActivity {

    TextView nombre, info;
    EditText e_permisos;
    Button gua_mod;
    Spinner rol,encargo;

    C_Activity_GestioUserModVer c_activity_gestioUserModVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menugestiomod_veruser);
        c_activity_gestioUserModVer = new C_Activity_GestioUserModVer();
<<<<<<< HEAD
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initialite();
=======
>>>>>>> master
        c_activity_gestioUserModVer.inicialite();
        initialite();
        setTitle("");
        info.setVisibility(View.INVISIBLE);
        if(!Activity_Menu.getInstance().c_activity_menu.user.getPermisos().equals("Super")){
            gua_mod.setVisibility(View.INVISIBLE);
            e_permisos.setVisibility(View.INVISIBLE);
            rol.setVisibility(View.INVISIBLE);
            encargo.setVisibility(View.INVISIBLE);
            info.setVisibility(View.VISIBLE);
            info.setText("Nombre:"+c_activity_gestioUserModVer.recibirNombre()+"\nPermiso:"+c_activity_gestioUserModVer.recibirPermisos());
        }
        if(c_activity_gestioUserModVer.isAlTra()){
            info.setText(info.getText().toString() + "\nEncargo : "+c_activity_gestioUserModVer.recibirEncargo());
        }
    }

    public void initialite(){
        rol = findViewById(R.id.spinner);
        encargo = findViewById(R.id.spinner2);
        nombre = findViewById(R.id.textView16);
        e_permisos = findViewById(R.id.editText7);
        gua_mod = findViewById(R.id.button5);
        info = findViewById(R.id.textView3);
        encargo.setEnabled(false);
        e_permisos.setText(c_activity_gestioUserModVer.recibirNombre());
        rol.setSelection(c_activity_gestioUserModVer.posRol(c_activity_gestioUserModVer.getUsers().get(AdaptadorUsers.position).getPermisos()));
        rol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String permiso = (String) rol.getSelectedItem();
                if(permiso.equals("Encargado") || permiso.equals("Trabajador")){ encargo.setEnabled(true); }
                else{ encargo.setEnabled(false); }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void modUser(View view) {
        c_activity_gestioUserModVer.modificar(e_permisos.getText().toString(),(String) rol.getSelectedItem(),(String) encargo.getSelectedItem());

    }

}
