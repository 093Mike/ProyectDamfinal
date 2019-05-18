package com.example.proyectdam.Vista.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.Pedidos.Adapter_Pedidos_Ver;
import com.example.proyectdam.Controlador.Activitys.Pedidos.C_Activity_Pedidos_Ver;
import com.example.proyectdam.R;

public class Activity_VerPedido extends AppCompatActivity {

    TextView fecha,cliente,estado,total,titulo;
    public C_Activity_Pedidos_Ver c_activity_pedidos_ver;
    RecyclerView recyclerView;
    public Adapter_Pedidos_Ver adaptadorPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpedido);
        getSupportActionBar().hide();
        fecha = findViewById(R.id.textView5);
        cliente = findViewById(R.id.textView9);
        estado = findViewById(R.id.textView8);
        total = findViewById(R.id.textView7);
        titulo = findViewById(R.id.textView16);
        recyclerView =  findViewById(R.id.reci_pedidos);

        c_activity_pedidos_ver =  new C_Activity_Pedidos_Ver();
        c_activity_pedidos_ver.initialize();
        titulo.setText(c_activity_pedidos_ver.getTitulo());
        fecha.setText(c_activity_pedidos_ver.getFecha());
        total.setText(c_activity_pedidos_ver.getTotal()+"€");
        estado.setText(c_activity_pedidos_ver.getEstado());
        cliente.setText(c_activity_pedidos_ver.getCliente());
        adaptadorPedidos = new Adapter_Pedidos_Ver(R.layout.item_pedidos_ver,c_activity_pedidos_ver.getProductos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adaptadorPedidos);

    }

    public void masInfoCliente(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cliente: " + c_activity_pedidos_ver.getCliente());
        builder.setMessage("id: "+ c_activity_pedidos_ver.getId_cliente()+"\n"+
                "Dirección:" + c_activity_pedidos_ver.getDireccionCliente()+"\n"+
                "Ciudad:" + c_activity_pedidos_ver.getCiudadCliente()+"\n"+
                "Telefono:" + c_activity_pedidos_ver.getTelefonoCliente());
        builder.setPositiveButton("OK",null);
        builder.setNeutralButton("Llamar Cliente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + c_activity_pedidos_ver.getTelefonoCliente()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        builder.create();
        builder.show();    }

    private static Activity_VerPedido myContext;
    public Activity_VerPedido() { myContext = this; }
    public static Activity_VerPedido getInstance() { return myContext; }


}
