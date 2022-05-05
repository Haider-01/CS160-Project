package com.example.CS160Broque;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;

public class forgetPass extends AppCompatActivity {

    EditText username, phonenumber;
    Button sendPass;
    String jsonMyAccount;
    Account account;
    BroqueDB broqueDB = new BroqueDB();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpass);

        // Get from bundle
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyAccount = extras.getString("Account");
        }
        account = new Gson().fromJson(jsonMyAccount, Account.class);
        System.out.println(account);

        username = (EditText) findViewById(R.id.edt_username_forgetpass);
        phonenumber = (EditText) findViewById(R.id.edt_phonenumber_forgetpass);
        sendPass = (Button) findViewById(R.id.btn_sendPass_forgetpass);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        sendPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().trim().length() == 0){
                    username.setError("Username is required");
                    username.requestFocus();
                }

                if (phonenumber.getText().toString().trim().length() == 0){
                    phonenumber.setError("Email is required");
                    phonenumber.requestFocus();
                }
                new ForgetPassTask().execute(username.getText().toString(), phonenumber.getText().toString());
                Intent finishIntent = new Intent(forgetPass.this, Login.class);
                startActivity(finishIntent);
            }
        });




    }

    public class ForgetPassTask extends AsyncTask<String, String, String> {
        public String doInBackground(String... args) {
            String s = null;
            try {
                System.out.println("forget pass start");
                // TODO remove hardcoded phonenumber
                s = broqueDB.forgetPass(args[0], args[1]);
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
        }

        public void onPostExecute(String result) {
            NotificationCompat.Builder notify= new NotificationCompat.Builder(forgetPass.this,"Notification");
            notify.setContentTitle("Broque");
            notify.setContentText("Your password is: " + result);
            notify.setSmallIcon(R.drawable.abc);
            notify.setAutoCancel(true);

            NotificationManagerCompat not = NotificationManagerCompat.from(forgetPass.this);
            not.notify(1,notify.build());
        }
    }
}
