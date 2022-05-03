package com.example.CS160Broque;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        final String userNameIdentifier = getIntent().getStringExtra("userName");

        final EditText currentPass = (EditText) findViewById(R.id.edt_currPass_changepass);
        final EditText newPass = (EditText) findViewById(R.id.edt_newPass_changepass);
        final EditText confirmPass = (EditText) findViewById(R.id.edt_confirmPass_changepass);
        Button chgPass = (Button) findViewById(R.id.btn_changePass_changepass);

        // CODE HERE: confirm the password with db

        chgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPass.getText().toString().trim().length()==0){
                    currentPass.setError("Enter current password");
                    currentPass.requestFocus();
                }
                else if (newPass.getText().toString().trim().length()==0 && confirmPass.getText().toString().trim().length()==0) {
                    Toast.makeText(changePass.this, "No changes made", Toast.LENGTH_SHORT).show();
                    Intent backToAccount = new Intent(changePass.this, AccountScreen.class);
                    backToAccount.putExtra("userName", userNameIdentifier);
                    startActivity(backToAccount);
                }else if (newPass.getText().toString().trim().length()==0 && confirmPass.getText().toString().trim().length()!=0){
                    newPass.setError("Enter the new password!");
                    newPass.requestFocus();
                }
                else if (confirmPass.getText().toString().trim().length()==0 && newPass.getText().toString().trim().length()!=0){
                    confirmPass.setError("Confirm the password!");
                    confirmPass.requestFocus();
                }else if (newPass.getText().toString().equals(confirmPass.getText().toString())){
                    //change the password in the db, if succesful:
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Password successfully changed", Toast.LENGTH_SHORT);
                    toast.show();


                    Intent backToAccount = new Intent(changePass.this, AccountScreen.class);
                    backToAccount.putExtra("userName", userNameIdentifier);
                    startActivity(backToAccount);
                }else{
                    Toast.makeText(changePass.this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Intent logOut = new Intent(changePass.this, Login.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
