package com.example.CS160Broque.DBTests;

import static org.junit.Assert.fail;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.net.URI;
import java.net.URL;

public class SignUpTests {
    @Test
    public void missingPassword(){
        String fullname = "bob";
        String username = "joe";
        String password = "";
        String phonenumber = "1234567890";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
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
    public void passwordLength(){
        String fullname = "bob";
        String username = "joe";
        String password = "passpasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspass";
        String phonenumber = "1234567890";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
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
    public void properInsert(){
        String fullname = "bob";
        String username = "joe";
        String password = "pass";
        String phonenumber = "1234567890";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
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
    public void properPhone(){
        String fullname = "bob";
        String username = "joe";
        String password = "pass";
        String phonenumber = "1234567890";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
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
    public void repeatSignup(){
        String fullname = "bob";
        String username = "joe";
        String password = "pass";
        String phonenumber = "1234567890";
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22"+fullname+"%22&username=%22"+username+"%22&password=%22"+password+"%22&phonenumber=%22"+phonenumber+"%22";
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
