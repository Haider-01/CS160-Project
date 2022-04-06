package com.example.loginregister_mysql_volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class Account extends AppCompatActivity {
    Button dashboard;
    ListView listview;

    String[] action = {"Change Password", "Change Name", "Delete Account"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountscreen);

        listview = (ListView) findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, action);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent chgPassIntent = new Intent(Account.this, changePass.class);
                    startActivity(chgPassIntent);
                }
                else if (position==1){
                    Intent chgNameIntent = new Intent(Account.this, changeName.class);
                    startActivity(chgNameIntent);
                }
                else if (position==2){
                    //delete account from DB
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        dashboard = (Button) findViewById(R.id.backToDashboard);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dashboardIntent = new Intent(Account.this, Dashboard.class);
                startActivity(dashboardIntent);
            }
        });




    }
}

