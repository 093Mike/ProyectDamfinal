package com.example.proyectdam.Controlador.Activitys.Almacen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.Model.Incidencia;
import com.example.proyectdam.Model.Producto;
import com.example.proyectdam.R;

import java.util.ArrayList;

public class AdapterIncidencias extends RecyclerView.Adapter<AdapterIncidencias.IncidenciasViewHolder> {
    private C_Almacen c_almacen;
    private ArrayList<Incidencia> incidencias;

    public AdapterIncidencias(ArrayList incidencias){
        this.incidencias = incidencias;
        c_almacen = new C_Almacen();
    }

    @NonNull
    @Override
    public IncidenciasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_incidencia, viewGroup, false);
        return new IncidenciasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncidenciasViewHolder incidenciasViewHolder, int i) {
        Producto p = c_almacen.buscaProducto(incidencias.get(i).getIdProducto());
        incidenciasViewHolder.textView_motivo.setText(incidencias.get(i).getMotivo());
        incidenciasViewHolder.textView_cantidad.setText("CANTIDAD: " + incidencias.get(i).getCantidad());
        incidenciasViewHolder.textView_detalles.setText("DETALLES\n" + incidencias.get(i).getDetalles());
        incidenciasViewHolder.textView_producto.setText(p.getId() + " - " + p.getNombre());
    }

    @Override
    public int getItemCount() {
        return incidencias.size();
    }

    public class IncidenciasViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_motivo,
                textView_producto,
                textView_cantidad,
                textView_detalles;

        public IncidenciasViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_cantidad = itemView.findViewById(R.id.textView_cantidad_incidencia);
            textView_detalles = itemView.findViewById(R.id.textView_detalle_incidencia);
            textView_producto = itemView.findViewById(R.id.textView_producto_incidencia);
            textView_motivo = itemView.findViewById(R.id.textView_motivo_incidencia);
        }
    }
}
