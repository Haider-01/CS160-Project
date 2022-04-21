package com.example.CS160Broque;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class changeName extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changename);
        final EditText currUser = (EditText) findViewById(R.id.currUsername);
        final EditText newUser = (EditText) findViewById(R.id.newUsername);
        Button chgName = (Button) findViewById(R.id.chgNameButton);



        chgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the name in the db, if succesful:

                if (currUser.getText().toString().trim().length()==0 && newUser.getText().toString().trim().length()==0){
                    Toast.makeText(changeName.this, "No changes made",Toast.LENGTH_SHORT).show();
                }else {
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Name successfully changed", Toast.LENGTH_SHORT);
                    toast.show();
                }

                Intent backToAccount = new Intent(changeName.this, AccountScreen.class);
                startActivity(backToAccount);
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
            Intent logOut = new Intent(changeName.this, Login.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
