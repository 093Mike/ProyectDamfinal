package com.example.proyectdam.Controlador.Activitys.Pedidos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_ModPedido;

import java.util.ArrayList;

public class Adapter_Pedidos_AddMod extends RecyclerView.Adapter<Adapter_Pedidos_AddMod.ViewHolder>{

    private int listItemLayout;
    private ArrayList<String> itemList;

    public static int position;


    public Adapter_Pedidos_AddMod(int listItemLayout, ArrayList<String> itemList) {
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

        Double precioproducto = Activity_ModPedido.getInstance().c_activity_pedidos_mod.precios.get(i);
        precioproducto= precioproducto * Activity_ModPedido.getInstance().c_activity_pedidos_mod.pedidoActual.getProductos().get(i).getCantidad();

        titulo.setText(itemList.get(i)+"\n"+
                Activity_ModPedido.getInstance().c_activity_pedidos_mod.pedidoActual.getProductos().get(i).getCantidad()+" unidades");
        precio.setText(precioproducto+"€");
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titulo, precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textView40);
            precio = itemView.findViewById(R.id.textView41);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    position = getAdapterPosition();
                    Activity_ModPedido.getInstance().onCreateDialogRemove();
                    return false;
                }
            });

        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            Activity_ModPedido.getInstance().editarCantidades();
        }
    }
}
