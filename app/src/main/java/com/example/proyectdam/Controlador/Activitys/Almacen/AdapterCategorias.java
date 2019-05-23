package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.proyectdam.Vista.Activity.Activity_ListaCategorias;

import java.io.File;
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
        try {
            String path = Activity_ListaCategorias.getInstance().getFilesDir().getPath() +
                    "/Productos/imagenes categoria/";
            String[] listFiles = new File(path).list();
            for (String file : listFiles) {
                if (file.substring(0,file.lastIndexOf(".")).equals(categoriasProductos.get(i).getId())){
                    path += file;
                    break;
                }
            }
            Bitmap bmp = BitmapFactory.decodeFile(path);
            categoriasViewHolder.button_categoria.setImageBitmap(bmp);
        } catch (Exception e){
            Log.d("AdapterCategorias", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return categoriasProductos.size();
    }

    public class CategoriasViewHolder extends RecyclerView.ViewHolder {

        public ImageButton button_categoria;

        public CategoriasViewHolder(@NonNull View itemView) {
            super(itemView);
            button_categoria = itemView.findViewById(R.id.imageButton_categoria);
            button_categoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentsMenu intentsMenu = new IntentsMenu();
                    Intent intent = intentsMenu.gestioIntent(button_categoria.getTag().toString().toUpperCase());
                    Activity_ListaCategorias.getInstance().startActivity(intent.
                            putExtra("categoria", categoriasProductos.get(getAdapterPosition())));
                }
            });
        }
    }
}