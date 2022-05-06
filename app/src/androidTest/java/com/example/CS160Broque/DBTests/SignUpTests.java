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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.CS160Broque.BroqueDB;
import com.example.CS160Broque.R;
import com.example.CS160Broque.SignUp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;
import java.net.URL;

@RunWith(AndroidJUnit4.class)
public class SignUpTests extends Activity {
    @Rule
    public ActivityTestRule<SignUp> activityScenarioRule
            = new ActivityTestRule<>(SignUp.class);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    private Handler mHandler;

    @Test
    public void testMissingPassword() {
        HandlerThread handlerThread = new HandlerThread("background-handler");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper);
        mHandler.post(new Runnable() {
            public void run() {
                // Type something
                onView(withId(R.id.edt_fullname_signup))
                        .perform(typeText("joe bob"), closeSoftKeyboard());

                onView(withId(R.id.edt_username_signup))
                        .perform(typeText("joe23"), closeSoftKeyboard());

                onView(withId(R.id.edt_phoneNumber_signup))
                        .perform(typeText("1234567999"), closeSoftKeyboard());
                // Press Button
                onView(withId(R.id.btn_register_signup)).perform(click());

                // Check if matches
                ViewInteraction editText =  onView(withId(R.id.edt_password_signup));
                editText.check(matches(hasErrorText("Password is empty")));
            }
        });
    }

    @Test
    public void testShortPhonenumber() {
        HandlerThread handlerThread = new HandlerThread("background-handler");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper);
        mHandler.post(new Runnable() {
            public void run() {
                // Type something
                onView(withId(R.id.edt_fullname_signup))
                        .perform(typeText("joe bob"), closeSoftKeyboard());

                onView(withId(R.id.edt_username_signup))
                        .perform(typeText("joe24"), closeSoftKeyboard());

                onView(withId(R.id.edt_password_signup)).perform(typeText("pass1234"));

                onView(withId(R.id.edt_phoneNumber_signup))
                        .perform(typeText("3334"), closeSoftKeyboard());
                // Press Button
                onView(withId(R.id.btn_register_signup)).perform(click());

                // Check if matches
                ViewInteraction editText =  onView(withId(R.id.edt_password_signup));
                editText.check(matches(hasErrorText("Phone number must be 10 digits")));
            }
        });
    }




//    @Test
//    public void testMissingPassword() throws Throwable {
//        final String fullname = "joe bob";
//        final String username = "joebob23";
//        final String phonenumber = "1234567999";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    SignUp signup = new SignUp();
//                    signup.register = (Button) findViewById(R.id.btn_register_signup);
//                    signup.broqueDB = new BroqueDB();
//                    signup.username = (EditText) findViewById(R.id.edt_username_signup);
//                    signup.fullname = (EditText) findViewById(R.id.edt_fullname_signup);
//                    signup.password = (EditText) findViewById(R.id.edt_password_signup);
//                    signup.confPassword = (EditText) findViewById(R.id.edt_confPassword_signup);
//                    signup.phonenumber = (EditText) findViewById(R.id.edt_phoneNumber_signup);
//
//                    signup.username.setText(username);
//                    signup.fullname.setText(fullname);
//                    signup.phonenumber.setText(phonenumber);
//                    boolean s = signup.register.performClick();
//
//                    fail("expected exception");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

//    @Test
//    public void testShortPassword() throws Throwable {
//
//        final String fullname = "joe bob";
//        final String username = "joebob24";
//        final String password = "p1";
//        final String phonenumber = "3334567890";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    SignUp signup = new SignUp();
//                    SignUp.SignUpTask button = signup.new SignUpTask();
//                    String result = button.execute(fullname, username, password, phonenumber).get();
//                    fail("exception expected");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    @Test
//    public void testGoodInsert() throws Throwable {
//
//        final String fullname = "joe bob";
//        final String username = "joebob25";
//        final String password = "pass123";
//        final String phonenumber = "1235557890";
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
//    }
//
//    @Test
//    public void testBadPhoneNumber() throws Throwable {
//
//        final String fullname = "joe bob";
//        final String username = "joebob";
//        final String password = "pass123";
//        final String phonenumber = "12343";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    SignUp signup = new SignUp();
//                    SignUp.SignUpTask button = signup.new SignUpTask();
//                    String result = button.execute(fullname, username, password, phonenumber).get();
//                    fail("No exception expected");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//    public void checkDuplicateUsername() throws Throwable {
//        final String fullname = "joe bob";
//        final String username = "joebob2";
//        final String password = "pass123";
//        final String phonenumber = "123434567890";
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    SignUp signup = new SignUp();
//                    SignUp.SignUpTask button = signup.new SignUpTask();
//                    button.execute(fullname, username, password, phonenumber);
//                    String result = button.execute(fullname, username, password, phonenumber).get();
//                    fail("exception expected");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
