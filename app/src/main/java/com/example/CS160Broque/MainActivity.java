package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText username, email, password, confPassword;
    Button login, register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.edt_usernameRegister);
        email = (EditText) findViewById(R.id.edt_emailRegister);
        password = (EditText) findViewById(R.id.edt_passwordRegister);
        confPassword = (EditText) findViewById(R.id.edt_confPasswordRegister);
        login = (Button) findViewById(R.id.btn_loginRegister);
        register = (Button) findViewById(R.id.btn_registerRegister);
        progressDialog = new ProgressDialog(MainActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                startActivity(loginIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = username.getText().toString();
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();
                String sConfPassword = confPassword.getText().toString();

                if (sUsername.isEmpty()){
                    username.setError("Username is empty");
                    username.requestFocus();
                }
                if (sEmail.isEmpty()){
                    email.setError("Email is empty");
                    email.requestFocus();
                }
                if (sPassword.isEmpty()){
                    password.setError("Password is empty");
                    password.requestFocus();
                }
                if (sConfPassword.isEmpty()){
                    confPassword.setError("Confirm the password!");
                    confPassword.requestFocus();
                }

                if (sPassword.equals(sConfPassword) && !sPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Account created",
                            Toast.LENGTH_SHORT).show();

                    Intent signUp = new Intent(MainActivity.this, UserFields.class);
                    startActivity(signUp);
                } else if (!sPassword.equals(sConfPassword)) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match",
                            Toast.LENGTH_SHORT).show();
                }

                //Account myAccount = new Account(sUsername, sPassword, sEmail);
            }
        });
    }
}
