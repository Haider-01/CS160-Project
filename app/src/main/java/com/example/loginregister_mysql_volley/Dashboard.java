package com.example.loginregister_mysql_volley;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Dashboard extends AppCompatActivity {

    Button account, graphView, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        account = (Button) findViewById(R.id.account);
        graphView = (Button) findViewById(R.id.graphView);
        settings = (Button) findViewById(R.id.settings);

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);


        account.setOnClickListener(new View.OnClickListener() { //take to account screen
            @Override
            public void onClick(View v) {
                Intent accountIntent = new Intent(Dashboard.this, Account.class);
                startActivity(accountIntent);
            }
        });

        graphView.setOnClickListener(new View.OnClickListener() {   //take to graphview screen
            @Override
            public void onClick(View v) {
                Intent graphViewIntent = new Intent(Dashboard.this, GraphView.class);
                startActivity(graphViewIntent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {    //take to settings screen
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(Dashboard.this, Settings.class);
                startActivity(settingsIntent);
            }
        });
    }

}
