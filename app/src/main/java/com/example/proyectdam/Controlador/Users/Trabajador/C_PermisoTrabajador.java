package com.example.proyectdam.Controlador.Users.Trabajador;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoTrabajador implements Permiso {

    private String tipo_Encargo;
    public C_PermisoTrabajador(String tipo_Encargo){
        this.tipo_Encargo = tipo_Encargo;
    }

    @Override
    public int[] permisosMenu() {
        switch (tipo_Encargo){
                case "Almacen":
                    C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                    return pe_Almacen.permisosMenu();
                case "Pedidos":
                    C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                    return pe_pedidos.permisosMenu();

            }
            return null;
    }

    @Override
    public int[] permisosMenuUsers() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                return pe_Almacen.permisosMenuUsers();
            case "Pedidos":
                C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                return pe_pedidos.permisosMenuUsers();

        }
        return null;
    }

    @Override
    public int[] permisosAlmacen() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                return pe_Almacen.permisosAlmacen();
            case "Pedidos":
                C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                return pe_pedidos.permisosAlmacen();

        }
        return null;
    }

    @Override
    public boolean permisosAlmacen_modificarProducto() {
        switch (tipo_Encargo){
            case "Almacen":
                C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                return pe_Almacen.permisosAlmacen_modificarProducto();
            case "Pedidos":
                C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                return pe_pedidos.permisosAlmacen_modificarProducto();

        }
        return false;
    }

    @Override
    public boolean permisosCliente() {
        switch (tipo_Encargo) {
            case "Almacen":
                C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                return pe_Almacen.permisosCliente();
            case "Pedidos":
                C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                return pe_pedidos.permisosCliente();

        }
        return false;

    }

    @Override
    public boolean permisoProveedor() {
        switch (tipo_Encargo) {
            case "Almacen":
                C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                return pe_Almacen.permisoProveedor();
            case "Pedidos":
                C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                return pe_pedidos.permisoProveedor();

        }
        return false;
    }

    @Override
    public boolean permisoAddPedidos() {
        switch (tipo_Encargo) {
            case "Almacen":
                C_PermisoTrabajador_Almacen pe_Almacen = new C_PermisoTrabajador_Almacen();
                return pe_Almacen.permisoAddPedidos();
            case "Pedidos":
                C_PermisoTrabajador_Pedidos pe_pedidos = new C_PermisoTrabajador_Pedidos();
                return pe_pedidos.permisoAddPedidos();

        }
        return false;
    }

}
