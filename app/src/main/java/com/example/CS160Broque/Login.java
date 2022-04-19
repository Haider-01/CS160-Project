package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    ProgressDialog progressDialog;
    TextView forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edt_usernameLogin);
        password = (EditText) findViewById(R.id.edt_passwordLogin);
        login = (Button) findViewById(R.id.btn_loginLogin);
        register = (Button) findViewById(R.id.btn_registerLogin);
        progressDialog = new ProgressDialog(Login.this);
        forgetPass = (TextView) findViewById(R.id.forgetPass);

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPass = new Intent (Login.this, forgetPass.class);
                startActivity(forgetPass);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, MainActivity.class);
                startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = username.getText().toString().trim();
                String sPassword = password.getText().toString().trim();

                if (sUsername.isEmpty()){
                    username.setError("Username is required!");
                    username.requestFocus();
                }
                if (sPassword.isEmpty()){
                    password.setError("Password is required!");
                    password.requestFocus();
                }else {

                    // CheckLogin(sUsername, sPassword);

                    Intent dashboardIntent = new Intent(Login.this, Dashboard.class);
                    startActivity(dashboardIntent);
                }
            }
        });
    }
}
