package com.example.proyectdam.Controlador.Activitys;

import android.widget.Toast;

import com.example.proyectdam.Vista.MainActivity;

public class C_Activity_Olvidar {

    public void olvidarContrasenya(String email){
        if(email.contains("@")) {
            MainActivity.getInstance().c_activityMain.getmAuth().sendPasswordResetEmail(email);
        }
        else{
            Toast.makeText(MainActivity.getInstance(), "Campos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
