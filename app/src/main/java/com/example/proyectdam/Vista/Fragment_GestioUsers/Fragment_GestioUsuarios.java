package com.example.proyectdam.Vista.Fragment_GestioUsers;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectdam.Controlador.Fragments.Users.AdaptadorUsers;
import com.example.proyectdam.Controlador.Fragments.Users.C_Fragment_GestioUsuarios;
import com.example.proyectdam.Controlador.Users.C_Permisos;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;

public class Fragment_GestioUsuarios extends Fragment {

    public RecyclerView recyclerView;
    public  C_Fragment_GestioUsuarios c_fragment_gestioUsuarios;
    C_Permisos c_permisos;
    public AdaptadorUsers adaptadorUsers;

    private Button adduser;
    public  Button[] options = new Button[]{adduser};
    private int[] id_buttons = new int[]{R.id.b_menu_adduser};
    String[] nombre_botones = new String[]{"AÑADIR USUARIOS"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gestiousers, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        for (int i = 0 ; i < options.length ; i++){
            options[i] = getView().findViewById(id_buttons[i]);
            options[i].setVisibility(View.INVISIBLE);
        }
        c_fragment_gestioUsuarios = new C_Fragment_GestioUsuarios();
        c_fragment_gestioUsuarios.creaListaUsuarios();
        adaptadorUsers = new AdaptadorUsers(R.layout.item_users, c_fragment_gestioUsuarios.getUsers());
        recyclerView = getView().findViewById(R.id.listausers);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Activity_Menu.getInstance(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adaptadorUsers);
        c_permisos = new C_Permisos();
        asignarBotones(c_permisos.permisosMenuUsers());

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void asignarBotones (int[] botones) {
        for (int i = 0; i < nombre_botones.length; i++) {
            for (int j = 0; j < botones.length; j++) {
                options[i].setVisibility(View.VISIBLE);
                options[i].setTag(nombre_botones[i]);
                options[i].setText(nombre_botones[i]);
                if (botones[i] == 1) {
                    options[i].setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                }
                else{
                    options[i].setTextColor(getResources().getColorStateList(R.color.color_denegado));
                }
            }

        }
    }


    private static Fragment_GestioUsuarios myContext;
    public Fragment_GestioUsuarios() { myContext = this; }
    public static Fragment_GestioUsuarios getInstance() { return myContext; }

}
