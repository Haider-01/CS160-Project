package com.example.broque;

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

        buttonLogin = findViewById(R.id.btn_logSignup);
        buttonSignup = findViewById(R.id.btn_createSignup);

        editFullname = findViewById(R.id.fullnameSignup);
        editUsername = findViewById(R.id.usernameSignup);
        editPassword = findViewById(R.id.passwordSignup);
        editPhone = findViewById(R.id.phonenumberSignup);
    }//onCreate

    public void register(View view){
        String fullname = editFullname.getText().toString();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String phonenumber = editPhone.getText().toString();

        new RegisterButton().execute(fullname, username, password, phonenumber);

    }//register

    private class RegisterButton extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... arg) {
            try{
                String fullname = (String)arg[0];
                String username = (String)arg[1];
                String password = (String)arg[2];
                String phonenumber = (String)arg[3];
                String link = "https://broke-test.herokuapp.com/signup.php?fullname="+fullname+"&username="+username+"&password="+password+"&phonenumber="+phonenumber;
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
                    break;
                }

                in.close();
                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            } catch(Exception e){
                System.out.println(e);            }
            return null;
        }//doInBackground
    }//RegisterButton



}


