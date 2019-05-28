package com.example.proyectdam.Vista.Fragment_Pedidos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.proyectdam.Controlador.Fragments.Pedidos.AdaptadorPedidos;
import com.example.proyectdam.Controlador.Fragments.Pedidos.C_Fragment_MenuPedidos;
import com.example.proyectdam.R;

public class Fragment_MenuPedidos  extends Fragment {

    public C_Fragment_MenuPedidos c_fragment_menuPedidos;
    public RecyclerView recyclerView;
    public AdaptadorPedidos adaptadorPedidos;
    private CheckBox recibido,enprogres,sendit,completed,canceled;
    private CheckBox all_filters [] = {recibido,enprogres,sendit,completed,canceled};
    private String[] tags = {"RECIBIDO ","EN PROGRESO ","ENVIADO ","COMPLETADO ","CANCELADO "};
    private String[] colores = {"#A9F5A9","#58FA58","#58ACFA","#F5A9F2","#FA5858"};
    private int[] id_all = {R.id.check1,R.id.check2,R.id.check3,R.id.check4,R.id.check5};
    boolean[] setCh;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedidos, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setCh = new boolean[]{true,true,true,true,true};

        c_fragment_menuPedidos = new C_Fragment_MenuPedidos();
        recyclerView = view.findViewById(R.id.reciclerpedidos);
        c_fragment_menuPedidos.initialite();
        for (int i = 0; i < all_filters.length;i++){
            all_filters[i] = view.findViewById(id_all[i]);
            all_filters[i].setTag(tags[i]);
            all_filters[i].setText(tags[i]);
            all_filters[i].setChecked(c_fragment_menuPedidos.getCheck()[i]);
            all_filters[i].setBackgroundColor(Color.parseColor(colores[i]));
        }
        adaptadorPedidos = new AdaptadorPedidos(R.layout.item_pedidos , c_fragment_menuPedidos.getPedidos());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorPedidos);

    }
    public void gestioFiltro(View v){
        if (tags[0].equals(v.getTag())) {
            setCh[0] = !setCh[0];
        }
        if (tags[1].equals(v.getTag())) {
            setCh[1] = !setCh[1];
        }
        if (tags[2].equals(v.getTag())) {
            setCh[2] = !setCh[2];
        }
        if (tags[3].equals(v.getTag())) {
            setCh[3] = !setCh[3];
        }
        if (tags[4].equals(v.getTag())) {
            setCh[4] = !setCh[4];
        }
        c_fragment_menuPedidos.setCheck(setCh);
        c_fragment_menuPedidos.actualizarFiltros();
        adaptadorPedidos = new AdaptadorPedidos(R.layout.item_pedidos , c_fragment_menuPedidos.getPedidosfiltrados());
        adaptadorPedidos.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorPedidos);

    }

    private static Fragment_MenuPedidos myContext;
    public Fragment_MenuPedidos() { myContext = this; }
    public static Fragment_MenuPedidos getInstance() { return myContext; }

}

