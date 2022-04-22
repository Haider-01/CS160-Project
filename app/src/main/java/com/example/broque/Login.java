package com.example.broque;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class Login extends AppCompatActivity {
    Button buttonLogin, buttonSignup;
    EditText editUser, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.btn_login);
        buttonSignup = findViewById(R.id.btn_register);
        editUser = findViewById(R.id.edt_usernameLogin);
        editPass = findViewById(R.id.edt_passwordLogin);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }
        });

        buttonLogin = (Button) findViewById(R.id.btn_login);
        this.buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                // do the work here
                System.out.println("onCreate listener invoked");
                String username = editUser.getText().toString();
                String password = editPass.getText().toString();
                new Login.loginButton().execute(username, password);
            }// onCLick
        });// setOnClickListener
    }

    private class loginButton extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg) {
            try{
                // check inputs

                System.out.println("inside try block");
                String username = (String)arg[0];
                String password = (String)arg[1];
                String link="https://broke-test.herokuapp.com/login.php";
                String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");
                System.out.println(link);


                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();
                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                reader.close();
                return sb.toString();
//                System.out.println("login success");
//                System.out.println("Hello " + sb);
            } catch(Exception e){
                System.out.println(e);
                System.out.println("login failed");
            }
            return null;
        }//doInBackground

        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);
            if (s.equals(editUser.getText().toString())) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }//RegisterButton

}
