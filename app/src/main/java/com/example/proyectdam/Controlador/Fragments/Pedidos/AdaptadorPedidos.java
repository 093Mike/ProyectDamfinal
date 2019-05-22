package com.example.proyectdam.Controlador.Fragments.Pedidos;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Activitys.C_Activity_Menu;
import com.example.proyectdam.Model.Pedido;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Fragment_Pedidos.Fragment_MenuPedidos;

import java.util.ArrayList;

public class AdaptadorPedidos  extends RecyclerView.Adapter<AdaptadorPedidos.ViewHolder> {
    private int listItemLayout;
    private ArrayList<Pedido> itemList;
    public static int position;


    public AdaptadorPedidos(int listItemLayout, ArrayList<Pedido> itemList) {
        this.listItemLayout = listItemLayout;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(listItemLayout, viewGroup, false);
        return new AdaptadorPedidos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPedidos.ViewHolder viewHolder, int i) {
        TextView id_pedido = viewHolder.id_pedido;
        TextView total = viewHolder.total;
        TextView num_productos = viewHolder.num_productos;
        boolean fin=false;
        for (int j = 0; j < Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().size(); j++){
            if(!fin) {
                if (Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(j).getId() == itemList.get(i).getId_cliente()) {
                    id_pedido.setText("PEDIDO #" + itemList.get(i).getId() + "\n" + itemList.get(i).getFecharealizado() +
                            "\n" + "Para: " + Fragment_MenuPedidos.getInstance().c_fragment_menuPedidos.getClientes().get(j).getNombre());
                    fin=true;
                }
            }
        }
        if(!fin){
            id_pedido.setText("PEDIDO #" + itemList.get(i).getId() + "\n" + itemList.get(i).getFecharealizado() +
                    "\n" + "Sin cliente escogido");
        }
        total.setText(itemList.get(i).getPrecioTotal() + "€");
        if(itemList.get(i).getProductos().size()!=0) {
            num_productos.setText(itemList.get(i).getProductos().size() + " Productos");
        }
        else{
            num_productos.setText("Pedido vacio");
        }
        int estado = itemList.get(i).getEstado();
        RelativeLayout layout = viewHolder.layout;
        switch (estado){
            case 0:
                layout.setBackgroundColor(Color.parseColor("#A9F5A9"));
                break;
            case 1:
                layout.setBackgroundColor(Color.parseColor("#58FA58"));
                break;
            case 2:
                layout.setBackgroundColor(Color.parseColor("#58ACFA"));
                break;
            case 3:
                layout.setBackgroundColor(Color.parseColor("#F5A9F2"));
                break;
            case 4:
                layout.setBackgroundColor(Color.parseColor("#FA5858"));

                break;
        }
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout layout;
        TextView id_pedido,total,num_productos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_pedido =itemView.findViewById(R.id.id_pedido);
            total = itemView.findViewById(R.id.total_precio);
            num_productos = itemView.findViewById(R.id.num_productos);
            layout =  itemView.findViewById(R.id.c_relative);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            C_Activity_Menu c_activity_menu = new C_Activity_Menu();
            c_activity_menu.selecionarPedido(view);
        }
    }
}
