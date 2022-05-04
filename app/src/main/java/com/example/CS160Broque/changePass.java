package com.example.CS160Broque;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class changePass extends AppCompatActivity {

    BroqueDB broqueDB = new BroqueDB();
    String jsonMyAccount;
    Account account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepass);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        final EditText currentPass = (EditText) findViewById(R.id.edt_currPass_changepass);
        final EditText newPass = (EditText) findViewById(R.id.edt_newPass_changepass);
        final EditText confirmPass = (EditText) findViewById(R.id.edt_confirmPass_changepass);
        Button chgPass = (Button) findViewById(R.id.btn_changePass_changepass);

        chgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPass.getText().toString().trim().length() == 0) {
                    currentPass.setError("Enter current password");
                    currentPass.requestFocus();
                } else if (newPass.getText().toString().trim().length() == 0 && confirmPass.getText().toString().trim().length() == 0) {
                    Toast.makeText(changePass.this, "No changes made", Toast.LENGTH_SHORT).show();
                    Intent backToAccount = new Intent(changePass.this, AccountScreen.class);
                    backToAccount.putExtra("Account", new Gson().toJson(account));
                    startActivity(backToAccount);
                } else if (newPass.getText().toString().trim().length() == 0 && confirmPass.getText().toString().trim().length() != 0) {
                    newPass.setError("Enter the new password!");
                    newPass.requestFocus();
                } else if (confirmPass.getText().toString().trim().length() == 0 && newPass.getText().toString().trim().length() != 0) {
                    confirmPass.setError("Confirm the password!");
                    confirmPass.requestFocus();
                } else if (newPass.getText().toString().equals(confirmPass.getText().toString())) {
                    //change the password in the db, if succesful:
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Password successfully changed", Toast.LENGTH_SHORT);
                    toast.show();


                    Intent backToAccount = new Intent(changePass.this, AccountScreen.class);
                    account.setPassword(newPass.getText().toString());
                    backToAccount.putExtra("Account", new Gson().toJson(account));
                    startActivity(backToAccount);
                } else {
                    Toast.makeText(changePass.this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
                }
                new ChangePassTask().execute(account.getUserName(), newPass.getText().toString());
            }
        });

    }

    // AsyncTask created to perform network task
    public class ChangePassTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("changePass start");
                s = broqueDB.changePass(args[0], args[1]);
                System.out.println(s);
                System.out.println("changePass end");

            }// doInBackground
            catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }// doInBackground
    }// ChangePassTask

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
