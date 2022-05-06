package com.example.CS160Broque.DBTests;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

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

import com.example.CS160Broque.Login;
import com.example.CS160Broque.R;
import com.example.CS160Broque.SignUp;
import com.example.CS160Broque.addExpense;

@RunWith(AndroidJUnit4.class)
public class BudgetExpenseTest extends Activity {

    @Rule
    public ActivityTestRule<addExpense> activityScenarioRule
            = new ActivityTestRule<>(addExpense.class);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    private Handler mHandler;

    @Test
    public void testAddExpense() throws Throwable{
        final String fullname = "joe bob";
        final String username = "joebob27";
        final String password = "pass123";
        final String phonenumber = "1235550000";

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
                onView(withId(R.id.edt_entertainment_dashboard))
                        .perform(typeText("10"), closeSoftKeyboard());

                // Press Button
                onView(withId(R.id.btn_addExpense_dashboard)).perform(click());

                // Check if matches
                ViewInteraction editText =  onView(withId(R.id.edt_entertainment_dashboard));
                editText.check(matches(hasErrorText("Expense added successfully")));
            }
        });

    }

}
