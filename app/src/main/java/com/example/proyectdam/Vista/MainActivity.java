package com.example.proyectdam.Vista;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.proyectdam.Controlador.Activitys.C_ActivityMain;
import com.example.proyectdam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity {
    EditText e_user, password;
    public C_ActivityMain c_activityMain ;
    public String token;

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
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }
                        else {
                            token = task.getResult().getToken();
                            // Log and toast

                            Log.w("TAG", token);
                        }

                    }
                });
        c_activityMain.initialize();
        e_user.setText("");
        password.setText("");

    }



    public void iniciarLogin(View view){ c_activityMain.iniciarLogin(e_user.getText().toString(),password.getText().toString()); }

    public void olvidaIntent(View view) {
        c_activityMain.olvidaIntent();
    }


    private static MainActivity myContext;
    public MainActivity() { myContext = this; }
    public static MainActivity getInstance() { return myContext; }


}
