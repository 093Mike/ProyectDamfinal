package com.example.proyectdam.Vista.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.proyectdam.Controlador.Activitys.C_ActivityMain;
import com.example.proyectdam.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText e_user, password;
    public C_ActivityMain c_activityMain ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        c_activityMain = new C_ActivityMain();
        c_activityMain.setmAuth(FirebaseAuth.getInstance());
        e_user = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        password.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    iniciarLogin(v);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        c_activityMain.initialize();
    }

    public void iniciarLogin(View view){ c_activityMain.iniciarLogin(e_user.getText().toString(),password.getText().toString()); }

    public void olvidaIntent(View view) {
        c_activityMain.olvidaIntent();
    }


    private static MainActivity myContext;
    public MainActivity() { myContext = this; }
    public static MainActivity getInstance() { return myContext; }


}
