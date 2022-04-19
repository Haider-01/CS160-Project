//package com.example.loginregister_mysql_volley;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;
//
//public class Dashboard extends AppCompatActivity {
//
//    Button account, graphView, settings, addExpense;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//
//        account = (Button) findViewById(R.id.account);
//        graphView = (Button) findViewById(R.id.graphView);
//        settings = (Button) findViewById(R.id.settings);
//        addExpense = (Button) findViewById(R.id.userFieldsAddExpense);
//
//        TextView budgetHint = (TextView) findViewById(R.id.userFieldsTotalBudget);
//        int totalBudget = 10000;
//        int totalBudgetSpent = 4000;
//        String budgetString = "Total Budget: " + totalBudgetSpent
//                + " out of " + totalBudget + " remaining";
//        budgetHint.setHint(budgetString);
//
//        TextView billsHint = (TextView) findViewById(R.id.userFieldsBillsBudget);
//        int bills = 2500;
//        int billsSpent = 1000;
//        String billsString = "Bills: " + billsSpent + " out of " + bills + " remaining";
//        billsHint.setHint(billsString);
//
//        TextView foodHint = (TextView) findViewById(R.id.userFieldsFoodBudget);
//        int food = 2500;
//        int foodSpent = 1000;
//        String foodString = "Food: " + foodSpent + " out of " + food + " remaining";
//        foodHint.setHint(foodString);
//
//        TextView entertainmentHint = (TextView) findViewById(R.id.userFieldsEntertainmentBudget);
//        int entertainment = 2500;
//        int entertainmentSpent = 1000;
//        String entertainmentString = "Entertainment: " + entertainmentSpent
//                + " out of " + entertainment + " remaining";
//        entertainmentHint.setHint(entertainmentString);
//
//        TextView othersHint = (TextView) findViewById(R.id.userFieldsOtherBudget);
//        int others = 2500;
//        int othersSpent = 1000;
//        String othersString = "Others: " + othersSpent + " out of " + others + " remaining";
//        othersHint.setHint(othersString);
//
//
//        account.setOnClickListener(new View.OnClickListener() { //take to account screen
//            @Override
//            public void onClick(View v) {
//                Intent accountIntent = new Intent(Dashboard.this, Account.class);
//                startActivity(accountIntent);
//            }
//        });
//
//        graphView.setOnClickListener(new View.OnClickListener() {   //take to graphview screen
//            @Override
//            public void onClick(View v) {
//                Intent graphViewIntent = new Intent(Dashboard.this, GraphView.class);
//                startActivity(graphViewIntent);
//            }
//        });
//
//        settings.setOnClickListener(new View.OnClickListener() {    //take to settings screen
//            @Override
//            public void onClick(View v) {
//                Intent settingsIntent = new Intent(Dashboard.this, Settings.class);
//                startActivity(settingsIntent);
//            }
//        });
//
//        addExpense.setOnClickListener(new View.OnClickListener() {    //take to addExpense screen
//            @Override
//            public void onClick(View v) {
//                Intent addExpenseIntent = new Intent(Dashboard.this, addExpense.class);
//                startActivity(addExpenseIntent);
//            }
//        });
//    }
//
//}
