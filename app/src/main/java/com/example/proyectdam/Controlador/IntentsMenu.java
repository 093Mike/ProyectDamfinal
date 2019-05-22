package com.example.proyectdam.Controlador;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.proyectdam.Nueva_Categoria;
import com.example.proyectdam.Vista.Activity.Activity_GestioUserModVer;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.Activity_ModPedido;
import com.example.proyectdam.Vista.Activity.Activity_VerPedido;
import com.example.proyectdam.Vista.Activity.ImagenProducto;
import com.example.proyectdam.Vista.Activity.ListaCategorias;
import com.example.proyectdam.Vista.Activity.ListaProductos;
import com.example.proyectdam.Nueva_Categoria;

import com.example.proyectdam.Vista.Activity.AddProducto;
import com.example.proyectdam.Vista.Activity.ImagenProducto;
import com.example.proyectdam.Vista.Activity.ListaCategorias;
import com.example.proyectdam.Vista.Activity.ListaProductos;
import com.example.proyectdam.Vista.Fragment_Almacen.MenuAlmacen;
import com.example.proyectdam.Vista.Activity.Activity_GestioUserModVer;

import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.MenuUser_add;
import com.example.proyectdam.Vista.Fragment_About.Fragment_AboutUs;
import com.example.proyectdam.Vista.Fragment_Almacen.MenuAlmacen;
import com.example.proyectdam.Vista.Fragment_Clientes.Fragment_Clientes;
import com.example.proyectdam.Vista.Fragment_Menu.Fragment_MenuMain;
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
            case "CREAR CATEGORIA":
                intent= new Intent(Activity_Menu.getInstance().getApplicationContext(), Nueva_Categoria.class);
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
            // ALMACEN
            case "INVENTARIO ALMACEN":
                intent = new Intent(Activity_Menu.getInstance().getApplicationContext(), ListaCategorias.class);
                break;
            case "ESCOGE_CATEGORIA":
                intent = new Intent(ListaCategorias.getInstance().getApplicationContext(), ListaProductos.class);
                break;
            case "AMPLIAR_IMAGEN_PRODUCTO":
                intent = new Intent(ListaProductos.getInstance().getApplicationContext(), ImagenProducto.class);
                break;
            case "MODIFICAR_PRODUCTO":
                intent = new Intent(ListaProductos.getInstance().getApplicationContext(), AddProducto.class);
                break;
        }
        return intent;
    }
}
