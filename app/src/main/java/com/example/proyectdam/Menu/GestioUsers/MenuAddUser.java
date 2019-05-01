package com.example.proyectdam.Menu.GestioUsers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectdam.MainActivity;
import com.example.proyectdam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuAddUser extends AppCompatActivity {

    Spinner rol,encargo;
    EditText nombre,correo,contrasenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_gestioadduser);
        nombre = findViewById(R.id.editText3);
        correo = findViewById(R.id.editText4);
        contrasenya = findViewById(R.id.editText5);
        rol = findViewById(R.id.rol);
        encargo = findViewById(R.id.puesto);
    }

    public void crearUser(View view) {
       MainActivity.getInstance().getmAuth().createUserWithEmailAndPassword(correo.getText().toString(), contrasenya.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = MainActivity.getInstance().getmAuth().getCurrentUser();
                            DatabaseReference myRef = database.getReference( "users/" +  user.getUid()+"/user");
                            myRef.child("Nombre").setValue(nombre.getText().toString());
                            if(rol.getSelectedItem().equals("SuperAdmin")){ myRef.child("Permisos").setValue("Super"); }
                            else if(rol.getSelectedItem().equals("Administrador")){ myRef.child("Permisos").setValue("Admin"); }
                            else{
                                myRef.child("Permisos").setValue(rol.getSelectedItem());
                                String s_encargo = (String) encargo.getSelectedItem();

                                myRef.child("Encargo").setValue(encargo.getSelectedItem());
                            }
                            Toast.makeText(MenuAddUser.this, "¡Cuenta creada!",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MenuAddUser.this, "La cuenta ya esta creada.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
