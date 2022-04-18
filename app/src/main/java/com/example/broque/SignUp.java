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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;
import java.net.URL;


public class SignUp extends AppCompatActivity{
    Button buttonLogin, buttonSignup;
    EditText editFullname, editUsername, editPhone, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.btn_login);
        buttonSignup = findViewById(R.id.btn_register);
        editFullname = findViewById(R.id.edt_fullnameRegister);
        editUsername = findViewById(R.id.edt_usernameRegister);
        editPassword = findViewById(R.id.edt_passwordRegister);
        editPhone = findViewById(R.id.edt_phoneRegister);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname, username, password, phonenumber;
                fullname = String.valueOf(editFullname.getText());
                username = String.valueOf(editUsername.getText());
                password = String.valueOf(editPassword.getText());
                phonenumber = String.valueOf(editPhone.getText());

                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !phonenumber.equals("") && phonenumber.length() == 10) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String link = "http://myphpmysqlweb.hostei.com/login.php?fullname="+fullname+"& username="+username+"& password="+password+"& phonenumber="+phonenumber;
                                URL url = new URL(link);
                                HttpClient client = new DefaultHttpClient();
                                HttpPost request = new HttpPost();
                                request.setURI(new URI(link));
                                HttpResponse response = client.execute(request);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
