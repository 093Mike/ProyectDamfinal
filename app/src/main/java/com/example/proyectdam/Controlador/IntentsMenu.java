package com.example.proyectdam.Controlador;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.proyectdam.Vista.Activity.Activity_AddIncidencia;
import com.example.proyectdam.Vista.Activity.Activity_ListaProductos;
import com.example.proyectdam.Vista.Activity.Activity_VerIncidencias;
import com.example.proyectdam.Vista.Activity.Nueva_Categoria;
import com.example.proyectdam.Vista.Activity.Activity_GestioUserModVer;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.Activity_ModPedido;
import com.example.proyectdam.Vista.Activity.Activity_VerPedido;
<<<<<<< HEAD
import com.example.proyectdam.Vista.Activity.Activity_AmpliarImagenProducto;
import com.example.proyectdam.Vista.Activity.Activity_ListaCategorias;

import com.example.proyectdam.Vista.Activity.Activity_AddProducto;
import com.example.proyectdam.Vista.Fragment_Almacen.MenuAlmacen;

import com.example.proyectdam.Vista.Activity.MenuUser_add;
import com.example.proyectdam.Vista.Fragment_About.Fragment_AboutUs;
import com.example.proyectdam.Vista.Fragment_Clientes.Fragment_Clientes;
import com.example.proyectdam.Vista.Fragment_GestioUsers.Fragment_GestioUsuarios;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;
import com.example.proyectdam.Vista.Fragment_Proveedores.Fragment_Proveedores;

public class IntentsMenu {

    public Fragment gestioIntent_Menu(String text_button){

        Fragment intent = null;
        switch (text_button){
            case "VACIO":
                intent = new Fragment_MenuMain();
                break;
            case "ALMACEN":
                intent = new MenuAlmacen();
                break;
            case "CLIENTES":
                intent = new Fragment_Clientes();
                break;
            case "PROVEEDORES":
                intent = new Fragment_Proveedores();
                break;
            case "USUARIOS":
                intent = new Fragment_GestioUsuarios();
                break;
            case "PEDIDOS":
                intent = new Fragment_MenuPedidos();
                break;
            case "ABOUT US":
                intent = new Fragment_AboutUs();
                break;
        }
        return intent;
    }

    public Intent gestioIntent(String text_button){
        Intent intent = null;
        switch (text_button){
            // USUARIOS
            case "AÑADIR USUARIO":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), MenuUser_add.class);
                break;
            case "MV_User":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_GestioUserModVer.class);
                break;
            case "MV_Pedido":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_VerPedido.class);
                break;
            case "MV_Mod_Pedido":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_ModPedido.class);
                break;

            // MenuAlmacen.java
            case "MENUALMACEN_INVENTARIO":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_ListaCategorias.class);
                break;
            case "MENUALMACEN_CREARCATEGORIA":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Nueva_Categoria.class);
                break;
            case "MENUALMACEN_CREARPRODUCTO":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_AddProducto.class);
                break;
            case "MENUALMACEN_CREARINCIDENCIA":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_AddIncidencia.class);
                break;
            case "MENUALMACEN_VERINCIDENCIAS":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_VerIncidencias.class);
                break;
            case "MENUALMACEN_MOVIMIENTOS":
//                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), Activity_VerIncidencias.class);
                break;

            // AdapterCategoria.java
            case "ADAPTERCATEGORIA_ENTRACATEGORIA":
                intent = new Intent(Activity_ListaCategorias.getInstance().getApplicationContext(), Activity_ListaProductos.class);
                break;

            // AdapterProducto.java
            case "ADAPTERPRODUCTO_AMPLIARIMAGEN":
                intent = new Intent(Activity_ListaProductos.getInstance().getApplicationContext(), Activity_AmpliarImagenProducto.class);
                break;
            case "ADAPTERPRODUCTO_MODIFICARPRODUCTO":
                intent = new Intent(Activity_ListaProductos.getInstance().getApplicationContext(), Activity_AddProducto.class);
                break;
        }
        return intent;
    }
}
