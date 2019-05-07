package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.proyectdam.R;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Vista.Activity.ListaProductos;

import java.util.ArrayList;

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.CategoriasViewHolder> {

    private ArrayList<Categoria> categoriasProductos;

    public AdapterCategorias(ArrayList<Categoria> categoriasProductos){
        this.categoriasProductos = categoriasProductos;
    }

    @NonNull
    @Override
    public CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categoria_adapter, viewGroup, false);
        return new CategoriasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasViewHolder categoriasViewHolder, int i) {
        switch (categoriasProductos.get(i).getNombre()){
            case "Hardware":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_hardware);
                break;
            case "Drones":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_drones);
                break;
            case "Televisores":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_televisores);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return categoriasProductos.size();
    }

    public class CategoriasViewHolder extends RecyclerView.ViewHolder {

        //public Button button_categoria;
        public ImageButton button_categoria;

        public CategoriasViewHolder(@NonNull final View itemView) {
            super(itemView);
            button_categoria = itemView.findViewById(R.id.imageButton_categoria);
            //button_categoria = itemView.findViewById(R.id.button_categoria);
            button_categoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("proyecto", "adapterPosition: " + String.valueOf(getAdapterPosition()) + " // itemId: " +
                            getItemId() + " // itemViewType: " + getItemViewType() + " // layoutPosition: " + getLayoutPosition() +
                            " // oldPosition: " + getOldPosition());

                    itemView.getContext().startActivity(new Intent(itemView.getContext(), ListaProductos.class).
                            putExtra("categoria", categoriasProductos.get(getAdapterPosition())));
                }
            });
        }
    }
}