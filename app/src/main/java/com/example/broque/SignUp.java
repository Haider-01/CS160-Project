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


public class SignUp extends AppCompatActivity{
    Button buttonLogin, buttonSignup;
    EditText editFullname, editUsername, editPhone, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.btn_login);
        buttonSignup = findViewById(R.id.btn_register);
        editFullname = findViewById(R.id.edt_fullname);
        editUsername = findViewById(R.id.edt_username);
        editPassword = findViewById(R.id.edt_password);
        editPhone = findViewById(R.id.edt_phonenumber);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname, username, password, phonenumber;
                fullname = String.valueOf(editFullname.getText());
                username = String.valueOf(editUsername.getText());
                password = String.valueOf(editPassword.getText());
                phonenumber = String.valueOf(editPhone.getText());

                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !phonenumber.equals("") && phonenumber.length() == 10) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "name";
                            field[1] = "phonenumber";
                            field[2] = "username";
                            field[3] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = phonenumber;
                            data[2] = username;
                            data[3] = password;
                            PutData putData = new PutData("http://192.168.86.37/broquedb/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "All fields required", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
