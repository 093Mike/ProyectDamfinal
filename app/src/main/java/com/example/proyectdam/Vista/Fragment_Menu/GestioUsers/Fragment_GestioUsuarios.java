package com.example.proyectdam.Vista.Fragment_Menu.GestioUsers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectdam.Controlador.Activitys.GestioUser.AdaptadorUsers;
import com.example.proyectdam.Controlador.Activitys.GestioUser.C_Activity_MenuUsers;
import com.example.proyectdam.R;

public class Fragment_GestioUsuarios extends Fragment {

    public RecyclerView recyclerView;
    C_Activity_MenuUsers c_activity_menuUsers;
    public AdaptadorUsers adaptadorUsers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gestiousers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        c_activity_menuUsers = new C_Activity_MenuUsers();
        c_activity_menuUsers.creaListaUsuarios();
        adaptadorUsers = new AdaptadorUsers(R.layout.item_users, c_activity_menuUsers.getUsers());
        recyclerView = getView().findViewById(R.id.listausers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorUsers);
    }
    private static Fragment_GestioUsuarios myContext;
    public Fragment_GestioUsuarios() { myContext = this; }
    public static Fragment_GestioUsuarios getInstance() { return myContext; }

}
