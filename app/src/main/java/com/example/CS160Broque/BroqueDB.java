package com.example.CS160Broque;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class BroqueDB {

    /**
     * Inserts Registration Data to Database
     *
     * @param fullname
     * @param username
     * @param password
     * @param phonenumber
     * @return PHP echo statement
     * @throws IOException
     * @throws URISyntaxException
     */
    public String signup(String fullname, String username, String password, String phonenumber) throws IOException, URISyntaxException {
        // -----------------------------
        // TODO Check if username taken
        // -----------------------------
        System.out.println("signup");
        // Replace spaces with %20 (HTML Code for Space)
        fullname = fullname.replaceAll(" ", "%20");
        username = username.replaceAll(" ", "%20");
        password = password.replaceAll(" ", "%20");

        // Validate Phonenumber Userinput
//        if (phonenumber.length() != 10) {
//            return "Phone number should be 10 characters";
//        } else if (!phonenumber.matches("[0-9]+")) {
//            return "Phone number should contain only numbers";
//        }
//
//        // Validate Password Userinput
//        if (password.length() <= 4) {
//            return "Password too weak, must be at least 4 characters long";
//        } else if (!password.matches(".*[0-9].*")) {
//            return "Password must contain at least one number";
//        } else if (!password.toLowerCase().matches(".*[a-z].*")) {
//            return "Password must contain at least one letter";
//        }

        // Link to DB
        String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22" + fullname + "%22&username=%22" + username + "%22&password=%22" + password + "%22&phonenumber=%22" + phonenumber + "%22";
        System.out.println(link);
        //String link = "https://broke-test.herokuapp.com/signup.php?fullname=%22this%22&username=%22isfrom%22&password=%22android%22&phonenumber=%22studio%22";

        URL url = new URL(link);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI(new URI(link));
        HttpResponse response = client.execute(request);
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer sb = new StringBuffer("");
        String line = "";

        while ((line = in.readLine()) != null) {
            sb.append(line);
            System.out.println("From sb" + sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public String login(String username, String password) throws IOException {
        String link = "https://broke-test.herokuapp.com/login.php";
        String data = URLEncoder.encode("username", "UTF-8") + "=" +
                URLEncoder.encode(username, "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                URLEncoder.encode(password, "UTF-8");
        System.out.println(link);


        URL url = new URL(link);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line = null;

        // Read Server Response
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            break;
        }
        reader.close();
        return sb.toString();
    }

    // Inserts values into db
    public void insert() {

    }

    // Retrieves data from db
    public void retrieve() {

    }
}
