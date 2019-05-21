package com.example.proyectdam.Vista.Fragment_Proveedores;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectdam.Controlador.Fragments.Proveedores.Adapter_Todos_Proveedores_Fragment;
import com.example.proyectdam.Controlador.Fragments.Proveedores.C_Fragment_Proveedor;
import com.example.proyectdam.Model.Proveedor;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;

public class Fragment_Proveedores extends Fragment {

    RecyclerView recyclerView;
    public Adapter_Todos_Proveedores_Fragment proveedores_fragment;
    C_Fragment_Proveedor c_fragment_proveedor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_proveedores, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        c_fragment_proveedor = new C_Fragment_Proveedor();
        c_fragment_proveedor.initialite();
        recyclerView = view.findViewById(R.id.all_proveedores);
        proveedores_fragment = new Adapter_Todos_Proveedores_Fragment(R.layout.item_clientes,c_fragment_proveedor.getProveedores());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(proveedores_fragment);
    }


    public void alertClient(){
        final Proveedor proveedor = c_fragment_proveedor.getProveedores().get(Adapter_Todos_Proveedores_Fragment.position);
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Menu.getInstance());
        builder.setTitle("Proveedor: " + proveedor.getNombre());
        builder.setMessage("id: "+ proveedor.getIdProveedor()+"\n"+
                "Nombre:" + proveedor.getNombre()+"\n"+
                "Correo:" + proveedor.getCorreoContacto()+"\n"+
                "Telefono:" + proveedor.getTelefonoContacto());
        builder.setPositiveButton("OK",null);
        builder.setNeutralButton("Llamar Cliente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + proveedor.getTelefonoContacto()));
                if (intent.resolveActivity(Activity_Menu.getInstance().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton("Enviar Correo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, Uri.fromParts("mailto:",proveedor.getCorreoContacto(), null));
                startActivity(Intent.createChooser(emailIntent,"Enviar Correo"));
            }
        });
        builder.create();
        builder.show();
    }



    private static Fragment_Proveedores myContext;
    public Fragment_Proveedores() { myContext = this; }
    public static Fragment_Proveedores getInstance() { return myContext; }
}
