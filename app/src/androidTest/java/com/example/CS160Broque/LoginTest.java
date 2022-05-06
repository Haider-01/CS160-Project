package com.example.CS160Broque;

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private String username, password,error1,error2;
    private Instrumentation.ActivityMonitor am;
    @Rule
    public ActivityTestRule<Login> rule = new ActivityTestRule<>(Login.class);

    @Before
    public void init(){
        username = "";
        password = "sos";
        error1="Username is required!";
        error2="Password is required!";
        am = getInstrumentation().addMonitor(Dashboard.class.getName(), null, true);
    }

    // Description: Check if Dashboard Activity is launched after clicking the login button and checking if we can input values
    // Rationale: Testing the activity and editText
    @Test
    public void loginBtnTest(){
        onView(withId(R.id.edt_username_login)).perform(typeText(username));
        onView(withId(R.id.edt_password_login)).perform(typeText(password));

        onView(withId(R.id.btn_login_login)).perform(click());

        assertEquals(1, am.getHits());
    }


    // Description: Check if error is set on the editText if it is left empty
    // Rationale: Make sure user fill the fields
    @Test
    public void loginBtnTest2(){

        onView(withId(R.id.btn_login_login)).perform(click());

        onView(withId(R.id.edt_username_login)).check(matches(hasErrorText(error1)));
        onView(withId(R.id.edt_password_login)).check(matches(hasErrorText(error2)));
    }

}