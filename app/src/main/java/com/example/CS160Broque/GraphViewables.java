package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.CS160Broque.R;

public class GraphViewables extends AppCompatActivity {
    Button dashboard;
    Button spending;
    GraphView graphview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphscreen);

        graphview = findViewById(R.id.gv_totalBudgetGraph_graphscreen);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 0),
                new DataPoint(1, 1000),
                new DataPoint(2, 1500),
                new DataPoint(3, 1550),
                new DataPoint(4, 1625),
                new DataPoint(5, 2765),
                new DataPoint(6, 3000),
                new DataPoint(7, 3224),
                new DataPoint(8, 3500)
        });

        graphview.addSeries(series);
        graphview.getViewport().setMinX(0);
        graphview.getViewport().setMinY(0);
        graphview.getViewport().setMaxX(8);   //set this to amt of all purchases so far, every purchase increment by 1
        graphview.getViewport().setMaxY(10000); //set this to whatever totalbudget is
        graphview.getViewport().setXAxisBoundsManual(true);
        graphview.getViewport().setYAxisBoundsManual(true);

        dashboard = (Button) findViewById(R.id.btn_dashboard_graphscreen);
        spending = (Button) findViewById(R.id.btn_continue_graphscreen);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(GraphViewables.this, Dashboard.class);
                startActivity(dashboardIntent);
            }
        });

        spending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graphViewCategoriesIntent = new Intent(GraphViewables.this, GraphViewCategories.class);
                startActivity(graphViewCategoriesIntent);
            }
        });
    }
}

