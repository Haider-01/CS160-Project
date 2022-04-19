package com.example.CS160Broque;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class forgetPass extends AppCompatActivity {

    EditText username, email;
    Button sendPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpass);

        username = (EditText) findViewById(R.id.editTextTextPersonName2);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        sendPass = (Button) findViewById(R.id.sendPassButton);



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

                if (email.getText().toString().trim().length() == 0){
                    email.setError("Email is required");
                    email.requestFocus();
                }
                NotificationCompat.Builder notify= new NotificationCompat.Builder(forgetPass.this,"Notification");

                notify.setContentTitle("Broque");
                notify.setContentText("Your password is: ThisIsMyMoney");
                notify.setSmallIcon(R.drawable.abc);
                notify.setAutoCancel(true);

                NotificationManagerCompat not = NotificationManagerCompat.from(forgetPass.this);
                not.notify(1,notify.build());

            }
        });




    }
}
