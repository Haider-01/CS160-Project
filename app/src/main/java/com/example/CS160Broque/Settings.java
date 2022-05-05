package com.example.CS160Broque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    Button updateBudget;
    TextView totalBudget;
    TextView billsBudget;
    TextView foodBudget;
    TextView entertainmentBudget;
    TextView otherBudget;
    Spinner spinner;
    EditText amount;
    String budgetType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final String userNameIdentifier = getIntent().getStringExtra("userName");


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
                String expense;



                Intent dashboardIntent = new Intent(Settings.this, Dashboard.class);
                dashboardIntent.putExtra("userName", userNameIdentifier);
                startActivity(dashboardIntent);
            }
        });




    }
}

