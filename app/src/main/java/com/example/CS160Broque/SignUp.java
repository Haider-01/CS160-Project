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

    EditText username, fullname, password, confPassword, phonenumber;
    Button login, register;
    ProgressDialog progressDialog;
    BroqueDB broqueDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.edt_username_signup);
        fullname = (EditText) findViewById(R.id.edt_fullname_signup);
        password = (EditText) findViewById(R.id.edt_password_signup);
        confPassword = (EditText) findViewById(R.id.edt_confPassword_signup);
        phonenumber = (EditText) findViewById(R.id.edt_phoneNumber_signup);
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
                String sFullname = fullname.getText().toString();
                String sPassword = password.getText().toString();
                String sConfPassword = confPassword.getText().toString();
                String sPhonenumber = phonenumber.getText().toString();
                System.out.println(sUsername + " " + sFullname + " " + sPassword + " " + sPhonenumber);

                if (sUsername.isEmpty()){
                    username.setError("Username is empty");
                    username.requestFocus();
                    return;
                }
                if (sFullname.isEmpty()){
                    fullname.setError("Email is empty");
                    fullname.requestFocus();
                    return;
                }
                if (sPassword.isEmpty()){
                    password.setError("Password is empty");
                    password.requestFocus();
                    return;
                }
                if (sConfPassword.isEmpty()){
                    confPassword.setError("Confirm the password!");
                    confPassword.requestFocus();
                    return;
                }
                if (sPhonenumber.length() != 10){
                    phonenumber.setError("Phone number must be 10 digits");
                    phonenumber.requestFocus();
                    return;
                }

                if (sPassword.equals(sConfPassword) && !sPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Account created",
                            Toast.LENGTH_SHORT).show();

                    Intent signUp = new Intent(SignUp.this, UserFields.class);
                    signUp.putExtra("userName", username.toString());
                    startActivity(signUp);
                } else if (!sPassword.equals(sConfPassword)) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                new SignUpTask().execute(sUsername, sFullname, sPassword, sPhonenumber);
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
                s = broqueDB.signup(args[0], args[1], args[2], args[3]);
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
