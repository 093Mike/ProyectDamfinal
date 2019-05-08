package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.Vista.Activity.ListaCategorias;
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
        switch (categoriasProductos.get(i).getId()){
            case "AUD":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_audiofotovideo);
                break;
            case "COM":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_componentesinformaticos);
                break;
            case "CON":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_consolasvideojuegos);
                break;
            case "OTR":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_otros);
                break;
            case "HOG":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_hogar);
                break;
            case "ORD":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_ordenadores);
                break;
            case "PER":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_perifericos);
                break;
            case "RAD":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_radiocontrol);
                break;
            case "SMA":
                categoriasViewHolder.button_categoria.setBackgroundResource(R.drawable.final_smartphones);
                break;
            case "TEL":
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

        public CategoriasViewHolder(@NonNull View itemView) {
            super(itemView);
            button_categoria = itemView.findViewById(R.id.imageButton_categoria);
            //button_categoria = itemView.findViewById(R.id.button_categoria);
            button_categoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("proyecto", "adapterPosition: " + String.valueOf(getAdapterPosition()) + " // itemId: " +
                            getItemId() + " // itemViewType: " + getItemViewType() + " // layoutPosition: " + getLayoutPosition() +
                            " // oldPosition: " + getOldPosition());
                    Log.d("aaa", v.getTag().toString());
                    IntentsMenu intentsMenu = new IntentsMenu();
                    Intent intent = intentsMenu.gestioIntent(button_categoria.getTag().toString().toUpperCase());

                    ListaCategorias.getInstance().startActivity(intent.
                            putExtra("categoria", categoriasProductos.get(getAdapterPosition())));
                }
            });
        }
    }
}