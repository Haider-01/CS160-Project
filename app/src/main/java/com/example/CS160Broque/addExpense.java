package com.example.CS160Broque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class addExpense extends AppCompatActivity {

    Button addExpense;
    Spinner spinner;
    EditText amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense);

        spinner = (Spinner) findViewById(R.id.spinner);
        addExpense = (Button) findViewById(R.id.addExpense);
        amount = (EditText) findViewById(R.id.editTextNumber2);
        String[] items = {"Select Budget Category","Food", "Bills", "Entertainment", "Other"};


        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                items){
            @Override
            public boolean isEnabled(int position) {
                if (position==0){
                    return false;
                }else{
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        });



        final String choice = spinner.getSelectedItem().toString();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Expense has been deducted", Toast.LENGTH_SHORT);
                toast.show();

                double amountNum = Double.parseDouble(amount.getText().toString().trim());
                if (amountNum>0){

                    NotificationCompat.Builder notify= new NotificationCompat.Builder(addExpense.this,"Notification");

                    notify.setContentTitle("Broque");
                    notify.setContentText("Your budget for "+ choice +" has exceeded 75%");
                    notify.setSmallIcon(R.drawable.abc);
                    notify.setAutoCancel(true);

                    NotificationManagerCompat not = NotificationManagerCompat.from(addExpense.this);
                    not.notify(1,notify.build());
                }

                Intent backToDashboard = new Intent(addExpense.this, Dashboard.class);
                startActivity(backToDashboard);
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
            Intent logOut = new Intent(addExpense.this, Login.class);
            logOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

