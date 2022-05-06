package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    ProgressDialog progressDialog;
    TextView forgetPass;
    BroqueDB broqueDB;
    Account account;

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
        broqueDB = new BroqueDB();


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
                registerIntent.putExtra("userName", username.toString());
                startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUsername = username.getText().toString();
                String sPassword = password.getText().toString();
                System.out.println(sUsername + " " + sPassword);

                if (sUsername.isEmpty()){
                    username.setError("Username is required!");
                    username.requestFocus();
                    return;
                }
                if (sPassword.isEmpty()){
                    password.setError("Password is required!");
                    password.requestFocus();
                    return;
                }
                new Login.LoginTask().execute(sUsername, sPassword);
            }
        });
    }
    public class LoginTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("login start");
                s = broqueDB.login(args[0]);
                System.out.println(s);
                if (s.equals(args[1])) {
                    Intent dashboardIntent = new Intent(Login.this, Dashboard.class);
                    String [] arrUser = broqueDB.getUser(args[0]);
                    double [] arrBudget = broqueDB.getBudget(args[0]);
                    double [] arrExpense = broqueDB.getExpense(args[0]);
                    account = new Account(arrUser[0], arrUser[1], arrUser[2], arrUser[3]);
                    account.insertBudgets(arrBudget[1], arrBudget[2], arrBudget[3], arrBudget[4]);
                    account.insertExpenses(arrExpense[1], arrExpense[2], arrExpense[3], arrExpense[4]);
                    dashboardIntent.putExtra("Account", new Gson().toJson(account));
                    startActivity(dashboardIntent);
                } else {
                  return "Incorrect password";
                }
                System.out.println("login end");
            } catch (IOException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            } catch (URISyntaxException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            }
            return s;
        }// doInBackground

        public void onPostExecute(String result) {
            if (result.equals("Incorrect password")) {
                Toast.makeText(getApplicationContext(), result,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Login successful",
                        Toast.LENGTH_SHORT).show();
            }
        }// onPostExecute
    }// LoginTask
}// Login
