package com.example.CS160Broque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;

public class Settings extends AppCompatActivity {
    Button updateBudget;
    Spinner spinner;
    EditText amount;
    String budgetType;
    String jsonMyAccount;
    Account account;
    BroqueDB broqueDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        broqueDB = new BroqueDB();

        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        spinner = (Spinner) findViewById(R.id.budgetSpinner);
        updateBudget = (Button) findViewById(R.id.btn_update_budget);
        amount = (EditText) findViewById(R.id.edt_update_budget);
        String[] items = {"Select Budget Category","Food", "Bills", "Entertainment", "Other"};

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                items){
            @Override
            public boolean isEnabled(int position) {
                if (position==0){
                    return false;
                }else{
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                budgetType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView)spinner.getSelectedView()).setError("None selected");
            }
        });


        updateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Expense has been deducted", Toast.LENGTH_SHORT);
                toast.show();
                double amountNum = Double.parseDouble(amount.getText().toString().trim());

                String type = budgetType;
                String set_budget;
                String total;

                if (amount.getText().toString().trim().length()==0){
                    amount.setError("Amount is empty");
                    amount.requestFocus();
                    return;
                }
                if (type.equalsIgnoreCase("bills")) {
                    account.setBillsBudget(amountNum);
                    set_budget = String.valueOf(account.getBillsBudget());
                } else if (type.equalsIgnoreCase("food")) {
                    account.setFoodBudget(amountNum);
                    set_budget = String.valueOf(account.getFoodBudget());
                } else if (type.equalsIgnoreCase("entertainment")) {
                    account.setEntertainmentBudget(amountNum);
                    set_budget = String.valueOf(account.getEntertainmentBudget());
                } else {
                    account.setOtherBudget(amountNum);
                    set_budget = String.valueOf(account.getOtherBudget());
                }
                total = account.updatetotalBudget();

                new SettingsTask().execute(type, set_budget, account.getUserName(), total);
                Intent dashboardIntent = new Intent(Settings.this, Dashboard.class);
                dashboardIntent.putExtra("Account", new Gson().toJson(account));
                startActivity(dashboardIntent);
            }
        });




    }

    public class SettingsTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            String t;
            try {
                System.out.println("settings start");
                s = broqueDB.updateBudget(args[0], args[1], args[2]);
                t = broqueDB.updateTotalBudget(args[2], args[3]);
                System.out.println(s);
                System.out.println(t);
            } catch (
                    IOException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            } catch (
                    URISyntaxException e) {
                System.out.println("uriexception caught");
                e.printStackTrace();
            }
            return s;
        }
    }
}

