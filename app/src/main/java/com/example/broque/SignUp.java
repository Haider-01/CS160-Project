package com.example.broque;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appconpat.app.AppConpatActivity;


public class Signup {
  Button buttonLogin, buttonRegister;
  EditText editUsername, editEmail, editPassword, editConfPassword;
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    buttonLogin = findViewByID(R.id.btn_login);
    buttonRegister = findViewById(R.id.btn_register);
    editUsername = findViewById(R.id.edt_usernameRegister);
    editEmail = findViewById(R.id.edt_emailRegister);
    editPassword = findViewByID(R.id.edt_passwordRegister);
    editConfPassword = findViewById(R.id.edt_confPasswordRegister);
  }
}
