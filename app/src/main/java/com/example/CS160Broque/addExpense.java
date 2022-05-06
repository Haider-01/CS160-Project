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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;

public class addExpense extends AppCompatActivity {

    Button addExpense;
    Spinner spinner;
    EditText amount;
    String budgetType;
    String jsonMyAccount;
    Account account;
    BroqueDB broqueDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense);
        broqueDB = new BroqueDB();

        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        spinner = (Spinner) findViewById(R.id.spinner);
        addExpense = (Button) findViewById(R.id.btn_addExpense_expense);
        amount = (EditText) findViewById(R.id.edt_expense_expense);
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
        
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                budgetType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView)spinner.getSelectedView()).setError("None selected");
            }
        });


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

                String type = budgetType;
                String expense;
                String total;

                if (amount.getText().toString().trim().length()==0){
                    amount.setError("Amount is empty");
                    amount.requestFocus();
                    return;
                }
                if (type.equalsIgnoreCase("bills")) {
                    expense = String.valueOf(account.addBillsExpense(amountNum));

                    // Send Notification if expenses has exceeded 75% of the budget
                    if (account.getBillsExpense() > (0.75*account.getBillsBudget())){
                        NotificationCompat.Builder notify= new NotificationCompat.Builder(addExpense.this,"Notification");
                        notify.setContentTitle("Broque");
                        notify.setContentText("Your expenses for Bills category has exceeded 75%");
                        notify.setSmallIcon(R.drawable.abc);
                        notify.setAutoCancel(true);
                        NotificationManagerCompat not = NotificationManagerCompat.from(addExpense.this);
                        not.notify(1,notify.build());
                    }
                } else if (type.equalsIgnoreCase("food")) {
                    expense = String.valueOf(account.addFoodExpense(amountNum));
                    if (account.getFoodExpense() > (0.75*account.getFoodBudget())){
                        NotificationCompat.Builder notify= new NotificationCompat.Builder(addExpense.this,"Notification");
                        notify.setContentTitle("Broque");
                        notify.setContentText("Your expenses for Food category has exceeded 75%");
                        notify.setSmallIcon(R.drawable.abc);
                        notify.setAutoCancel(true);
                        NotificationManagerCompat not = NotificationManagerCompat.from(addExpense.this);
                        not.notify(1,notify.build());
                    }
                } else if (type.equalsIgnoreCase("entertainment")) {
                    expense = String.valueOf(account.addEntertainmentExpense(amountNum));
                    if (account.getEntertainmentExpense() > (0.75*account.getEntertainmentBudget())){
                        NotificationCompat.Builder notify= new NotificationCompat.Builder(addExpense.this,"Notification");
                        notify.setContentTitle("Broque");
                        notify.setContentText("Your expenses for Entertainment category has exceeded 75%");
                        notify.setSmallIcon(R.drawable.abc);
                        notify.setAutoCancel(true);
                        NotificationManagerCompat not = NotificationManagerCompat.from(addExpense.this);
                        not.notify(1,notify.build());
                    }
                } else {
                    expense = String.valueOf(account.addOtherExpense(amountNum));
                    if (account.getOtherExpense() > (0.75*account.getOtherBudget())){
                        NotificationCompat.Builder notify= new NotificationCompat.Builder(addExpense.this,"Notification");
                        notify.setContentTitle("Broque");
                        notify.setContentText("Your expenses for Other category has exceeded 75%");
                        notify.setSmallIcon(R.drawable.abc);
                        notify.setAutoCancel(true);
                        NotificationManagerCompat not = NotificationManagerCompat.from(addExpense.this);
                        not.notify(1,notify.build());
                    }
                }
                total = account.updateTotalExpense();
                new ExpenseTask().execute(account.getUserName(), type, expense, total);

                //new TransactionTask().execute(account.getUserName(), )
                Intent backToDashboard = new Intent(addExpense.this, Dashboard.class);
                backToDashboard.putExtra("Account", new Gson().toJson(account));
                startActivity(backToDashboard);
            }
        });



    }

    // AsyncTask created to perform network task
    public class ExpenseTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            String t;
            try {
                System.out.println("expense start");
                // TODO remove hardcoded phonenumber
                s = broqueDB.updateExpense(args[0], args[1], args[2]);
                t = broqueDB.updateTotalExpense(args[0], args[3]);
                System.out.println(s);
            } catch (
                    IOException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            } catch (
                    URISyntaxException e) {
                System.out.println("uriexception caught");
                e.printStackTrace();
            }
            return s;
        }// doInBackground
    }//SignUpTask

    public class TransactionTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            String t;
            try {
                System.out.println("expense start");
                // TODO remove hardcoded phonenumber
                s = broqueDB.updateExpense(args[0], args[1], args[2]);
                t = broqueDB.updateTotalExpense(args[0], args[3]);
                System.out.println(s);
            } catch (
                    IOException e) {
                System.out.println("ioexception caught");
                e.printStackTrace();
            } catch (
                    URISyntaxException e) {
                System.out.println("uriexception caught");
                e.printStackTrace();
            }
            return s;
        }// doInBackground
    }//SignUpTask
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

