package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_VerPedido;

import java.util.ArrayList;

public class Adapter_Pedidos_Ver extends RecyclerView.Adapter<Adapter_Pedidos_Ver.ViewHolder> {

    private int listItemLayout;
    private ArrayList<String> itemList;

    public static int position;


    public Adapter_Pedidos_Ver(int listItemLayout, ArrayList<String> itemList) {
        this.listItemLayout = listItemLayout;
        this.itemList = itemList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView titulo = viewHolder.titulo;
        TextView precio = viewHolder.precio;

        Double precioproducto = Activity_VerPedido.getInstance().c_activity_pedidos_ver.precios.get(i);
        precioproducto= precioproducto * Activity_VerPedido.getInstance().c_activity_pedidos_ver.pedidoActual.getProductos().get(i).getCantidad();

        titulo.setText(itemList.get(i)+"\n"+
                Activity_VerPedido.getInstance().c_activity_pedidos_ver.pedidoActual.getProductos().get(i).getCantidad()+" unidades");
        precio.setText(precioproducto+"€");
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size();    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView titulo, precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textView23);
            precio = itemView.findViewById(R.id.textView25);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
