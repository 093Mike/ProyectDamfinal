package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.Model.Cliente;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_ModPedido;

import java.util.ArrayList;

public class Adapter_Todos_Clientes extends RecyclerView.Adapter<Adapter_Todos_Clientes.ViewHolder> {

    private int listItemLayout;
    private ArrayList<Cliente> itemList;
    public static int position;

    public Adapter_Todos_Clientes(int listItemLayout, ArrayList<Cliente> itemList) {
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
        titulo.setText(itemList.get(i).getNombre());
        precio.setText(itemList.get(i).getCiudad());
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size();    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView titulo, precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.id_pedido);
            precio = itemView.findViewById(R.id.num_productos);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            Activity_ModPedido.getInstance().c_activity_pedidos_mod.setCliente(position);

        }
    }
}
