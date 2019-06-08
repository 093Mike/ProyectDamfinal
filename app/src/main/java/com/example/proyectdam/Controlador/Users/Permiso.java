package com.example.proyectdam.Controlador.Users;

public interface Permiso {

    int[] permisosMenu();
    int[] permisosMenuUsers();
    int[] permisosAlmacen();
    boolean permisosAlmacen_modificarProducto();

    boolean permisosCliente();

    boolean permisoProveedor();

    boolean permisoAddPedidos();

}
