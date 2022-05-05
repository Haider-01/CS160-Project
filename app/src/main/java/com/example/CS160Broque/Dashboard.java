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

//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//        String dateString = formatter.format(date);

//        Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

        // Can change to reset in account class
//        if(dateString.substring(0, 2).equals("01")){    //reset budget every month on dashboard
//            totalBudgetSpent = 0;
//            billsSpent = 0;
//            foodSpent = 0;
//            entertainmentSpent = 0;
//            othersSpent = 0;
//        }

        //TextView budgetHint = (TextView) findViewById(R.id.edt_total_userfields);
        String budgetString = "Total Budget: " + account.getTotalExpense()
                + " out of " + account.getTotalBudget() + " remaining";
        //budgetHint.setHint(budgetString);

        TextView billsHint = (TextView) findViewById(R.id.edt_bill_userfields);
        String billsString = "Bills: " + account.getBillsExpense()
                + " out of " + account.getBillsBudget() + " remaining";
        //billsHint.setHint(billsString);

        TextView foodHint = (TextView) findViewById(R.id.edt_food_userfields);
        String foodString = "Food: " + account.getFoodExpense()
                + " out of " + account.getFoodBudget() + " remaining";
        //foodHint.setHint(foodString);

        TextView entertainmentHint = (TextView) findViewById(R.id.edt_entertainment_userfields);
        String entertainmentString = "Entertainment: " + account.getFoodExpense()
                + " out of " + account.getFoodBudget() + " remaining";
        //entertainmentHint.setHint(entertainmentString);

        TextView othersHint = (TextView) findViewById(R.id.edt_other_userfields);
        String othersString = "Others: " + account.getOtherExpense()
                + " out of " + account.getOtherBudget() + " remaining";
        //othersHint.setHint(othersString);

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
//        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(20, "Food & Dining"));
        entries.add(new PieEntry(15, "Medical"));
        entries.add(new PieEntry(10, "Entertainment"));
        entries.add(new PieEntry(25, "Electricity and Gas"));
        entries.add(new PieEntry(30, "Housing"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }



        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        //data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);

    }

}
