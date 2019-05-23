package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.GestioUser.C_Activity_GestioUserModVer;
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
        setContentView(R.layout.menugestiomod_veruser);
        c_activity_gestioUserModVer = new C_Activity_GestioUserModVer();
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initialite();
        c_activity_gestioUserModVer.inicialite();

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
            nombre.setText(c_activity_gestioUserModVer.recibirNombre()+"\n"+c_activity_gestioUserModVer.recibirPermisos()+" - "+c_activity_gestioUserModVer.recibirEncargo());
            info.setText(info.getText().toString() + "\nEncargo : "+c_activity_gestioUserModVer.recibirEncargo());
        }
        else{
            nombre.setText(c_activity_gestioUserModVer.recibirNombre()+"\n"+c_activity_gestioUserModVer.recibirPermisos());
        }

    }

    public void initialite(){
        rol = findViewById(R.id.spinner);
        encargo = findViewById(R.id.spinner2);
        nombre = findViewById(R.id.textView16);
        e_permisos = findViewById(R.id.editText7);
        gua_mod = findViewById(R.id.button5);
        info = findViewById(R.id.textView3);
    }


    public void modUser(View view) {
        c_activity_gestioUserModVer.modificar(e_permisos.getText().toString(),(String) rol.getSelectedItem(),(String) encargo.getSelectedItem());
    }

}
