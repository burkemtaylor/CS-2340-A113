package com.example.burketaylor.rattracker.controller;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.burketaylor.rattracker.model.GraphData;
import com.example.burketaylor.rattracker.model.GraphDataManager;
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

    private PopupWindow optionsWindow;
    private TextView startDateText;
    private TextView endDateText;
    private DatePicker startPicker;
    private DatePicker endPicker;
    private LineChart lineChart;
    private ProgressBar graphProgress;
    private Button graphOptionsButton;

    private int start = 20160100;
    private int end = 20161200;

    private GraphData data = new GraphData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = (LineChart) findViewById(R.id.chart);
        graphProgress = (ProgressBar) findViewById(R.id.graphProgressBar);
        graphOptionsButton = (Button) findViewById(R.id.button2);
        lineChart.setNoDataText("Loading data. This may take a while...");
        new DataTask().execute();



    }

    private List<Entry> convertDataSetToEntry(List<GraphData> data) {
        List<Entry> entries = new ArrayList<>();

        for (GraphData d : data) {
            entries.add(new Entry(d.getMonthYear(), d.getSightings()));
        }

        return entries;
    }

    public void showOptions(View view){
        View optionsView = getLayoutInflater().inflate(R.layout.map_options, null);

        optionsWindow = new PopupWindow(optionsView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        optionsWindow.setFocusable(true);
        optionsWindow.setBackgroundDrawable(new ColorDrawable());
        //numRangeSpinner = (Spinner) optionsView.findViewById(R.id.numRangeSpinner);

        /*ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, SPINNER_RANGE);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numRangeSpinner.setAdapter(adapter);*/

        startDateText = (TextView) optionsView.findViewById(R.id.startDateView);
        endDateText = (TextView) optionsView.findViewById(R.id.endDateView);
        //rangeText = (TextView) optionsView.findViewById(R.id.rangeText);
        startPicker = (DatePicker)optionsView.findViewById(R.id.startDatePicker);
        endPicker = (DatePicker) optionsView.findViewById(R.id.endDatePicker);

        startDateText.setVisibility(View.VISIBLE);
        endDateText.setVisibility(View.VISIBLE);
        startPicker.setVisibility(View.VISIBLE);
        endPicker.setVisibility(View.VISIBLE);
        optionsView.findViewById(R.id.optionRadioGroup).setVisibility(View.GONE);
        optionsView.findViewById(R.id.rangeText).setVisibility(View.GONE);
        optionsView.findViewById(R.id.numRangeSpinner).setVisibility(View.GONE);
        optionsWindow.showAsDropDown(view);
    }

    public void optionConfirm(View view) {
        int dayS = startPicker.getDayOfMonth();
        String daySString;
        if (dayS < 10){
            daySString = "0"+Integer.toString(dayS);
        } else {
            daySString = Integer.toString(dayS);
        }
        int monthS = startPicker.getMonth();
        String monthSString;
        if (monthS < 10){
            monthSString = "0"+Integer.toString(monthS);
        } else {
            monthSString = Integer.toString(monthS);
        }
        String startDateString = Integer.toString(startPicker.getYear()) + monthSString + daySString;

        int dayE = endPicker.getDayOfMonth();
        String dayEString;
        if (dayE < 10){
            dayEString = "0"+Integer.toString(dayE);
        } else {
            dayEString = Integer.toString(dayE);
        }
        int monthE = endPicker.getMonth();
        String monthEString;
        if (monthE < 10){
            monthEString = "0"+Integer.toString(monthE);
        } else {
            monthEString = Integer.toString(monthE);
        }
        String endDateString = Integer.toString(endPicker.getYear()) + monthEString + dayEString;
        Log.d("startdate", startDateString);
        Log.d("enddate", endDateString);

        start = (Integer.parseInt(startDateString) / 100) * 100;
        end = (Integer.parseInt(endDateString) / 100) * 100;

        new DataTask().execute();
        optionsWindow.dismiss();
    }

    private class DataTask extends AsyncTask<Void, Void, Void> {
        private LineData data;

        @Override
        protected void onPreExecute() {
            graphProgress.setVisibility(View.VISIBLE);
            graphOptionsButton.setVisibility(View.INVISIBLE);
            lineChart.clear();

        }

        @Override
        protected Void doInBackground(Void[] params) {


            GraphDataManager.clear();
            GraphDataManager.generateGraphData(start, end);

            List<Entry> entries = convertDataSetToEntry(GraphDataManager.getDataList());

            LineDataSet dataset = new LineDataSet(entries, "# of Calls");

            Log.d("APP", "Made dataset with : " + entries.size());

            data = new LineData(dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawFilled(true);





            return null;
        }

        @Override
        protected void onPostExecute(Void stuff) {
            graphProgress.setVisibility(View.INVISIBLE);
            graphOptionsButton.setVisibility(View.VISIBLE);


            lineChart.setData(data);
            lineChart.animateY(5000);

            lineChart.getDescription().setText("Rat Sightings per Month");

        }
    }
}
