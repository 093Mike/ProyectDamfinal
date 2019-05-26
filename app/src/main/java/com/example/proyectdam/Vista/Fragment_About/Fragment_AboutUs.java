package com.example.proyectdam.Vista.Fragment_About;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectdam.Controlador.Fragments.AboutUs.C_Fragment_About;
import com.example.proyectdam.R;

public class Fragment_AboutUs extends Fragment {

    TextView nom_empresa;
    C_Fragment_About c_fragment_about;
    Button incidencia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        c_fragment_about =  new C_Fragment_About();
        nom_empresa = view.findViewById(R.id.textView11);
        incidencia = view.findViewById(R.id.button6);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                nom_empresa.setText(c_fragment_about.getNombreEmpresa() + "\nPLAN: " + c_fragment_about.getPlanEmpresa());
            }
        };

        c_fragment_about.initialite(r);
        incidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + 902902902));
                    startActivity(intent);
            }
        });
    }
}
