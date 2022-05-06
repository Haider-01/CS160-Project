package com.example.CS160Broque;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

public class Dashboard extends AppCompatActivity {

    Button btn_account, graphView, settings, addExpense;
    String jsonMyAccount;
    Account account;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn_account = (Button) findViewById(R.id.btn_account_dashboard);
        graphView = (Button) findViewById(R.id.btn_graph_dashboard);
        settings = (Button) findViewById(R.id.btn_settings_dashboard);
        addExpense = (Button) findViewById(R.id.btn_addExpense_dashboard);
        pieChart = (PieChart) findViewById(R.id.dashboard_piechart);
        setupPieChart();
        loadPieChartData();

        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dateString = formatter.format(date);

        Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

         //Can change to reset in account class
        if(dateString.substring(0, 2).equals("01")){    //reset budget every month on dashboard
            account.setTotalBudget(0);
            account.setBillsBudget(0);
            account.setFoodBudget(0);
            account.setEntertainmentBudget(0);
            account.setOtherBudget(0);
        }

        // AccountScreen
        // goesto forgetPass,changeName screen
        btn_account.setOnClickListener(new View.OnClickListener() { //take to account screen
            @Override
            public void onClick(View v) {
                Intent goToAccountScreen = new Intent(Dashboard.this, AccountScreen.class);
                goToAccountScreen.putExtra("Account", new Gson().toJson(account));
                startActivity(goToAccountScreen);
            }
        });

        // Goes to GraphViewables
        graphView.setOnClickListener(new View.OnClickListener() {   //take to graphview screen
            @Override
            public void onClick(View v) {
                Intent goToGraphViewables = new Intent(Dashboard.this, GraphViewables.class);
                goToGraphViewables.putExtra("Account", new Gson().toJson(account));
                startActivity(goToGraphViewables);
            }
        });

        // Goes to Settings
        settings.setOnClickListener(new View.OnClickListener() {    //take to settings screen
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(Dashboard.this, Settings.class);
                goToSettings.putExtra("Account", new Gson().toJson(account));
                startActivity(goToSettings);
            }
        });

        // Goes to addExpense
        addExpense.setOnClickListener(new View.OnClickListener() {    //take to addExpense screen
            @Override
            public void onClick(View v) {
                Intent goToAddExpense = new Intent(Dashboard.this, addExpense.class);
                goToAddExpense.putExtra("Account", new Gson().toJson(account));
                startActivity(goToAddExpense);
            }
        });
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);
//
        Legend l = pieChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(5, "Used Food"));
        entries.add(new PieEntry(25, "Remaining Food"));
        entries.add(new PieEntry(20, "Used Bills"));
        entries.add(new PieEntry(10, "Remaining Bills"));
        entries.add(new PieEntry(10, "Used Entertainment"));
        entries.add(new PieEntry(15, "Remaining Entertainment"));
        entries.add(new PieEntry(2, "Used Other"));
        entries.add(new PieEntry(13, "Remaining Other"));

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ColorTemplate.VORDIPLOM_COLORS[0]);
        colors.add(ColorTemplate.MATERIAL_COLORS[0]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[1]);
        colors.add(ColorTemplate.MATERIAL_COLORS[1]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[4]);
        colors.add(ColorTemplate.MATERIAL_COLORS[2]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[3]);
        colors.add(ColorTemplate.MATERIAL_COLORS[3]);



        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);

    }

}
