package com.example.proyectdam.controllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectdam.R;
import com.example.proyectdam.models.almacen.Categoria;
import com.example.proyectdam.models.almacen.Producto;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ProductosViewHolder> {

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

    public static class ProductosViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView_imagenProducto;
        public TextView textView_nombreProducto;
        public TextView textView_cantidadProducto;
        public TextView textView_ubicacion;
        public TextView textView_proveedor;
        public TextView textView_precioProveedor;
        public TextView textView_precioPVP;

        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView_imagenProducto = itemView.findViewById(R.id.imageView_producto);
            textView_nombreProducto = itemView.findViewById(R.id.textView_nombreProducto);
            textView_cantidadProducto = itemView.findViewById(R.id.textView_cantidadProducto);
            textView_ubicacion = itemView.findViewById(R.id.textView_ubicacionProducto);
            textView_proveedor = itemView.findViewById(R.id.textView_proveedor);
            textView_precioProveedor = itemView.findViewById(R.id.textView_precioProveedor);
            textView_precioPVP = itemView.findViewById(R.id.textView_precioPVP);
        }
    }
}