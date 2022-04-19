package com.example.CS160Broque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class UserFields extends AppCompatActivity {
    EditText totalBudget, billsBudget, foodBudget, entertainmentBudget, otherBudget,monthlyIncome;
    Spinner spinner;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfields);

        spinner = (Spinner) findViewById(R.id.spinner);
        monthlyIncome = (EditText) findViewById(R.id.monthlyIncome);
        totalBudget = (EditText) findViewById(R.id.userFieldsTotalBudget);
        billsBudget = (EditText) findViewById(R.id.userFieldsBillsBudget);
        foodBudget = (EditText) findViewById(R.id.userFieldsFoodBudget);
        entertainmentBudget = (EditText) findViewById(R.id.userFieldsEntertainmentBudget);
        otherBudget = (EditText) findViewById(R.id.userFieldsOtherBudget);
        finish = (Button) findViewById(R.id.finishUserFields);

        monthlyIncome.setEnabled(true);
        totalBudget.setEnabled(false);
        billsBudget.setEnabled(false);
        foodBudget.setEnabled(false);
        entertainmentBudget.setEnabled(false);
        otherBudget.setEnabled(false);
        String[] options = {"Select Option","Default", "Manual Input"};

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options){
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
                if (position == 1){
                    if (monthlyIncome.getText().toString().trim().length() == 0){
                        monthlyIncome.setError("Enter your income!");
                        monthlyIncome.requestFocus();
                    }
                    else{
                        double income = Double.parseDouble(monthlyIncome.getText().toString().trim());
                        double totalBgt = income*0.75;
                        double bill = income*0.3;
                        double food = income*0.2;
                        double entertainment = income*0.15;
                        double other = income*0.10;

                        totalBudget.setText(Double.toString(totalBgt));
                        billsBudget.setText(Double.toString(bill));
                        foodBudget.setText(Double.toString(food));
                        entertainmentBudget.setText(Double.toString(entertainment));
                        otherBudget.setText(Double.toString(other));
                    }

                }else if (position==2){
                    monthlyIncome.setEnabled(false);
                    totalBudget.setEnabled(true);
                    billsBudget.setEnabled(true);
                    foodBudget.setEnabled(true);
                    entertainmentBudget.setEnabled(true);
                    otherBudget.setEnabled(true);
                    if (totalBudget.getText().toString().trim().length() == 0){
                        totalBudget.setError("Enter your total budget!");
                        totalBudget.requestFocus();
                    }
                    if (billsBudget.getText().toString().trim().length() == 0){
                        billsBudget.setError("Enter your bills budget!");
                        billsBudget.requestFocus();
                    }
                    if (foodBudget.getText().toString().trim().length() == 0){
                        foodBudget.setError("Enter your food budget!");
                        foodBudget.requestFocus();
                    }
                    if (entertainmentBudget.getText().toString().trim().length() == 0){
                        entertainmentBudget.setError("Enter your entertainment budget!");
                        entertainmentBudget.requestFocus();
                    }
                    if (otherBudget.getText().toString().trim().length() == 0){
                        otherBudget.setError("Enter your other budget!");
                        otherBudget.requestFocus();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set budget in the database, if user doesn't enter any value for any budget category, set budget to 0
                Intent finishIntent = new Intent(UserFields.this, Login.class);
                startActivity(finishIntent);
            }
        });
    }
}