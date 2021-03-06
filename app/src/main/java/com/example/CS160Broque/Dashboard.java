package com.example.CS160Broque;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        pieChart = (PieChart) findViewById(R.id.dashboard_piechart);
        double food = account.getFoodExpense();
        double bill = account.getBillsExpense();
        double entertainment = account.getEntertainmentExpense();
        double other = account.getOtherExpense();
        double total = account.getTotalBudget()-food-bill-entertainment-other;
        double[] chartdata =  {food,bill,entertainment,other,total};
        setupPieChart();
        loadPieChartData(chartdata);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dateString = formatter.format(date);
        
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

    private void loadPieChartData(double[] dataset){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) dataset[0], "Food"));
        entries.add(new PieEntry((float) dataset[1], "Bills"));
        entries.add(new PieEntry((float) dataset[2], "Entertainment"));
        entries.add(new PieEntry((float) dataset[3], "Other"));
        entries.add(new PieEntry((float) dataset[4], "Remaining"));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ColorTemplate.VORDIPLOM_COLORS[0]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[1]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[4]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[3]);
        colors.add(ColorTemplate.VORDIPLOM_COLORS[2]);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Intent logOut = new Intent(Dashboard.this, Login.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
