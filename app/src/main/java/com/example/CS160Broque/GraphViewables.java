package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
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
    String jsonMyAccount;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphscreen);

        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        graphview = findViewById(R.id.gv_totalBudgetGraph_graphscreen);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                // on below line we are adding
                // each point on our x and y axis.
                //use loop here to insert datapoints
                //in here for each datapoint, x will be the transaction index, y will be the amount spent at that index
                new DataPoint(10, 0),
                new DataPoint(20, 1000),
                new DataPoint(30, 1500),
                new DataPoint(40, 1550),
                new DataPoint(50, 1625),
                new DataPoint(60, 2765),
                new DataPoint(70, 3000),
                new DataPoint(80, 3224),
                new DataPoint(90, 3500)
        });

        graphview.addSeries(series);
        graphview.getViewport().setMinX(0);
        graphview.getViewport().setMinY(0);
        graphview.getViewport().setMaxX(account.getTotalExpense());   //set this to amt of all purchases so far, every purchase increment by 1
        graphview.getViewport().setMaxY(10); //set this to the amount of transactions so far in the specified category
        graphview.getViewport().setXAxisBoundsManual(true);
        graphview.getViewport().setYAxisBoundsManual(true);

        dashboard = (Button) findViewById(R.id.btn_dashboard_graphscreen);
        spending = (Button) findViewById(R.id.btn_continue_graphscreen);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(GraphViewables.this, Dashboard.class);
                dashboardIntent.putExtra("Account", new Gson().toJson(account));
                startActivity(dashboardIntent);
            }
        });

        spending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graphViewCategoriesIntent = new Intent(GraphViewables.this, GraphViewCategories.class);
                graphViewCategoriesIntent.putExtra("Account", new Gson().toJson(account));;
                startActivity(graphViewCategoriesIntent);
            }
        });
    }
}

