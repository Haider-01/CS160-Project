package com.example.broque;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


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

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname, username, password, phonenumber;
                fullname = String.valueOf(editFullname.getText());
                username = String.valueOf(editUsername.getText());
                password = String.valueOf(editPassword.getText());
                phonenumber = String.valueOf(editPhone.getText());

                // Signup part
                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !phonenumber.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
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
                                System.out.println("account created");
//                                String link="https://broke-test.herokuapp.com/signup.php";
//                                String data  = URLEncoder.encode("username", "UTF-8") + "=" +
//                                        URLEncoder.encode(fullname, "UTF-8");
//                                data += "&" + URLEncoder.encode("username", "UTF-8") + "=" +
//                                        URLEncoder.encode(username, "UTF-8");
//                                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
//                                        URLEncoder.encode(password, "UTF-8");
//                                data += "&" + URLEncoder.encode("phonenumber", "UTF-8") + "=" +
//                                        URLEncoder.encode(phonenumber, "UTF-8");
//
//                                URL url = new URL(link);
//                                URLConnection conn = url.openConnection();
//
//                                conn.setDoOutput(true);
//                                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//
//                                wr.write( data );
//                                wr.flush();
//                                URL url = new URL(link);
//                                HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
//                                httpConn.setRequestMethod("GET");
//                                try {
//                                    InputStream in = new BufferedInputStream(httpConn.getInputStream());
//                                    readStream(in);
//                                } finally {
//                                    httpConn.disconnect();
//                                }
//                                HttpClient client = new DefaultHttpClient();
//                                HttpGet request = new HttpGet();
//                                request.setURI(new URI(link));
//                                HttpResponse response = client.execute(request);
                            } catch (Exception e) {
                                System.out.println(e);
                                System.out.println("error!");
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                    System.out.println("account not created");
                }
            }
        });

    }


}
