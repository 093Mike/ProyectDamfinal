package com.example.proyectdam.Controlador.Users.Encagado;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoEncargado implements Permiso {

    private String tipo_Encargo;
    public C_PermisoEncargado(String tipo_Encargo){
        this.tipo_Encargo = tipo_Encargo;
    }

    @Override
    public int[] permisosMenu() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosMenu();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisosMenu();

        }
    return null;
    }

    @Override
    public int[] permisosMenuUsers() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosMenuUsers();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisosMenuUsers();

        }
        return null;
    }

    @Override
    public int[] permisosAlmacen() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosAlmacen();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisosAlmacen();

        }
        return null;
    }

    @Override
    public boolean permisosAlmacen_modificarProducto() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosAlmacen_modificarProducto();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisosAlmacen_modificarProducto();
        }
        return false;
    }

    @Override
    public boolean permisosCliente() {
        switch (tipo_Encargo) {
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisosCliente();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisosCliente();
        }
        return false;

    }

    @Override
    public boolean permisoProveedor() {
        switch (tipo_Encargo) {
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisoProveedor();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisoProveedor();
        }
        return false;
    }

    @Override
    public boolean permisoAddPedidos() {
        switch (tipo_Encargo) {
            case "Almacen":
                C_PermisoEncargado_Almacen pe_Almacen = new C_PermisoEncargado_Almacen();
                return pe_Almacen.permisoAddPedidos();
            case "Pedidos":
                C_PermisoEncargado_Pedidos pe_Pedidos = new C_PermisoEncargado_Pedidos();
                return pe_Pedidos.permisoAddPedidos();
        }
        return false;
    }
}
