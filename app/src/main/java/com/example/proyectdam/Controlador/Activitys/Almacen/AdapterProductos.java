package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectdam.Controlador.IntentsMenu;
import com.example.proyectdam.R;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.Vista.Activity.Activity_Menu;
import com.example.proyectdam.Vista.Activity.ListaProductos;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ProductosViewHolder> implements Filterable {

    private ArrayList<Producto> productos;

    public AdapterProductos(ArrayList<Producto> productos){
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.producto_adapter, viewGroup, false);
        return new ProductosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder productosViewHolder, int i) {
        //productosViewHolder.imageView_imagenProducto.setImageBitmap();
        productosViewHolder.textView_nombreProducto.setText(productos.get(i).getNombre());
        productosViewHolder.textView_cantidadProducto.setText(String.valueOf(productos.get(i).getCantidad()));
        //productosViewHolder.textView_ubicacion.setText(productos.get(i).getUbicacion());
        productosViewHolder.textView_proveedor.setText(productos.get(i).getProveedor());
        productosViewHolder.textView_precioProveedor.setText(String.valueOf(productos.get(i).getPrecioProveedor()));
        productosViewHolder.textView_precioPVP.setText(String.valueOf(productos.get(i).getPrecioPVP()));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ProductosViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView_imagenProducto;
        public TextView textView_nombreProducto,
                textView_cantidadProducto,
                textView_ubicacion,
                textView_proveedor,
                textView_precioProveedor,
                textView_precioPVP;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_imagenProducto = itemView.findViewById(R.id.imageView_producto);
            textView_nombreProducto = itemView.findViewById(R.id.textView_nombreProducto);
            textView_cantidadProducto = itemView.findViewById(R.id.textView_cantidadProducto);
            textView_ubicacion = itemView.findViewById(R.id.textView_ubicacionProducto);
            textView_proveedor = itemView.findViewById(R.id.textView_proveedor);
            textView_precioProveedor = itemView.findViewById(R.id.textView_precioProveedor);
            textView_precioPVP = itemView.findViewById(R.id.textView_precioPVP);

            imageView_imagenProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: enviar imagen del viewHolder a ImagenProducto.class

//                    Bitmap bm = ((StateListDrawable)imageView_imagenProducto.getDrawable())
//
//
//                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//                    bm.compress(Bitmap.CompressFormat.PNG, 100, bStream);
//                    byte[] imagen = bStream.toByteArray();
//
//                    IntentsMenu intentsMenu = new IntentsMenu();
//                    Intent intent = intentsMenu.gestioIntent(imageView_imagenProducto.getTag().toString().toUpperCase());
//                    ListaProductos.getInstance().startActivity(intent.putExtra("imagen", imagen));


                }
            });
        }
    }
}