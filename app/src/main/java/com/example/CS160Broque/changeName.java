package com.example.CS160Broque;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.IOException;
import java.net.URISyntaxException;

public class changeName extends AppCompatActivity{

    EditText currUser, newUser;
    Button chgName;
    BroqueDB broqueDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changename);

        final String userNameIdentifier = getIntent().getStringExtra("userName");

        currUser = (EditText) findViewById(R.id.edt_currUsername_changename);
        newUser = (EditText) findViewById(R.id.edt_newUsername_changename);
        chgName = (Button) findViewById(R.id.btn_changeName_changename);


        chgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sCurrUser = currUser.getText().toString();
                String sNewUser = newUser.getText().toString();
                //change the name in the db, if succesful:

                if (currUser.getText().toString().trim().length()==0 && newUser.getText().toString().trim().length()==0){
                    Toast.makeText(changeName.this, "No changes made",Toast.LENGTH_SHORT).show();
                }else {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Name successfully changed", Toast.LENGTH_SHORT);
                    toast.show();
                }
                // Update to DB
                new changeName.ChangeNameTask().execute(sCurrUser, sNewUser);

                Intent backToAccount = new Intent(changeName.this, AccountScreen.class);
                backToAccount.putExtra("userName", userNameIdentifier);
                startActivity(backToAccount);
            }
        });
    }

    // AsyncTask created to perform network task
    public class ChangeNameTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("changeName start");
                s = broqueDB.changeName(args[0], args[1]);
                System.out.println(s);
                System.out.println("changeName end");

            }// doInBackground
            catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }// doInBackground
    }// ChangeNameTask

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
            Intent logOut = new Intent(changeName.this, Login.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
