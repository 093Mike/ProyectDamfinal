package com.example.proyectdam.Vista.Fragment_Clientes;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectdam.Controlador.Fragments.Clientes.Adapter_Todos_Clientes_Fragment;
import com.example.proyectdam.Controlador.Fragments.Clientes.C_Fragment_Clientes;
import com.example.proyectdam.Model.Cliente;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;

public class Fragment_Clientes extends Fragment {
    C_Fragment_Clientes c_fragment_clientes;
    RecyclerView recyclerView;
    public Adapter_Todos_Clientes_Fragment adapter_todos_clientes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clientes, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.clientes_all);
        c_fragment_clientes = new C_Fragment_Clientes();
        c_fragment_clientes.initialite();
        adapter_todos_clientes = new Adapter_Todos_Clientes_Fragment(R.layout.item_clientes,c_fragment_clientes.getClientes());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Activity_Menu.getInstance(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter_todos_clientes);
    }


    public void alertClient(){
        final Cliente cliente = c_fragment_clientes.getClientes().get(Adapter_Todos_Clientes_Fragment.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Menu.getInstance());
        builder.setTitle("Cliente: " + cliente.getNombre());
        builder.setMessage("id: "+ cliente.getId()+"\n"+
                "Dirección:" + cliente.getDireccion()+"\n"+
                "Ciudad:" + cliente.getCiudad()+"\n"+
                "Telefono:" + cliente.getTelefono());
        builder.setPositiveButton("OK",null);
        builder.setNeutralButton("Llamar Cliente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + cliente.getTelefono()));
                if (intent.resolveActivity(Activity_Menu.getInstance().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        builder.create();
        builder.show();
    }



    private static Fragment_Clientes myContext;
    public Fragment_Clientes() { myContext = this; }
    public static Fragment_Clientes getInstance() { return myContext; }
}
