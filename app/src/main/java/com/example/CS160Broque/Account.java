package com.example.CS160Broque;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class Account extends AppCompatActivity {
    Button dashboard;
    ListView listview;
    String[] action = {"Change Password", "Change Name", "Delete Account"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountscreen);

        listview = (ListView) findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, action);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    Intent chgPassIntent = new Intent(Account.this, changePass.class);
                    startActivity(chgPassIntent);
                }
                else if (position==1){
                    Intent chgNameIntent = new Intent(Account.this, changeName.class);
                    startActivity(chgNameIntent);
                }
                else if (position==2){
                    //delete account from DB
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Account deleted", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent backToLogin = new Intent(Account.this, Login.class);
                    startActivity(backToLogin);
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
            Intent logOut = new Intent(Account.this, Login.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

