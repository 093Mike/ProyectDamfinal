package com.example.proyectdam.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Menu.Activity_Menu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    EditText e_user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        e_user = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }


    public void iniciarLogin(View view){
        if(e_user.getText().toString().contains("@") && password.getText().toString().length() >=6) {
            controlLogin(e_user.getText().toString(), password.getText().toString());
        }
        else{
            Toast.makeText(myContext, "Campos incorrectos", Toast.LENGTH_SHORT).show();
        }

    }

    private void controlLogin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOGIN", "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), Activity_Menu.class);
                            startActivity(intent);
//                          updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("NO LOGIN", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "La autentificación a sido incorrecta.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void olvidaIntent(View view) {
        Intent intent = new Intent(this, PerdidoContrasenya.class);
        startActivity(intent);
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


    private static MainActivity myContext;
    public MainActivity() { myContext = this; }
    public static MainActivity getInstance() { return myContext; }


}
