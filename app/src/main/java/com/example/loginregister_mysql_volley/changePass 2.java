package com.example.loginregister_mysql_volley;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class changePass extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepass);
        EditText currentPass = (EditText) findViewById(R.id.currPass);
        EditText newPass = (EditText) findViewById(R.id.newPass);
        EditText confirmPass = (EditText) findViewById(R.id.confirmPass);
        Button chgPass = (Button) findViewById(R.id.chgPassButton);

        // CODE HERE: confirm the password with db

        chgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the password in the db, if succesful:
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Password successfully changed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
