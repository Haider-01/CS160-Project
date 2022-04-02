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

public class UserFields extends AppCompatActivity {
    EditText totalBudget, billsBudget, foodBudget, entertainmentBudget, otherBudget;
    EditText currentBillsCost, currentFoodCost, currentEntertainmentCost, currentOthersCost;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userfields);

        totalBudget = (EditText) findViewById(R.id.userFieldsTotalBudget);
        billsBudget = (EditText) findViewById(R.id.userFieldsBillsBudget);
        foodBudget = (EditText) findViewById(R.id.userFieldsFoodBudget);
        entertainmentBudget = (EditText) findViewById(R.id.userFieldsEntertainmentBudget);
        otherBudget = (EditText) findViewById(R.id.userFieldsOtherBudget);
        currentBillsCost = (EditText) findViewById(R.id.userFieldsCurrentBillsCost);
        currentFoodCost = (EditText) findViewById(R.id.userFieldsCurrentFoodCost);
        currentEntertainmentCost = (EditText) findViewById(R.id.userFieldsCurrentEntertainmentCosts);
        currentOthersCost = (EditText) findViewById(R.id.userFieldsCurrentOthersCost);
        finish = (Button) findViewById(R.id.finishUserFields);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finishIntent = new Intent(UserFields.this, Login.class);
                startActivity(finishIntent);
            }
        });
    }
}
