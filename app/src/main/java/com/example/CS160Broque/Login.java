package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    ProgressDialog progressDialog;
    TextView forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edt_username_login);
        password = (EditText) findViewById(R.id.edt_password_login);
        login = (Button) findViewById(R.id.btn_login_login);
        register = (Button) findViewById(R.id.btn_register_login);
        progressDialog = new ProgressDialog(Login.this);
        forgetPass = (TextView) findViewById(R.id.tv_forgetPass_login);

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetPass = new Intent (Login.this, forgetPass.class);
                forgetPass.putExtra("userName", username.toString());
                startActivity(forgetPass);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, SignUp.class);
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
                    dashboardIntent.putExtra("userName", username.toString());
                    startActivity(dashboardIntent);
                }
            }
        });
    }
}
