package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.CS160Broque.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphViewCategories extends AppCompatActivity {
    Button dashboard;
    Button back;
    GraphView billsGraph, foodGraph, entertainmentGraph, othersGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphviewscreen);

        final String userNameIdentifier = getIntent().getStringExtra("userName");

        dashboard = (Button) findViewById(R.id.btn_dashboard_graphviewscreen);
        back = (Button) findViewById(R.id.btn_back_graphviewscreen);

        billsGraph = findViewById(R.id.gv_billsGraph_graphviewscreen);
        foodGraph = findViewById(R.id.gv_foodGraph_graphviewscreen);
        entertainmentGraph = findViewById(R.id.gv_entertainmentGraph_graphviewscreen);
        othersGraph = findViewById(R.id.gv_othersGraph_graphviewscreen);

        LineGraphSeries<DataPoint> billsSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 0),
                new DataPoint(1, 1000),
                new DataPoint(2, 1500),
                new DataPoint(3, 1600),
                new DataPoint(4, 1700),
                new DataPoint(5, 1800),
                new DataPoint(6, 1900)
        });

        billsGraph.addSeries(billsSeries);
        billsGraph.getViewport().setMinX(0);
        billsGraph.getViewport().setMinY(0);
        billsGraph.getViewport().setMaxX(6);   //set this to amt of bills paid so far, every bill pay increment by 1
        billsGraph.getViewport().setMaxY(2500); //set this to whatever totalbills is
        billsGraph.getViewport().setXAxisBoundsManual(true);
        billsGraph.getViewport().setYAxisBoundsManual(true);

        LineGraphSeries<DataPoint> foodSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 0),
                new DataPoint(1, 20),
                new DataPoint(2, 30),
                new DataPoint(3, 49),
                new DataPoint(4, 61),
                new DataPoint(5, 94),
                new DataPoint(6, 121)
        });

        foodGraph.addSeries(foodSeries);
        foodGraph.getViewport().setMinX(0);
        foodGraph.getViewport().setMinY(0);
        foodGraph.getViewport().setMaxX(6);   //set this to amt of food bought so far, every food bought increment by 1
        foodGraph.getViewport().setMaxY(2500); //set this to whatever totalfood is
        foodGraph.getViewport().setXAxisBoundsManual(true);
        foodGraph.getViewport().setYAxisBoundsManual(true);

        LineGraphSeries<DataPoint> entertainmentSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 0),
                new DataPoint(1, 20),
                new DataPoint(2, 100),
                new DataPoint(3, 400),
                new DataPoint(4, 595),
                new DataPoint(5, 800),
                new DataPoint(6, 900)
        });

        entertainmentGraph.addSeries(entertainmentSeries);
        entertainmentGraph.getViewport().setMinX(0);
        entertainmentGraph.getViewport().setMinY(0);
        entertainmentGraph.getViewport().setMaxX(6);   //set this to amt of entertainment bought so far
        entertainmentGraph.getViewport().setMaxY(2500); //set this to whatever totalentertainment is
        entertainmentGraph.getViewport().setXAxisBoundsManual(true);
        entertainmentGraph.getViewport().setYAxisBoundsManual(true);

        LineGraphSeries<DataPoint> othersSeries = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                new DataPoint(0, 0),
                new DataPoint(1, 400),
                new DataPoint(2, 450),
                new DataPoint(3, 650),
                new DataPoint(4, 730),
                new DataPoint(5, 890),
                new DataPoint(6, 1010)
        });

        othersGraph.addSeries(othersSeries);
        othersGraph.getViewport().setMinX(0);
        othersGraph.getViewport().setMinY(0);
        othersGraph.getViewport().setMaxX(6);   //set this to amt of others bought so far
        othersGraph.getViewport().setMaxY(2500); //set this to whatever totalothers is
        othersGraph.getViewport().setXAxisBoundsManual(true);
        othersGraph.getViewport().setYAxisBoundsManual(true);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(GraphViewCategories.this, Dashboard.class);
                dashboardIntent.putExtra("userName", userNameIdentifier);
                startActivity(dashboardIntent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graphViewCategoriesIntent = new Intent(GraphViewCategories.this, GraphViewables.class);
                graphViewCategoriesIntent.putExtra("userName", userNameIdentifier);
                startActivity(graphViewCategoriesIntent);
            }
        });
    }
}
