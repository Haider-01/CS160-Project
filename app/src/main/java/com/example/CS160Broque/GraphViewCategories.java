package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.CS160Broque.R;
import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphViewCategories extends AppCompatActivity {
    Button dashboard;
    Button back;
    GraphView billsGraph, foodGraph, entertainmentGraph, othersGraph;
    String jsonMyAccount;
    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphviewscreen);

        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        dashboard = (Button) findViewById(R.id.btn_dashboard_graphviewscreen);
        back = (Button) findViewById(R.id.btn_back_graphviewscreen);

        billsGraph = findViewById(R.id.gv_billsGraph_graphviewscreen);
        foodGraph = findViewById(R.id.gv_foodGraph_graphviewscreen);
        entertainmentGraph = findViewById(R.id.gv_entertainmentGraph_graphviewscreen);
        othersGraph = findViewById(R.id.gv_othersGraph_graphviewscreen);

        double runningBillsTotal = 0;

        List<DataPoint> listOfBillsDatapoints = new ArrayList<>();
        listOfBillsDatapoints.add(new DataPoint(0, 0));

        HashMap<Integer, Double> hashtestOne = new HashMap<>();
        hashtestOne.put(1, 32.0);
        hashtestOne.put(2, 563.4);
        hashtestOne.put(3, 222.2);

        account.setBillsTransactions(hashtestOne);

        for(int i = 1; i <= account.getBillsTransactions().size(); i++) {
            runningBillsTotal += account.getBillsTransactions().get(i);
            listOfBillsDatapoints.add(new DataPoint(i, runningBillsTotal));
        }

        LineGraphSeries<DataPoint> billsSeries = new LineGraphSeries<>(listOfBillsDatapoints.toArray(new DataPoint[0]));

        billsGraph.addSeries(billsSeries);
        billsGraph.getViewport().setMinX(0);
        billsGraph.getViewport().setMinY(0);
        billsGraph.getViewport().setMaxX(account.getBillsTransactions().size());
        billsGraph.getViewport().setMaxY(account.getBillsBudget());
        billsGraph.getViewport().setXAxisBoundsManual(true);
        billsGraph.getViewport().setYAxisBoundsManual(true);

        double runningFoodTotal = 0;

        HashMap<Integer, Double> hashtestTwo = new HashMap<>();
        hashtestTwo.put(1, 143.0);
        hashtestTwo.put(2, 109.4);
        hashtestTwo.put(3, 94.2);

        account.setFoodTransactions(hashtestTwo);

        List<DataPoint> listOfFoodDatapoints = new ArrayList<>();
        listOfFoodDatapoints.add(new DataPoint(0, 0));

        for(int i = 1; i <= account.getFoodTransactions().size(); i++) {
            runningFoodTotal += account.getFoodTransactions().get(i);
            listOfFoodDatapoints.add(new DataPoint(i, runningFoodTotal));
        }

        LineGraphSeries<DataPoint> foodSeries = new LineGraphSeries<>(listOfFoodDatapoints.toArray(new DataPoint[0]));

        foodGraph.addSeries(foodSeries);
        foodGraph.getViewport().setMinX(0);
        foodGraph.getViewport().setMinY(0);
        foodGraph.getViewport().setMaxX(account.getFoodTransactions().size());
        foodGraph.getViewport().setMaxY(account.getFoodBudget());
        foodGraph.getViewport().setXAxisBoundsManual(true);
        foodGraph.getViewport().setYAxisBoundsManual(true);

        double runningEntertainmentTotal = 0;

        HashMap<Integer, Double> hashtestThree = new HashMap<>();
        hashtestThree.put(1, 121.0);
        hashtestThree.put(2, 19.4);
        hashtestThree.put(3, 43.2);

        account.setEntertainmentTransactions(hashtestThree);

        List<DataPoint> listOfEntertainmentDatapoints = new ArrayList<>();
        listOfEntertainmentDatapoints.add(new DataPoint(0, 0));

        for(int i = 1; i <= account.getEntertainmentTransactions().size(); i++) {
            runningEntertainmentTotal += account.getEntertainmentTransactions().get(i);
            listOfEntertainmentDatapoints.add(new DataPoint(i, runningEntertainmentTotal));
        }

        LineGraphSeries<DataPoint> entertainmentSeries = new
                LineGraphSeries<>(listOfEntertainmentDatapoints.toArray(new DataPoint[0]));

        entertainmentGraph.addSeries(entertainmentSeries);
        entertainmentGraph.getViewport().setMinX(0);
        entertainmentGraph.getViewport().setMinY(0);
        entertainmentGraph.getViewport().setMaxX(account.getEntertainmentTransactions().size());
        entertainmentGraph.getViewport().setMaxY(account.getEntertainmentBudget());
        entertainmentGraph.getViewport().setXAxisBoundsManual(true);
        entertainmentGraph.getViewport().setYAxisBoundsManual(true);

        double runningOthersTotal = 0;

        HashMap<Integer, Double> hashtestFour = new HashMap<>();
        hashtestFour.put(1, 201.0);
        hashtestFour.put(2, 66.4);
        hashtestFour.put(3, 123.2);

        account.setOthersTransactions(hashtestFour);

        List<DataPoint> listOfOthersDatapoints = new ArrayList<>();
        listOfOthersDatapoints.add(new DataPoint(0, 0));

        for(int i = 1; i <= account.getOthersTransactions().size(); i++) {
            runningOthersTotal += account.getOthersTransactions().get(i);
            listOfOthersDatapoints.add(new DataPoint(i, runningOthersTotal));
        }

        LineGraphSeries<DataPoint> othersSeries = new
                LineGraphSeries<>(listOfOthersDatapoints.toArray(new DataPoint[0]));

        othersGraph.addSeries(othersSeries);
        othersGraph.getViewport().setMinX(0);
        othersGraph.getViewport().setMinY(0);
        othersGraph.getViewport().setMaxX(account.getOthersTransactions().size());
        othersGraph.getViewport().setMaxY(account.getOtherBudget());
        othersGraph.getViewport().setXAxisBoundsManual(true);
        othersGraph.getViewport().setYAxisBoundsManual(true);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(GraphViewCategories.this, Dashboard.class);
                dashboardIntent.putExtra("Account", new Gson().toJson(account));
                startActivity(dashboardIntent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graphViewCategoriesIntent = new Intent(GraphViewCategories.this, GraphViewables.class);
                graphViewCategoriesIntent.putExtra("Account", new Gson().toJson(account));
                startActivity(graphViewCategoriesIntent);
            }
        });
    }
}
