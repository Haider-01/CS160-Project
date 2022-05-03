package com.example.CS160Broque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.IOException;
import java.net.URISyntaxException;


public class UserFields extends AppCompatActivity {
    EditText totalBudget, billsBudget, foodBudget, entertainmentBudget, otherBudget, monthlyIncome;
    Spinner spinner;
    Button finish;
    BroqueDB broqueDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfields);

//        spinner = (Spinner) findViewById(R.id.spinner);
        monthlyIncome = (EditText) findViewById(R.id.edt_monthlyincome_userfields);
        totalBudget = (EditText) findViewById(R.id.edt_total_userfields);
        billsBudget = (EditText) findViewById(R.id.edt_bill_userfields);
        foodBudget = (EditText) findViewById(R.id.edt_food_userfields);
        entertainmentBudget = (EditText) findViewById(R.id.edt_entertainment_userfields);
        otherBudget = (EditText) findViewById(R.id.edt_other_userfields);
        finish = (Button) findViewById(R.id.btn_finish_userfields);
        broqueDB = new BroqueDB();
        Bundle extras = getIntent().getExtras();
        final String user = extras.getString("Username");

//        monthlyIncome.setEnabled(true);
//        totalBudget.setEnabled(false);
//        billsBudget.setEnabled(false);
//        foodBudget.setEnabled(false);
//        entertainmentBudget.setEnabled(false);
//        otherBudget.setEnabled(false);
//        String[] options = {"Select Option","Default", "Manual Input"};
//
//        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options){
//            @Override
//            public boolean isEnabled(int position) {
//                if (position==0){
//                    return false;
//                }else{
//                    return true;
//                }
//            }
//
//            @Override
//            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if(position == 0){
//                    tv.setTextColor(Color.GRAY);
//                }
//                else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        });
//
//
//
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 1){
//                    if (monthlyIncome.getText().toString().trim().length() == 0){
//                        monthlyIncome.setError("Enter your income!");
//                        monthlyIncome.requestFocus();
//                    }
//                    else{
//                        double income = Double.parseDouble(monthlyIncome.getText().toString().trim());
//                        double totalBgt = income*0.75;
//                        double bill = income*0.3;
//                        double food = income*0.2;
//                        double entertainment = income*0.15;
//                        double other = income*0.10;
//
//                        totalBudget.setText(Double.toString(totalBgt));
//                        billsBudget.setText(Double.toString(bill));
//                        foodBudget.setText(Double.toString(food));
//                        entertainmentBudget.setText(Double.toString(entertainment));
//                        otherBudget.setText(Double.toString(other));
//                        System.out.println(monthlyIncome + " " + totalBudget + " " + billsBudget + " " + foodBudget + " " + entertainmentBudget + " " + otherBudget);
//                    }
//
//                } else if (position==2){
//                    monthlyIncome.setEnabled(false);
//                    totalBudget.setEnabled(true);
//                    billsBudget.setEnabled(true);
//                    foodBudget.setEnabled(true);
//                    entertainmentBudget.setEnabled(true);
//                    otherBudget.setEnabled(true);
//                    if (totalBudget.getText().toString().trim().length() == 0){
//                        totalBudget.setError("Enter your total budget!");
//                        totalBudget.requestFocus();
//                    }
//                    if (billsBudget.getText().toString().trim().length() == 0){
//                        billsBudget.setError("Enter your bills budget!");
//                        billsBudget.requestFocus();
//                    }
//                    if (foodBudget.getText().toString().trim().length() == 0){
//                        foodBudget.setError("Enter your food budget!");
//                        foodBudget.requestFocus();
//                    }
//                    if (entertainmentBudget.getText().toString().trim().length() == 0){
//                        entertainmentBudget.setError("Enter your entertainment budget!");
//                        entertainmentBudget.requestFocus();
//                    }
//                    if (otherBudget.getText().toString().trim().length() == 0){
//                        otherBudget.setError("Enter your other budget!");
//                        otherBudget.requestFocus();
//                    }
//                    System.out.println(monthlyIncome + " " + totalBudget + " " + billsBudget + " " + foodBudget + " " + entertainmentBudget + " " + otherBudget);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set budget in the database, if user doesn't enter any value for any budget category, set budget to 0

                String mIncome = monthlyIncome.getText().toString();
                String tBudget = totalBudget.getText().toString();
                String bBudget = billsBudget.getText().toString();
                String fBudget = foodBudget.getText().toString();
                String eBudget = entertainmentBudget.getText().toString();
                String oBudget = otherBudget.getText().toString();
                System.out.println(mIncome + " " + tBudget + " " + bBudget + " " + fBudget + " " + eBudget + " " + oBudget);

                if (mIncome.isEmpty()){
                    monthlyIncome.setError("monthly income is empty");
                    monthlyIncome.requestFocus();
                    return;
                }
                if (tBudget.isEmpty()){
                    totalBudget.setError("total is empty");
                    totalBudget.requestFocus();
                    return;
                }
                if (bBudget.isEmpty()){
                    billsBudget.setError("bills is empty");
                    billsBudget.requestFocus();
                    return;
                }
                if (fBudget.isEmpty()){
                    foodBudget.setError("food is empty");
                    foodBudget.requestFocus();
                    return;
                }
                new UserFieldsTask().execute(user, tBudget, bBudget, fBudget, eBudget, oBudget);
//                Intent finishIntent = new Intent(UserFields.this, Dashboard.class);
//                finishIntent.putExtra("Username", user);
//                startActivity(finishIntent);
            }
        });
    }

    public class UserFieldsTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("Userfields start");
                // TODO remove hardcoded phonenumber
                s = broqueDB.insertBudget(args[0], args[1], args[2], args[3], args[4], args[5]);
                System.out.println(s);
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
        }// doInBackground
    }//SignUpTask


}