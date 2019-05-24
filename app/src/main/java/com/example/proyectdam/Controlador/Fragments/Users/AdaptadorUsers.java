package com.example.proyectdam.Controlador.Fragments.Users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.Model.User;
import com.example.proyectdam.R;
import com.example.proyectdam.Vista.Activity.Activity_Menu;

import java.util.ArrayList;

public class AdaptadorUsers extends RecyclerView.Adapter<AdaptadorUsers.ViewHolder> {

    private int listItemLayout;
    private ArrayList<User> itemList;
    public static int position;


    public AdaptadorUsers(int listItemLayout, ArrayList<User> itemList) {
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
        TextView nombre = viewHolder.nombre;
        TextView encargo = viewHolder.encargo;
        TextView tipo = viewHolder.tipo;
        nombre.setText(itemList.get(i).getNombre());
        encargo.setText(itemList.get(i).getPermisos());
        tipo.setText(itemList.get(i).getTipo_permisos());
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size(); }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre,encargo,tipo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.pedidoid);
            encargo = itemView.findViewById(R.id.preciototal);
            tipo = itemView.findViewById(R.id.num_pedidos);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            Activity_Menu.getInstance().c_activity_menu.selecionarUser(view);
        }
    }
}
