package com.example.CS160Broque;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    Button account, graphView, settings, addExpense;
    int totalbudgetBudget = 10000;
    int billsBudget = 2500;
    int foodBudget = 2500;
    int entertainmentBudget = 2500;
    int othersBudget = 2500;
    int totalBudgetSpent = 4000;
    int billsSpent = 1000;
    int foodSpent = 1000;
    int entertainmentSpent = 1000;
    int othersSpent = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        account = (Button) findViewById(R.id.account);
        graphView = (Button) findViewById(R.id.graphView);
        settings = (Button) findViewById(R.id.settings);
        addExpense = (Button) findViewById(R.id.userFieldsAddExpense);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dateString = formatter.format(date);

        //Toast.makeText(getApplicationContext(),dateString.substring(0, 2), Toast.LENGTH_SHORT).show();

        if(dateString.substring(0, 2).equals("01")){    //reset budget every month on dashboard
            totalBudgetSpent = 0;
            billsSpent = 0;
            foodSpent = 0;
            entertainmentSpent = 0;
            othersSpent = 0;
        }

        TextView budgetHint = (TextView) findViewById(R.id.userFieldsTotalBudget);
        String budgetString = "Total Budget: " + totalBudgetSpent
                + " out of " + totalbudgetBudget + " remaining";
        budgetHint.setHint(budgetString);

        TextView billsHint = (TextView) findViewById(R.id.userFieldsBillsBudget);
        String billsString = "Bills: " + billsSpent + " out of " + billsBudget + " remaining";
        billsHint.setHint(billsString);

        TextView foodHint = (TextView) findViewById(R.id.userFieldsFoodBudget);
        String foodString = "Food: " + foodSpent + " out of " + foodBudget + " remaining";
        foodHint.setHint(foodString);

        TextView entertainmentHint = (TextView) findViewById(R.id.userFieldsEntertainmentBudget);
        String entertainmentString = "Entertainment: " + entertainmentSpent
                + " out of " + entertainmentBudget + " remaining";
        entertainmentHint.setHint(entertainmentString);

        TextView othersHint = (TextView) findViewById(R.id.userFieldsOtherBudget);
        String othersString = "Others: " + othersSpent + " out of " + othersBudget + " remaining";
        othersHint.setHint(othersString);


        account.setOnClickListener(new View.OnClickListener() { //take to account screen
            @Override
            public void onClick(View v) {
                Intent accountIntent = new Intent(Dashboard.this, AccountScreen.class);
                startActivity(accountIntent);
            }
        });

        graphView.setOnClickListener(new View.OnClickListener() {   //take to graphview screen
            @Override
            public void onClick(View v) {
                Intent graphViewIntent = new Intent(Dashboard.this, GraphViewables.class);
                startActivity(graphViewIntent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {    //take to settings screen
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(Dashboard.this, Settings.class);
                startActivity(settingsIntent);
            }
        });

        addExpense.setOnClickListener(new View.OnClickListener() {    //take to addExpense screen
            @Override
            public void onClick(View v) {
                Intent addExpenseIntent = new Intent(Dashboard.this, addExpense.class);
                startActivity(addExpenseIntent);
            }
        });
    }

}
