package com.example.broque;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTests {

    @Test
    public void incorrectUsername(){
        String username = "wrongjoe";
        String password = "pass";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22";        try{
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);  // should not get exception from php insertion
        }
        catch(Exception e) {
            fail("Exception caught when inserting data");
        }
    }

    @Test
    public void incorrectPassword(){
        String username = "joe";
        String password = "wrongpass";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22";        try{
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);  // should not get exception from php insertion
        }
        catch(Exception e) {
            fail("Exception caught when inserting data");
        }
    }

    @Test
    public void correctInfo(){
        String username = "joe";
        String password = "pass";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22";        try{
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);  // should not get exception from php insertion
        }
        catch(Exception e) {
            fail("Exception caught when inserting data");
        }
    }

    @Test
    public void missingUsername(){
        String username = "";
        String password = "pass";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22";
        try{
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);  // should not get exception from php insertion
        }
        catch(Exception e) {
            fail("Exception caught when inserting data");
        }
    }

    @Test
    public void missingPassword(){
        String username = "joe";
        String password = "";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22";
        try{
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);  // should not get exception from php insertion
        }
        catch(Exception e) {
            fail("Exception caught when inserting data");
        }
    }
}

