package com.example.proyectdam.Vista.Fragment_Menu;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectdam.R;
import com.example.proyectdam.Vista.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuUser_gestor extends AppCompatActivity {
    DatabaseReference mref;
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
        leerUsuario();
    }

    private void leerUsuario() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mref = database.getReference("users/"+ MainActivity.getInstance().getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()){
                    Activity_Menu.getInstance().permisos = user.child("Permisos").getValue(String.class);
                    if(Activity_Menu.getInstance().permisos.equals("Encargado") || Activity_Menu.getInstance().permisos.equals("Trabajador")){
                        Activity_Menu.getInstance().tipo_encargo = user.child("Encargo").getValue(String.class);
                    }
                    controlPermisos();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void controlPermisos() {
        switch (Activity_Menu.getInstance().permisos){
            case "Admin":
                Toast.makeText(this, "No tienes permisos para añadir usuarios", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "Encargado":
                Toast.makeText(this, "No tienes permisos para añadir usuarios", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "Trabajador":
                Toast.makeText(this, "No tienes permisos para añadir usuarios", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
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
                            Toast.makeText(MenuUser_gestor.this, "¡Cuenta creada!",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MenuUser_gestor.this, "La cuenta ya esta creada.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
