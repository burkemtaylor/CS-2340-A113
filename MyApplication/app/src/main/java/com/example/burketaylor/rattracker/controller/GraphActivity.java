package com.example.burketaylor.rattracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.burketaylor.rattracker.model.GraphData;
import com.example.burketaylor.rattracker.model.RatSighting;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import com.example.burketaylor.rattracker.R;

public class GraphActivity extends AppCompatActivity {

    private GraphData data = new GraphData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        //generate graph data

        List<Entry> entries = convertDataSetToEntry(data.getDataList());

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        Log.d("APP", "Made dataset with : " + entries.size());

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //

        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        lineChart.getDescription().setText("Average Calls per Month");

    }

    private List<Entry> convertDataSetToEntry(List<GraphData> data) {
        List<Entry> entries = new ArrayList<>();

        for (RatSighting d : data) {
            entries.add(new Entry(d.x, d.y));
        }

        return entries;
    }
}
