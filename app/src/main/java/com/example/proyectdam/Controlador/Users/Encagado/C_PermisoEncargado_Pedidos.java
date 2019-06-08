package com.example.proyectdam.Controlador.Users.Encagado;

import com.example.proyectdam.Controlador.Users.Permiso;

public class C_PermisoEncargado_Pedidos implements Permiso {
        @Override
        public int[] permisosMenu() { return new int[]{0,1,2,3,4,5}; }

        @Override
        public int[] permisosMenuUsers() { return new int[]{0}; }

        @Override
        public int[] permisosAlmacen() { return new int[]{0,3,6}; }

        @Override
        public boolean permisosAlmacen_modificarProducto() { return false; }

        @Override
        public boolean permisosCliente() {
                return true;
        }

        @Override
        public boolean permisoProveedor() {
                return false;
        }

        @Override
        public boolean permisoAddPedidos() {
                return true;
        }

}
