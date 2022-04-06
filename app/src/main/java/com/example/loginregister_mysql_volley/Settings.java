package com.example.loginregister_mysql_volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.loginregister_mysql_volley.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

        dashboard = (Button) findViewById(R.id.dashboardFromSettings);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(Settings.this, Dashboard.class);
                startActivity(dashboardIntent);
            }
        });

        edtTotalBudget = (EditText) findViewById(R.id.settingsTotalBudget);
        edtBillsBudget = (EditText) findViewById(R.id.settingsBillsBudget);
        edtFoodBudget = (EditText) findViewById(R.id.settingsFoodBudget);
        edtEntertainmentBudget = (EditText) findViewById(R.id.settingsEntertainmentBudget);
        edtOtherBudget = (EditText) findViewById(R.id.settingsOtherBudget);

        edtTotalBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Total budget successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

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

