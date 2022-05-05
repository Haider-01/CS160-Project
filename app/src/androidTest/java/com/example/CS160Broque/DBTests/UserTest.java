package com.example.CS160Broque.DBTests;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

import android.content.Context;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.CS160Broque.changeName;
import com.example.CS160Broque.changePass;

import java.net.URI;
import java.net.URL;

@RunWith(AndroidJUnit4.class)
public class UserTest {

    @Test
    public void checkChangeName() throws Throwable {
        final String fullname = "Michael Johnson";
        final String username = "MichaelJohnson";
        final String password = "pass123";
        final String phonenumber = "9999999928";

        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22" + fullname + "%22&username=%22" + username + "%22&password=%22" + password + "%22&phonenumber=%22" + phonenumber + "%22";
                    URL url = new URL(link);
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(link));
                    HttpResponse response = client.execute(request);  // should not get exception from php insertion
                } catch (Exception e) {
                    fail("Exception caught when inserting account");
                }
                changeName change = new changeName();
                changeName.ChangeNameTask task = change.new ChangeNameTask();
                String s = task.doInBackground("MichaelJones", "MichaelJones1");
                assertEquals(s, "MichaelJones1");
            }
        });
    }

    @Test
    public void checkChangePassword() throws Throwable {
        final String fullname = "Michael Jordan";
        final String username = "MichaelJordan";
        final String password = "pass123";
        final String phonenumber = "9999999938";

        runOnUiThread(new Runnable() {
                          public void run() {
                              try {
                                  String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
                                  URL url = new URL(link);
                                  HttpClient client = new DefaultHttpClient();
                                  HttpGet request = new HttpGet();
                                  request.setURI(new URI(link));
                                  HttpResponse response = client.execute(request);  // should not get exception from php insertion
                              } catch (Exception e) {
                                  fail("Exception caught when inserting account");
                              }
                          }
                      });
        changePass change = new changePass();
        changePass.ChangePassTask task = change.new ChangePassTask();
        String s = task.doInBackground("pass123", "pass1234");
        assertEquals(s, "pass1234");
    }

}
