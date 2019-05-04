package com.example.proyectdam.Controlador.Activitys.GestioUser;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectdam.Model.User;
import com.example.proyectdam.R;

import java.util.ArrayList;

public class AdaptadorUsers extends RecyclerView.Adapter<AdaptadorUsers.ViewHolder> {

    private int listItemLayout;
    private ArrayList<User> itemList;

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
        nombre.setText(itemList.get(i).getNombre());
    }

    @Override
    public int getItemCount() { return itemList == null ? 0 : itemList.size(); }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.t_categoria);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
