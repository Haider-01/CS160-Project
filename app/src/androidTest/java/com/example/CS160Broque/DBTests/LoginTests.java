package com.example.CS160Broque.DBTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.widget.Button;

import com.example.CS160Broque.BroqueDB;
import com.example.CS160Broque.Login;
import com.example.CS160Broque.R;
import com.example.CS160Broque.SignUp;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTests extends Activity {
    @Rule
    public ActivityTestRule<Login> activityScenarioRule
            = new ActivityTestRule<>(Login.class);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    private Handler mHandler;

    @Test
    public void testIncorrectPassword() {

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
                    fail("exception expected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        HandlerThread handlerThread = new HandlerThread("background-handler");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper);
        mHandler.post(new Runnable() {
            public void run() {
                // Type something
                onView(withId(R.id.edt_username_login))
                        .perform(typeText("joebob25"), closeSoftKeyboard());

                onView(withId(R.id.edt_password_login))
                        .perform(typeText("badpass"), closeSoftKeyboard());

                // Check if matches
                ViewInteraction editText =  onView(withId(R.id.edt_password_login));
                editText.check(matches(hasErrorText("Incorrect password")));
            }
        });
    }

    @Test
    public void testGoodLogin(){
        final String fullname = "joe bob";
        final String username = "joebob26";
        final String password = "pass123";
        final String phonenumber = "1222557890";

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
        HandlerThread handlerThread = new HandlerThread("background-handler");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper);
        mHandler.post(new Runnable() {
            public void run() {
                // Type something
                onView(withId(R.id.edt_username_login))
                        .perform(typeText("joebob26"), closeSoftKeyboard());

                onView(withId(R.id.edt_password_login))
                        .perform(typeText("pass123"), closeSoftKeyboard());

                // Check if matches
                ViewInteraction editText =  onView(withId(R.id.edt_password_login));
                editText.check(matches(hasErrorText("Login successful")));
            }
        });
    }

//    @Test
//    public void testIncorrectUsername() throws Throwable{
//
//        final String username = "notregistered";
//        final String password = "pass123";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    Login login = new Login();
//                    Login.LoginTask task = login.new LoginTask();
//                    String result = task.doInBackground(username, password);
//                    fail("Exception expected");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    @Test
//    public void testCorrectLoginInfo() throws Throwable{
//
//        final String fullname = "joe bob";
//        final String username = "joebob27";
//        final String password = "pass123";
//        final String phonenumber = "1435557893";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    SignUp signup = new SignUp();
//                    signup.broqueDB = new BroqueDB();
////                    signup.login = findViewByid(R.id.btn_register_signup);
//                    SignUp.SignUpTask button = signup.new SignUpTask();
//                    String result = button.doInBackground(fullname, username, password, phonenumber);
//                    assertNotNull(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    fail("No exception expected");
//                }
//            }
//        });
//
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    Login login = new Login();
//                    Login.LoginTask task = login.new LoginTask();
//                    String result = task.doInBackground(username, password);
//                    assertNotNull(result);
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                    fail("No exception expected");
//                }
//            }
//        });
//
//    }
//
//    @Test
//    public void testIncorrectPassword() throws Throwable{
//        final String fullname = "joe bob";
//        final String username = "joebob28";
//        final String password = "pass123";
//        final String phonenumber = "1235507893";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    SignUp signup = new SignUp();
//                    SignUp.SignUpTask button = signup.new SignUpTask();
//                    String result = button.execute(fullname, username, password, phonenumber).get();
//                    assertNotNull(result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    fail("No exception expected");
//                }
//            }
//        });
//
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    Login login = new Login();
//                    Login.LoginTask task = login.new LoginTask();
//                    String result = task.doInBackground(username, "incorrectPass");
//                    fail("Exception expected");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//                }
//            }
//        });
//    }
}