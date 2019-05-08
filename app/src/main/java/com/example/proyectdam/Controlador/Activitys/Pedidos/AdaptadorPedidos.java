package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.R;

import java.util.ArrayList;

public class AdaptadorPedidos  extends RecyclerView.Adapter<AdaptadorPedidos.ViewHolder> {
    private int listItemLayout;
    private ArrayList<Pedido> itemList;
    public static int position;

    public AdaptadorPedidos(int listItemLayout, ArrayList<Pedido> itemList) {
        this.listItemLayout = listItemLayout;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemLayout, viewGroup, false);
        return new AdaptadorPedidos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPedidos.ViewHolder viewHolder, int i) {
        TextView id_pedido = viewHolder.id_pedido;
        TextView total = viewHolder.total;
        TextView num_productos = viewHolder.num_productos;
        id_pedido.setText("PEDIDO #"+itemList.get(i).getId());
        total.setText(itemList.get(i).getPrecioTotal() + "€");
        num_productos.setText("Productos");
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id_pedido,total,num_productos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_pedido =itemView.findViewById(R.id.id_pedido);
            total = itemView.findViewById(R.id.total_precio);
            num_productos = itemView.findViewById(R.id.num_productos);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
