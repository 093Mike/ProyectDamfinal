package com.example.proyectdam.Vista.Fragment_Pedidos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectdam.Controlador.Activitys.Pedidos.AdaptadorPedidos;
import com.example.proyectdam.Controlador.Activitys.Pedidos.C_Fragment_MenuPedidos;
import com.example.proyectdam.R;

public class Fragment_MenuPedidos  extends Fragment {

    C_Fragment_MenuPedidos c_fragment_menuPedidos;
    RecyclerView recyclerView;
    public AdaptadorPedidos adaptadorPedidos;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        c_fragment_menuPedidos = new C_Fragment_MenuPedidos();
        recyclerView = view.findViewById(R.id.reciclerpedidos);
        c_fragment_menuPedidos.initialite();
        adaptadorPedidos = new AdaptadorPedidos(R.layout.item_pedidos , c_fragment_menuPedidos.getPedidos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorPedidos);



    }

    private static Fragment_MenuPedidos myContext;
    public Fragment_MenuPedidos() { myContext = this; }
    public static Fragment_MenuPedidos getInstance() { return myContext; }

}

