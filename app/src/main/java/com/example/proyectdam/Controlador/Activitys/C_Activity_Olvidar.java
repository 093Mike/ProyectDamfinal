package com.example.proyectdam.Controlador.Activitys;

import com.example.proyectdam.Vista.MainActivity;

public class C_Activity_Olvidar {

    public void olvidarContrasenya(String email){
        MainActivity.getInstance().c_activityMain.getmAuth().sendPasswordResetEmail(email);
    }
}
