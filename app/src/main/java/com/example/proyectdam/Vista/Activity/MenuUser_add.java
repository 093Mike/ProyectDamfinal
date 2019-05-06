package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.proyectdam.Controlador.Activitys.GestioUser.C_Activity_GestioUserAdd;
import com.example.proyectdam.R;

public class MenuUser_add extends AppCompatActivity {
    Spinner rol,encargo;
    EditText nombre,correo,contrasenya;
    C_Activity_GestioUserAdd c_activity_gestioUserAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_gestioadduser);
        getSupportActionBar().hide();
        nombre = findViewById(R.id.editText3);
        correo = findViewById(R.id.editText4);
        contrasenya = findViewById(R.id.editText5);
        rol = findViewById(R.id.rol);
        encargo = findViewById(R.id.puesto);
        c_activity_gestioUserAdd = new C_Activity_GestioUserAdd();
        c_activity_gestioUserAdd.leerUsuario();
    }

    public void crearUser(View view) {
      c_activity_gestioUserAdd.crearUsuario(nombre.getText().toString(),correo.getText().toString(),contrasenya.getText().toString(),(String)rol.getSelectedItem(),(String)encargo.getSelectedItem());
    }

    private static MenuUser_add myContext;
    public MenuUser_add() { myContext = this; }
    public static MenuUser_add getInstance() { return myContext; }

}
