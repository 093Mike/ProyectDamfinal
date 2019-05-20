package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.Model.Categoria;
import com.example.proyectdam.R;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.Vista.Activity.AddProducto;
import com.example.proyectdam.Vista.Activity.ImagenProducto;
import com.example.proyectdam.Vista.Activity.ListaProductos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ProductosViewHolder> implements Filterable {

    private ArrayList<Producto> exampleList;
    private ArrayList<Producto> exampleListFull;

    public AdapterProductos(ArrayList productosCategoria){
        this.exampleList = productosCategoria;
        exampleListFull = new ArrayList<>(productosCategoria);
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.producto_adapter, viewGroup, false);
        return new ProductosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, int i) {
        String path = ListaProductos.getInstance().getApplicationContext().getFilesDir().getPath() +
                "/Productos/" + exampleList.get(i).getCategoria().getNombre() + "/";
        String[] listFiles = new File(path).list();
        for (String file : listFiles) {
            Log.d("aaa", file.substring(0,file.lastIndexOf(".")));
            if (file.substring(0,file.lastIndexOf(".")).equals(exampleList.get(i).getId())){
                path += file;
                break;
            }
        }
        try {
            Bitmap bmp = BitmapFactory.decodeFile(path);
            productosViewHolder.imageView_imagenProducto.setImageBitmap(bmp);
            productosViewHolder.imageView_imagenProducto.setTag(path);
        } catch (Exception e){

        }
        productosViewHolder.textView_nombreProducto.setText(exampleList.get(i).getNombre() + " (" + exampleList.get(i).getId() + ")");
        productosViewHolder.textView_cantidadProducto.setText("STOCK: " + String.valueOf(exampleList.get(i).getCantidad()));
        productosViewHolder.textView_ubicacion.setText("UBICACIÓN: " + exampleList.get(i).getUbicacion());
        productosViewHolder.textView_proveedor.setText("PROVEEDOR: " + exampleList.get(i).getProveedor());
        productosViewHolder.textView_precioProveedor.setText("PRECIO PROVEEDOR: " + String.valueOf(exampleList.get(i).getPrecioProveedor()) + " €");
        productosViewHolder.textView_precioPVP.setText("PVP: " + String.valueOf(exampleList.get(i).getPrecioPVP()) + " €");
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Producto> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Producto item : exampleListFull){
                    if (item.getNombre().toLowerCase().trim().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView_imagenProducto;
        public TextView textView_nombreProducto,
                textView_cantidadProducto,
                textView_ubicacion,
                textView_proveedor,
                textView_precioProveedor,
                textView_precioPVP;
        public CardView cardView;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_imagenProducto = itemView.findViewById(R.id.imageView_producto);
            textView_nombreProducto = itemView.findViewById(R.id.textView_nombreProducto);
            textView_cantidadProducto = itemView.findViewById(R.id.textView_cantidadProducto);
            textView_ubicacion = itemView.findViewById(R.id.textView_ubicacionProducto);
            textView_proveedor = itemView.findViewById(R.id.textView_proveedor);
            textView_precioProveedor = itemView.findViewById(R.id.textView_precioProveedor);
            textView_precioPVP = itemView.findViewById(R.id.textView_precioPVP);
            cardView = itemView.findViewById(R.id.cadView_producto);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Implementar metodos IntentsMenu.class para abrir intents
                    Log.d("aaa", "Click en el cardView");
                    Intent intent = new IntentsMenu().gestioIntent("MODIFICAR_PRODUCTO");
                    intent.putExtra("productoModificar", exampleList.get(getLayoutPosition()));
                    intent.putExtra("rutaImagen", imageView_imagenProducto.getTag().toString());
                    v.getContext().startActivity(intent);
                }
            });

            imageView_imagenProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new IntentsMenu().gestioIntent("AMPLIAR_IMAGEN_PRODUCTO");
                    intent.putExtra("productoString", textView_nombreProducto.getText().toString());
                    intent.putExtra("rutaImagen", imageView_imagenProducto.getTag().toString());
                    ListaProductos.getInstance().startActivity(intent);
                }
            });
        }
    }
}