package com.example.proyectdam.Vista.Fragment_Menu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;

public class Fragment_Menu extends Fragment {

    private static Button boton1,boton2,boton3,boton4,boton5,boton6;
    public static Button[] options = new Button[] {boton1,boton2,boton3,boton4,boton5,boton6};
    private int[] id_buttons = new int[]{R.id.boton_menu_principal,R.id.boton_menu_principal2,R.id.boton_menu_principal3,R.id.boton_menu_principal4,
            R.id.boton_menu_principal5,R.id.boton_menu_principal6};
    static String[] nombre_botones = new String[]{"Almacen","Pedidos","Clientes","Proveedores","Usuarios","ABOUT US"};
    ImageView powerOff;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_objects, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        powerOff = view.findViewById(R.id.b_poweroff);
        for (int i=0;i<id_buttons.length;i++){
            options[i] = getView().findViewById(id_buttons[i]);
        }
        actualiza();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void actualiza(){
        for (int i=0;i<id_buttons.length;i++){
            options[i].setVisibility(View.INVISIBLE);
            options[i].setTextColor(Color.parseColor("#D81B1B"));
            options[i].setEnabled(false);
        }
        FragmentTransaction ft = Activity_Menu.getInstance().getSupportFragmentManager().beginTransaction();
        IntentsMenu intentsMenu = new IntentsMenu();
        ft.replace(R.id.fragment_global, intentsMenu.gestioIntent_Menu("VACIO"), "fragment_meters");
        ft.commit();
    }

    public static void asignarBotones(int[] botones){
        int pos = 0;
        for (int i = 0 ; i < nombre_botones.length;i++){
            options[pos].setVisibility(View.VISIBLE);
            for (int j = 0 ;j < botones.length;j++){
                if(i == botones[j]){
                    options[pos].setTextColor(Color.parseColor("#000000"));
                    options[pos].setEnabled(true);
                }
            }
            options[pos].setTag(nombre_botones[i]);
            options[pos].setText(nombre_botones[i]);
            pos++;
        }
    }




}
