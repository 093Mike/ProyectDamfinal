package com.example.proyectdam.Vista.Fragment_Clientes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectdam.Controlador.Fragments.Clientes.Adapter_Todos_Clientes_Fragment;
import com.example.proyectdam.Controlador.Fragments.Clientes.C_Fragment_Clientes;
import com.example.proyectdam.R;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter_todos_clientes);
    }
    private static Fragment_Clientes myContext;
    public Fragment_Clientes() { myContext = this; }
    public static Fragment_Clientes getInstance() { return myContext; }
}
