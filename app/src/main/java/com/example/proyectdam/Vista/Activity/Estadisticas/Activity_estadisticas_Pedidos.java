package com.example.proyectdam.Vista.Activity.Estadisticas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.proyectdam.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Activity_estadisticas_Pedidos extends AppCompatActivity {

    private BarChart lineChart;
    private BarDataSet lineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_pedidos);
        getSupportActionBar().hide();
        lineChart = findViewById(R.id.lineChart);
        ArrayList<BarEntry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            float y = (int) (Math.random() * 8) + 1;
            lineEntries.add(new BarEntry((float) i, y));
        }
        lineDataSet = new BarDataSet(lineEntries, "Platzi");
        BarData lineData = new BarData();
        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);
    }

}
