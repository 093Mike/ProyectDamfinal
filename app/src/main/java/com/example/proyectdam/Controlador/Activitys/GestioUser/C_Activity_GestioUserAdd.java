package com.example.proyectdam.Controlador.Activitys.GestioUser;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.MenuUser_add;
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

public class C_Activity_GestioUserAdd extends Activity {
    FirebaseDatabase database;
    DatabaseReference mref;

    public void leerUsuario() {
        database =  Activity_Menu.getInstance().c_activity_menu.getDatabase();
        mref = database.getReference("users/"+ MainActivity.getInstance().c_activityMain.getUser().getUid());
        mref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()){
                    controlPermisos();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void controlPermisos() {
        switch (Activity_Menu.getInstance().c_activity_menu.getUser().getPermisos()) {
            case "Admin":
                Toast.makeText(MenuUser_add.getInstance(), "No tienes permisos para añadir usuarios", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "Encargado":
                Toast.makeText(MenuUser_add.getInstance(), "No tienes permisos para añadir usuarios", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case "Trabajador":
                Toast.makeText(MenuUser_add.getInstance(), "No tienes permisos para añadir usuarios", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
    public void crearUsuario(final String nombre, String email, String pass, final String rol, final String encargo) {
        boolean con_email = false, con_pass = false;
        if(email.length() > 0 && email.contains("@")) {
            con_email = true;
        }
        if (pass.length()>=6){
            con_pass = true;
        }
        if(con_email && con_pass){
            MainActivity.getInstance().c_activityMain.getmAuth().createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(MenuUser_add.getInstance(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = MainActivity.getInstance().c_activityMain.getmAuth().getCurrentUser();
                                DatabaseReference myRef = database.getReference("users/" + user.getUid() + "/user");
                                myRef.child("Nombre").setValue(nombre);
                                if (rol.equals("SuperAdmin")) {
                                    myRef.child("Permisos").setValue("Super");
                                } else if (rol.equals("Administrador")) {
                                    myRef.child("Permisos").setValue("Admin");
                                } else {
                                    myRef.child("Permisos").setValue(rol);
                                    myRef.child("Encargo").setValue(encargo);
                                }
                                Toast.makeText(MenuUser_add.getInstance(), "¡Cuenta creada!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MenuUser_add.getInstance(), "La cuenta ya esta creada.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
        else {
            if(!con_email && !con_pass){
                Toast.makeText(Activity_Menu.getInstance(), "Campos incompletos", Toast.LENGTH_SHORT).show();
            }
            else if(!con_email){
                Toast.makeText(Activity_Menu.getInstance(), "Email incorrecto", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Activity_Menu.getInstance(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }

        }


}
