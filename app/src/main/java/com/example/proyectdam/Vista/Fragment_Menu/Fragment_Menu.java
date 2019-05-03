package com.example.proyectdam.Vista.Fragment_Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectdam.R;

public class Fragment_Menu extends Fragment {

    private static Button boton1,boton2;
    public static Button[] options = new Button[] {boton1,boton2};
    private int[] id_buttons = new int[]{R.id.boton_menu_principal,R.id.boton_menu_principal2};
    static String[] nombre_botones = new String[]{"Almacen","Usuarios"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_objects, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        for (int i=0;i<id_buttons.length;i++){
            options[i] = getView().findViewById(id_buttons[i]);
        }
        actualiza();

    }

    public void actualiza(){
        for (int i=0;i<id_buttons.length;i++){
            options[i].setVisibility(View.INVISIBLE);
        }
    }

    public static void asignarBotones (int[] botones){
        int pos = 0;
        for (int i = 0 ; i < nombre_botones.length;i++){
            for (int j = 0 ;j < botones.length;j++){
                if(i == botones[j]){
                    options[pos].setVisibility(View.VISIBLE);
                    options[pos].setTag(nombre_botones[i]);
                    options[pos].setText(nombre_botones[i]);
                    pos++;
                }
            }
        }
    }




}
