package com.example.CS160Broque.DBTests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.CS160Broque.Login;
import com.example.CS160Broque.SignUp;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTests {

    @Rule public ActivityScenarioRule<SignUp> activityScenarioRule
            = new ActivityScenarioRule<>(SignUp.class);

//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//
//        assertEquals("com.example.broque", appContext.getPackageName());
//
//    }



//    @Test
//    public void signUpText_signupActivity() {
//        // Type something
//        onView(withId(R.id.fullnameSignup))
//                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
//        // Press Button
//        onView(withId(R.id.btn_createSignup)).perform(click());
//
//        // Check if matches
//        onView(withId(R.id.fullnameSignup)).check(matches(withText(FULLNAME)));
//    }

    @Test
    public void testIncorrectUsername() throws Throwable{

        final String username = "notregistered";
        final String password = "pass123";
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Login login = new Login();
                    Login.LoginTask task = login.new LoginTask();
                    String result = task.doInBackground(username, password);
                    fail("Exception expected");

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }

    @Test
    public void testCorrectLoginInfo() throws Throwable{

        final String fullname = "joe bob";
        final String username = "joebob27";
        final String password = "pass123";
        final String phonenumber = "1435557893";
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

        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Login login = new Login();
                    Login.LoginTask task = login.new LoginTask();
                    String result = task.doInBackground(username, password);
                    assertNotNull(result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    fail("No exception expected");
                }
            }
        });

    }

    @Test
    public void testIncorrectPassword() throws Throwable{
        final String fullname = "joe bob";
        final String username = "joebob28";
        final String password = "pass123";
        final String phonenumber = "1235507893";
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

        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Login login = new Login();
                    Login.LoginTask task = login.new LoginTask();
                    String result = task.doInBackground(username, "incorrectPass");
                    fail("Exception expected");

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}