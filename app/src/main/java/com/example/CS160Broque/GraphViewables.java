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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        double runningTotal = 0;

//        account.setTotalExpense(account.getBillsExpense()+account.getFoodExpense()+account.getOtherExpense()+account.getEntertainmentExpense());

        List<DataPoint> listOfDatapoints = new ArrayList<>();
        listOfDatapoints.add(new DataPoint(0, 0));

        for(int i = 1; i <= account.getTotalTransactions().size(); i++) {
            runningTotal += account.getTotalTransactions().get(i);
            listOfDatapoints.add(new DataPoint(i, runningTotal));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(listOfDatapoints.toArray(new DataPoint[0]));

        graphview.addSeries(series);
        graphview.getViewport().setMinX(0);
        graphview.getViewport().setMinY(0);
        graphview.getViewport().setMaxX(account.getTotalTransactions().size());
        graphview.getViewport().setMaxY(account.getTotalBudget());
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

