package com.example.broque;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class SignUp extends AppCompatActivity{
    Button buttonLogin, buttonRegister;
    EditText editUsername, editEmail, editPassword, editConfPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.btn_login);
        buttonRegister = findViewById(R.id.btn_register);
        editUsername = findViewById(R.id.edt_usernameRegister);
        editEmail = findViewById(R.id.edt_emailRegister);
        editPassword = findViewById(R.id.edt_passwordRegister);
        editConfPassword = findViewById(R.id.edt_confPasswordRegister);


    }
}
