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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class forgetPassTest {

    private Instrumentation.ActivityMonitor am;

    @Rule
    public ActivityTestRule<forgetPass> rule = new ActivityTestRule<>(forgetPass.class);

    @Before
    public void init(){
        am = getInstrumentation().addMonitor(Login.class.getName(), null, true);
    }

    // Description: Check if Login Activity is launched after receiving notification
    // Rationale: Testing the activity
    @Test
    public void forgetPassBtnTest(){
        onView(withId(R.id.sendPassButton)).perform(click());

        assertEquals(1, am.getHits());
    }



}