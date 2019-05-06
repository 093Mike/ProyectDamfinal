package com.example.proyectdam.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import com.example.proyectdam.R;
import com.example.proyectdam.views.ListaCategorias;

public class MenuAlmacen extends Fragment {

    private Button button_verInventario;
    private Button button_movimientos;
    private Button button_incidencias;
    private Button button_cambiarAlmacen;

    public MenuAlmacen() { }

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
                startActivity(new Intent(view.getContext(), ListaCategorias.class));
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