package com.example.proyectdam.Vista.Fragment_Almacen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;

public class MenuAlmacen extends Fragment {

    private Button button_verInventario,
            button_movimientos,
            button_incidencias,
            button_cambiarAlmacen;

    public MenuAlmacen() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_almacen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button_verInventario = view.findViewById(R.id.button_inventario);
        button_movimientos = view.findViewById(R.id.button_movimientos);
        button_incidencias = view.findViewById(R.id.button_incidencias);
        button_cambiarAlmacen = view.findViewById(R.id.button_cambiarAlmacen);

        button_verInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent(v.getTag().toString().toUpperCase()));
            }
        });

        button_movimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_incidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_cambiarAlmacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}