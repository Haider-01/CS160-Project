package com.example.CS160Broque.DBTests;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.CS160Broque.SignUp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;
import java.net.URL;

@RunWith(AndroidJUnit4.class)
public class SignUpTests {

    @Test
    public void testMissingPassword() throws Throwable {
        final String fullname = "joe bob";
        final String username = "joebob23";
        final String phonenumber = "1234567999";
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SignUp signup = new SignUp();
                    SignUp.SignUpTask button = signup.new SignUpTask();
                    String result = button.doInBackground(fullname, username, phonenumber);
                    fail("expected exception");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void testShortPassword() throws Throwable {

        final String fullname = "joe bob";
        final String username = "joebob24";
        final String password = "p1";
        final String phonenumber = "3334567890";
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SignUp signup = new SignUp();
                    SignUp.SignUpTask button = signup.new SignUpTask();
                    String result = button.execute(fullname, username, password, phonenumber).get();
                    fail("exception expected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void testGoodInsert() throws Throwable {

        final String fullname = "joe bob";
        final String username = "joebob25";
        final String password = "pass123";
        final String phonenumber = "1235557890";
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SignUp signup = new SignUp();
                    SignUp.SignUpTask button = signup.new SignUpTask();
                    String result = button.execute(fullname, username, password, phonenumber).get();
                    assertNotNull(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail("No exception expected");
                }
            }
        });
    }

    @Test
    public void testBadPhoneNumber() throws Throwable {

        final String fullname = "joe bob";
        final String username = "joebob";
        final String password = "pass123";
        final String phonenumber = "12343";
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SignUp signup = new SignUp();
                    SignUp.SignUpTask button = signup.new SignUpTask();
                    String result = button.execute(fullname, username, password, phonenumber).get();
                    fail("No exception expected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void checkDuplicateUsername() throws Throwable {
        final String fullname = "joe bob";
        final String username = "joebob2";
        final String password = "pass123";
        final String phonenumber = "123434567890";
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    SignUp signup = new SignUp();
                    SignUp.SignUpTask button = signup.new SignUpTask();
                    button.execute(fullname, username, password, phonenumber);
                    String result = button.execute(fullname, username, password, phonenumber).get();
                    fail("exception expected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
