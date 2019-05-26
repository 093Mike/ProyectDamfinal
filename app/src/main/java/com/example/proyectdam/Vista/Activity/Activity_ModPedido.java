package com.example.proyectdam.Vista.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.Pedidos.Adapter_Pedidos_AddMod;
import com.example.proyectdam.Controlador.Activitys.Pedidos.C_Activity_Pedidos_Mod;
import com.example.proyectdam.Model.Prodcuto_en_Pedido;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;

public class Activity_ModPedido extends AppCompatActivity {

    public TextView fecha,cliente,total,titulo;
    public Spinner estado;
    RecyclerView recyclerView;
    public Adapter_Pedidos_AddMod adaptadorPedidos;
    public C_Activity_Pedidos_Mod c_activity_pedidos_mod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modpedido);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fecha = findViewById(R.id.textView5);
        cliente = findViewById(R.id.textView9);
        estado = findViewById(R.id.spinner5);
        total = findViewById(R.id.textView7);
        titulo = findViewById(R.id.textView16);
        recyclerView =  findViewById(R.id.reci_pedidos);
        c_activity_pedidos_mod =  new C_Activity_Pedidos_Mod();
        c_activity_pedidos_mod.initialize();
        titulo.setText(c_activity_pedidos_mod.getTitulo());
        cliente.setText(c_activity_pedidos_mod.getCliente());
        fecha.setText(c_activity_pedidos_mod.getFecha());
        estado.setSelection(c_activity_pedidos_mod.getEstado());
        adaptadorPedidos = new Adapter_Pedidos_AddMod(R.layout.item_pedidos_mod,c_activity_pedidos_mod.getProductos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adaptadorPedidos);

    }

    public void onCreateDialog() {
        final AlertDialog.Builder d = new AlertDialog.Builder(Activity_ModPedido.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog_numberpicker, null);
        d.setView(dialogView);
        d.setTitle("NumberPicker");
        final NumberPicker np = dialogView.findViewById(R.id.numberPicker1);
        np.setMinValue(1);
        long max = Math.round(c_activity_pedidos_mod.getCatidadtotales().get(Adapter_Pedidos_AddMod.position));
        np.setMaxValue((int) max);
        np.setValue(c_activity_pedidos_mod.getPedidoActual().getProductos().get(Adapter_Pedidos_AddMod.position).getCantidad());
        np.setWrapSelectorWheel(false);
        d.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                c_activity_pedidos_mod.getPedidoActual().getProductos().set(Adapter_Pedidos_AddMod.position,new Prodcuto_en_Pedido(
                        np.getValue(),
                        c_activity_pedidos_mod.getPedidoActual().getProductos().get(Adapter_Pedidos_AddMod.position).getId_producto()
                ));
                adaptadorPedidos.notifyDataSetChanged();
                total.setText(c_activity_pedidos_mod.actualizarPrecios()+"€");
            }
        });
        d.show();
    }

    public void onCreateDialogRemove() {
        final AlertDialog.Builder d = new AlertDialog.Builder(Activity_ModPedido.this);
        d.setTitle("¿Quiere borrar este producto?");
        d.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                c_activity_pedidos_mod.id_productos.remove(Adapter_Pedidos_AddMod.position);
                c_activity_pedidos_mod.getProductos().remove(Adapter_Pedidos_AddMod.position);
                c_activity_pedidos_mod.getCatidadtotales().remove(Adapter_Pedidos_AddMod.position);
                c_activity_pedidos_mod.getPrecios().remove(Adapter_Pedidos_AddMod.position);
                c_activity_pedidos_mod.getPedidoActual().getProductos().remove(Adapter_Pedidos_AddMod.position);
                adaptadorPedidos.notifyDataSetChanged();
                Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.control = false;
                c_activity_pedidos_mod.guardarDatos();
                total.setText(c_activity_pedidos_mod.actualizarPrecios()+"€");

            }
        });
        d.setNegativeButton("No", null);
        d.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        guardar(null);
    }

    public void editarCantidades(){
        onCreateDialog();
    }

    public void anadirProducto(View view) {
        Intent intent = new Intent (getApplicationContext(),Activity_all_Products.class);
        startActivity(intent);
    }
    public void guardar(View view) {
        Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.control = true;
        c_activity_pedidos_mod.guardarDatos();
        Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.actualizarProductos();
        finish();
    }

    public void gestionarPedido(View view) {
        final AlertDialog.Builder d = new AlertDialog.Builder(Activity_ModPedido.this);
        d.setTitle("¿Que quieres hacer?");
        d.setPositiveButton("Selecionar Cliente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent (getApplicationContext(), Activity_all_Clientes.class);
                startActivity(intent);
            }
        });
        d.show();
    }

    private static Activity_ModPedido myContext;
    public Activity_ModPedido() { myContext = this; }
    public static Activity_ModPedido getInstance() { return myContext; }



}
