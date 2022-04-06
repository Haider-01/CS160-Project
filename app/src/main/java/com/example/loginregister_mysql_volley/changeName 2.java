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

public class changeName extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changename);
        EditText firstname = (EditText) findViewById(R.id.firstName);
        EditText middlename = (EditText) findViewById(R.id.middleName);
        EditText lastname = (EditText) findViewById(R.id.lastName);
        Button chgName = (Button) findViewById(R.id.chgNameButton);

        

        chgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the name in the db, if succesful:
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Name successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
