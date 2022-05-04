package com.example.CS160Broque;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class Dashboard extends AppCompatActivity {

    Button btn_account, graphView, settings, addExpense;
    String jsonMyAccount;
    String user;
    Account account;

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
            user = extras.getString("Username");
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

        TextView budgetHint = (TextView) findViewById(R.id.edt_total_userfields);
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
                Intent goToAccountScreen = new Intent(Dashboard.this, Account.class);
                goToAccountScreen.putExtra("Username", user);
                goToAccountScreen.putExtra("Account", new Gson().toJson(account));
                startActivity(goToAccountScreen);
            }
        });

        // Goes to GraphViewables
        graphView.setOnClickListener(new View.OnClickListener() {   //take to graphview screen
            @Override
            public void onClick(View v) {
                Intent goToGraphViewables = new Intent(Dashboard.this, GraphViewables.class);
                goToGraphViewables.putExtra("Username", user);
                goToGraphViewables.putExtra("Account", new Gson().toJson(account));
                startActivity(goToGraphViewables);
            }
        });

        // Goes to Settings
        settings.setOnClickListener(new View.OnClickListener() {    //take to settings screen
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(Dashboard.this, Settings.class);
                goToSettings.putExtra("Username", user);
                goToSettings.putExtra("Account", new Gson().toJson(account));
                startActivity(goToSettings);
            }
        });

        // Goes to addExpense
        addExpense.setOnClickListener(new View.OnClickListener() {    //take to addExpense screen
            @Override
            public void onClick(View v) {
                Intent goToAddExpense = new Intent(Dashboard.this, addExpense.class);
                goToAddExpense.putExtra("Username", user);
                goToAddExpense.putExtra("Account", new Gson().toJson(account));
                startActivity(goToAddExpense);
            }
        });
    }

}
