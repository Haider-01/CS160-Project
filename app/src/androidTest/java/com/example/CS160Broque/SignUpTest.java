package com.example.CS160Broque;

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

import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class SignUpTest {


    private String error1,error2,error3,error4,pass,confPass;
    private Instrumentation.ActivityMonitor am;

    @Rule
    public ActivityTestRule<SignUp> rule = new ActivityTestRule<>(SignUp.class);

    @Before
    public void init(){
        error1="Username is empty";
        error2="Email is empty";
        error3="Password is empty";
        error4="Confirm the password!";
        pass="sos";
        confPass="sos";
        am = getInstrumentation().addMonitor(UserFields.class.getName(), null, true);
    }



    // Description: Check if Userfields Activity is launched after clicking the login button and checking if we can input values
    // Rationale: Testing the activity and editText
    @Test
    public void MainActivityBtnTest(){
        onView(withId(R.id.edt_password_SignUp)).perform(typeText(pass));
        onView(withId(R.id.edt_confPassword_SignUp)).perform(typeText(confPass));

        onView(withId(R.id.btn_register_SignUp)).perform(click());

        assertEquals(1, am.getHits());
    }


    // Description: Check if error is raised if fields are left empty
    // Rationale: Make sure user input values for these fields
    @Test
    public void signUpBtnTest(){

        onView(withId(R.id.btn_register_SignUp)).perform(click());

        onView(withId(R.id.edt_username_SignUp)).check(matches(hasErrorText(error1)));
        onView(withId(R.id.edt_email_SignUp)).check(matches(hasErrorText(error2)));
        onView(withId(R.id.edt_password_SignUp)).check(matches(hasErrorText(error3)));
        onView(withId(R.id.edt_confPassword_SignUp)).check(matches(hasErrorText(error4)));
    }



}