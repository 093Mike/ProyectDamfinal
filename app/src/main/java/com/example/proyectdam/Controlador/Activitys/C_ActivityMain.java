package com.example.proyectdam.Controlador.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.MainActivity;
import com.example.proyectdam.Vista.Activity.PerdidoContrasenya;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class C_ActivityMain extends Activity{
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    public boolean control;

    public void initialize(){
        user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(MainActivity.getInstance(), Activity_Menu.class);
            MainActivity.getInstance().startActivity(intent);
        }
    }


    public void iniciarLogin(String email , String pass){

        if(!control) {
            control=true;
            boolean con_email = false, con_pass = false;
            if(email.length() > 0 && email.contains("@")) {
                con_email = true;
            }
            if (pass.length()>6){
                con_pass = true;
            }
            if(con_email && con_pass){
                mAuth = FirebaseAuth.getInstance();
                controlLogin(email, pass);
            }
            else {
                control=false;
                if(!con_email && !con_pass){
                    Toast.makeText(MainActivity.getInstance(), "Campos incompletos", Toast.LENGTH_SHORT).show();
                }
                else if(!con_email){
                    Toast.makeText(MainActivity.getInstance(), "Email incorrecto", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.getInstance(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void controlLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.getInstance(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("LOGIN", "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.getInstance(), Activity_Menu.class);
                            MainActivity.getInstance().startActivity(intent);
                        } else {
                            control=false;
                            Log.w("NO LOGIN", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.getInstance(), "La autentificación a sido incorrecta.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void olvidaIntent() {
        Intent intent = new Intent(MainActivity.getInstance(), PerdidoContrasenya.class);
        MainActivity.getInstance().startActivity(intent);
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
    public FirebaseUser getUser() {
        return user;
    }
    public void setUser(FirebaseUser user) {
        this.user = user;
    }
    public boolean isControl() {
        return control;
    }
    public void setControl(boolean control) {
        this.control = control;
    }
}
