package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URISyntaxException;


public class SignUp extends AppCompatActivity {

    EditText username, email, password, confPassword;
    Button login, register;
    ProgressDialog progressDialog;
    BroqueDB broqueDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.edt_username_signup);
        email = (EditText) findViewById(R.id.edt_email_signup);
        password = (EditText) findViewById(R.id.edt_password_signup);
        confPassword = (EditText) findViewById(R.id.edt_confPassword_signup);
        login = (Button) findViewById(R.id.btn_login_signup);
        register = (Button) findViewById(R.id.btn_register_signup);
        progressDialog = new ProgressDialog(SignUp.this);
        broqueDB = new BroqueDB();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignUp.this, Login.class);
                startActivity(loginIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("register clicked");
                String sUsername = username.getText().toString();
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();
                String sConfPassword = confPassword.getText().toString();
                System.out.println(sUsername + " " + sEmail + " " + sPassword + " " + sConfPassword);

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

                    Intent signUp = new Intent(SignUp.this, UserFields.class);
                    startActivity(signUp);
                } else if (!sPassword.equals(sConfPassword)) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match",
                            Toast.LENGTH_SHORT).show();
                }
                new SignUpTask().execute(sUsername, sEmail, sPassword, "1234567890");
                //Account myAccount = new Account(sUsername, sPassword, sEmail);

            }//onClick
        });//setOnClickListener
    }//onCreate

    // AsyncTask created to perform network task
    public class SignUpTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("signup start");
                // TODO remove hardcoded phonenumber
                s = broqueDB.signup(args[0], args[1], args[2], "1234567890");
                System.out.println(s);
                System.out.println("signup end");
            } catch (
                    IOException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            } catch (
                    URISyntaxException e) {
                System.out.println("uriexception caught");
                e.printStackTrace();
            }
            return s;
        }// doInBackground
    }//SignUpTask

}//SignUp
