package com.example.proyectdam.Vista.Fragment_Almacen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.Almacen.C_Almacen;
import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Controlador.Users.C_Permisos;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.Activity_VerMovimientos;

public class MenuAlmacen extends Fragment {

    private Button button_verInventario,
        button_movimientos,
        button_incidencias,
        button_cambiarAlmacen,
        button_anadirProducto,
        button_verIncidencias,
        button_actualizarImagenes,
        button_crearCategoria;

    private Button[] buttons = new Button[]{button_verInventario,
            button_movimientos,
            button_incidencias,
            button_cambiarAlmacen,
            button_anadirProducto,
            button_verIncidencias,
            button_actualizarImagenes,
            button_crearCategoria };

    private int[] idButtons = {R.id.button_menuAlmacen_inventario,
            R.id.button_menuAlmacen_movimientos,
            R.id.button_menuAlmacen_crearIncidencia,
            R.id.button_menuAlmacen_cambiarAlmacen,
            R.id.button_menuAlmacen_crearProducto,
            R.id.button_menuAlmacen_verIncidencias,
            R.id.button_menuAlmacen_actualizarImagenes,
            R.id.button_menuAlmacen_crearCategoria};

    private TextView textView_almacenActual;
    public C_Almacen c_almacen;
    private C_Permisos c_permisos;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_almacen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        c_permisos = new C_Permisos();
        c_almacen = new C_Almacen();
        textView_almacenActual = view.findViewById(R.id.textView_menuAlmacen_almacenActual);
        textView_almacenActual.setText(c_almacen.getAlmacenActual().getId() + " - " + c_almacen.getAlmacenActual().getDireccion());

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = view.findViewById(idButtons[i]);
            buttons[i].setEnabled(false);
        }

        c_almacen.menuAlmacen_asignarBotones(buttons, c_permisos.permisosAlmacen());

        buttons[0].setOnClickListener(new View.OnClickListener() { // VER INVENTARIO
            @Override
            public void onClick(View v) {
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent("MENUALMACEN_INVENTARIO"));
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() { // MOVIMIENTOS ALMACÉN
            @Override
            public void onClick(View v) {
                // TODO: falta por implementar
                Activity_VerMovimientos activity_verMovimientos = new Activity_VerMovimientos(c_almacen.getAlmacenActual());
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent("MENUALMACEN_MOVIMIENTOS"));
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() { // CREAR INCIDENCIA
            @Override
            public void onClick(View v) {
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent("MENUALMACEN_CREARINCIDENCIA"));
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // CAMBIAR ALMACÉN
                c_almacen.alertDialog_escogeAlmacen(v);
            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // AÑADIR PRODUCTO
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent("MENUALMACEN_CREARPRODUCTO"));
            }
        });

        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // VER INCIDENCIAS
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent("MENUALMACEN_VERINCIDENCIAS"));
            }
        });

        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // ACTUALIZAR IMAGENES
                c_almacen.actualizarImagenes(Activity_Menu.getInstance());
            }
        });

        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // CREAR CATEGORIA
                IntentsMenu intentsMenu = new IntentsMenu();
                startActivity(intentsMenu.gestioIntent("MENUALMACEN_CREARCATEGORIA"));
            }
        });
    }
    private static MenuAlmacen myContext;
    public MenuAlmacen() { myContext = this; }
    public static MenuAlmacen getInstance() { return myContext; }
}