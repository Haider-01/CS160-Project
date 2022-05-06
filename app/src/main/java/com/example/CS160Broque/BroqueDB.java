package com.example.CS160Broque;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    /**
     * @param username
     * @return
     * @throws IOException
     */
    public String login(String username) throws IOException {
        String link = "https://broke-test.herokuapp.com/login.php";
        String data = URLEncoder.encode("username", "UTF-8") + "=" +
                URLEncoder.encode(username, "UTF-8");
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

    public String changeName(String currUser, String newName) throws IOException, URISyntaxException {
        String link = "https://broke-test.herokuapp.com/changeName.php?current_username=%22" + currUser + "%22&new_username=%22" + newName + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    public String changePass(String currUser, String newPass) throws IOException, URISyntaxException {
        String link = "https://broke-test.herokuapp.com/changePass.php?username=%22" + currUser + "%22&password=%22" + newPass + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    public String insertBudget(String username, String tBudget, String bBudget, String fBudget, String eBudget, String oBudget) throws IOException, URISyntaxException {
        // Link to DB
        String link = "https://broke-test.herokuapp.com/insertBudget.php?username=%22" + username + "%22&total=%22" + tBudget + "%22&bill=%22" + bBudget + "%22&food=%22"
                + fBudget + "%22&entertainment=%22" + eBudget + "%22&other=%22" + oBudget + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    /**
     * 
     * @param category
     * @param amount
     * @param username
     * @return "Category updated successfully" from PHP
     * @throws IOException
     * @throws URISyntaxException
     */
    public String updateBudget(String category, String amount, String username) throws IOException, URISyntaxException {
        // Link to DB
        String link = "https://broke-test.herokuapp.com/updateBudgets.php?category=%22" + category + "%22&budget=%22" + amount + "%22&username=%22" + username + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    public String updateTotalBudget(String username, String total)  throws IOException, URISyntaxException {
        String link = "https://broke-test.herokuapp.com/updateTotalBudget.php?username=%22" + username + "%22&budget=%22" + total + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    // Returns budgets from database in array form
    // budget[0] = total
    // budget[1] = bill
    // budget[2] = food
    // budget[3] = entertainment
    // budget[4] = other
    public double[] getBudget(String username) throws IOException, URISyntaxException {
        String link = "http://broke-test.herokuapp.com/getBudget.php?username=%22" + username + "%22";

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
            System.out.println(sb);
            break;
        }

        String [] str = sb.toString().split("<br>");

        double total = Double.parseDouble(str[1]);
        double bill = Double.parseDouble(str[2]);
        double food = Double.parseDouble(str[3]);
        double entertainment = Double.parseDouble(str[4]);
        double other = Double.parseDouble(str[5]);

        double[] budget = {total, bill, food, entertainment, other};
        return budget;
    }

    // Returns expenses from database
    // budget[0] = total
    // budget[1] = bill
    // budget[2] = food
    // budget[3] = entertainment
    // budget[4] = other
    public double[] getExpense(String username) throws IOException, URISyntaxException {
        String link = "http://broke-test.herokuapp.com/getExpense.php?username=%22" + username + "%22";

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
            System.out.println(sb);
            break;
        }

        String [] str = sb.toString().split("<br>");

        double total = Double.parseDouble(str[1]);
        double bill = Double.parseDouble(str[2]);
        double food = Double.parseDouble(str[3]);
        double entertainment = Double.parseDouble(str[4]);
        double other = Double.parseDouble(str[5]);

        double[] budget = {total, bill, food, entertainment, other};
        return budget;
    }

    // Given user, return user account details
    // str[0] = fullname
    // str[1] = username
    // str[2] = password
    // str[3] = phonenumber
    public String[] getUser(String username) throws IOException, URISyntaxException {
        String link = "http://broke-test.herokuapp.com/getUser.php?username=%22" + username + "%22";

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
            System.out.println(sb);
            break;
        }

        String [] str = sb.toString().split("<br>");

        return str;
    }

    public String updateExpense(String username, String budgetType, String expense) throws IOException, URISyntaxException {
        // Link to DB
        String link = "https://broke-test.herokuapp.com/updateExpenses.php?username=%22" + username + "%22&category=%22" + budgetType + "%22&amount=%22" + expense + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    public String updateTotalExpense(String username, String total)  throws IOException, URISyntaxException {
        String link = "https://broke-test.herokuapp.com/updateTotalExpense.php?username=%22" + username + "%22&expense=%22" + total + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    public String forgetPass(String username, String phonenumber)  throws IOException, URISyntaxException {
        String link = "https://broke-test.herokuapp.com/forgetPass.php?username=%22" + username + "%22&phonenumber=%22" + phonenumber + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }

    public String deleteAccount(String username) throws IOException, URISyntaxException {
        String link = "https://broke-test.herokuapp.com/deleteAccount.php?username=%22" + username + "%22";
        System.out.println(link);

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
            System.out.println(sb);
            break;
        }
        in.close();

        return sb.toString();
    }


}
