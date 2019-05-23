package com.example.proyectdam.Controlador.Activitys.Almacen.Movimientos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.Model.Movimientos;
import com.example.proyectdam.R;

import java.util.ArrayList;

public class Adapter_movimientos extends RecyclerView.Adapter<Adapter_movimientos.ViewHolder>{

    private ArrayList<Movimientos> itemList;

    public static int position;

    public Adapter_movimientos(ArrayList<Movimientos> itemList) {
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movimento, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView desc;
        desc = viewHolder.desc;
        desc.setText( itemList.get(i).getIdproducto()+"\n"+
                itemList.get(i).getDescripcion());

    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView  desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.mov_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
        }
    }
}
