package com.example.broque;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;


public class SignUp extends AppCompatActivity{
    Button buttonLogin, buttonSignup;
    EditText editFullname, editUsername, editPassword, editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        buttonLogin = findViewById(R.id.btn_logSignup);
//        buttonSignup = findViewById(R.id.btn_createSignup);

        editFullname = findViewById(R.id.fullnameSignup);
        editUsername = findViewById(R.id.usernameSignup);
        editPassword = findViewById(R.id.passwordSignup);
        editPhone = findViewById(R.id.phonenumberSignup);

        // for asynctask
        buttonSignup = (Button) findViewById(R.id.btn_createSignup);
        this.buttonSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                // do the work here
                System.out.println("onCreate listener invoked");
                String fullname = editFullname.getText().toString();
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                String phonenumber = editPhone.getText().toString();
                new RegisterButton().execute(fullname, username, password, phonenumber);
            }// onCLick
        });// setOnClickListener
    }//onCreate

    public void register(View view){
//        System.out.println("register method invoked");
//        String fullname = editFullname.getText().toString();
//        String username = editUsername.getText().toString();
//        String password = editPassword.getText().toString();
//        String phonenumber = editPhone.getText().toString();
//
//        new RegisterButton().execute(fullname, username, password, phonenumber);
//        System.out.println("executing registerbutton");

    }//register


    public class RegisterButton extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg) {
            String s = null;
            try{
                // check inputs

                System.out.println("inside try block");
                String fullname = (String)arg[0];
                //System.out.println(fullname);
                String username = (String)arg[1];
                String password = (String)arg[2];
                String phonenumber = (String)arg[3];

                // here we might want to do a select to let user know if username already taken

                fullname = fullname.replaceAll(" ", "%20");
                username = username.replaceAll(" ", "%20");
                password = password.replaceAll(" ", "%20");

                if(phonenumber.length() != 10){
                    s = "phone number length not correct";
                }

                else if(!phonenumber.matches("[0-9]+")){
                    s = "phone number should contain only numbers";
                }

                if(password.length() <= 4) {
                    s = "password too weak, must be at least 4 characters long";
                }

                else if(!password.matches(".*[0-9].*")){
                    s = "password must contain at least one number";
                }

                else if(!password.toLowerCase().matches(".*[a-z].*")){
                    s = "password must contain at least one letter";
                }

                String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
                //String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22this%22&username=%22isfrom%22&password=%22android%22&phonenumber=%22studio%22";
                System.out.println(link);
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    System.out.println("From sb" + sb);
                    break;
                }
                in.close();
                //Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                //System.out.println("account created");
            } catch(Exception e){
                s = e.toString();
                //System.out.println("account not created");
            }
            return s;
        }//doInBackground

    }//RegisterButton

}

