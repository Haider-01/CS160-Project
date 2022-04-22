package com.example.broque;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.broque.R;
import com.example.broque.SignUp;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    Button account, graphView, settings, addExpense;
    EditText editTotal, editBill, editFood, editEntertainment, editOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        account = (Button) findViewById(R.id.account);
        graphView = (Button) findViewById(R.id.graphView);
        settings = (Button) findViewById(R.id.settings);
        addExpense = (Button) findViewById(R.id.userFieldsAddExpense);

        editTotal = findViewById(R.id.userFieldsTotalBudget);
        editBill = findViewById(R.id.userFieldsBillsBudget);
        editFood = findViewById(R.id.userFieldsFoodBudget);
        editEntertainment = findViewById(R.id.userFieldsEntertainmentBudget);
        editOther = findViewById(R.id.userFieldsOtherBudget);




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

        // for asynctask
        addExpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                // do the work here
                System.out.println("onCreate listener invoked");
                String total = editTotal.getText().toString();
                String bill = editBill.getText().toString();
                String food = editFood.getText().toString();
                String entertainment = editEntertainment.getText().toString();
                String other = editOther.getText().toString();
                new Dashboard.addExpenseButton().execute(total, bill, food, entertainment, other);
            }// onCLick
        });// setOnClickListener
    }// on create

    private class addExpenseButton extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... arg) {
            try{
                // check inputs
                String total = (String)arg[0];
                String bill = (String)arg[1];
                String food = (String)arg[2];
                String entertainment = (String)arg[3];
                String other = (String)arg[4];
                String username = "tim";
                String link = "https://broke-test.herokuapp.com/addExpense.php?total=%22"+total+"%22&bill=%22"+bill+"%22&food=%22"+food+"%22&entertainment=%22"+entertainment+"%22&other=%22"+other+"%22&username=%22"+username+"%22";
                System.out.println(link);
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    System.out.println("From sb" + sb);
                    break;
                }
                in.close();
                System.out.println("Expenses added");
            } catch(Exception e){
                System.out.println(e);
                System.out.println("Expenses not added");
            }
            return null;
        }//doInBackground

    }//RegisterButton

}
