package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    Button dashboard;
    TextView totalBudget;
    TextView billsBudget;
    TextView foodBudget;
    TextView entertainmentBudget;
    TextView otherBudget;
    EditText edtTotalBudget, edtBillsBudget, edtFoodBudget, edtEntertainmentBudget, edtOtherBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final String userNameIdentifier = getIntent().getStringExtra("userName");

        dashboard = (Button) findViewById(R.id.btn_finish_settings);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(Settings.this, Dashboard.class);
                dashboardIntent.putExtra("userName", userNameIdentifier);
                startActivity(dashboardIntent);
            }
        });

        edtBillsBudget = (EditText) findViewById(R.id.edt_bill_settings);
        edtFoodBudget = (EditText) findViewById(R.id.edt_food_settings);
        edtEntertainmentBudget = (EditText) findViewById(R.id.edt_entertainment_settings);
        edtOtherBudget = (EditText) findViewById(R.id.edt_other_settings);

        edtBillsBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Bills budget successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        edtFoodBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Food budget successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        edtEntertainmentBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Entertainment budget successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        edtOtherBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Other budget successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });




    }
}

