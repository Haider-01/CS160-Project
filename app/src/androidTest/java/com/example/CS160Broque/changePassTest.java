package com.example.CS160Broque;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class changePassTest {

    private Instrumentation.ActivityMonitor am;
    String newPass, confPass,currPass;

    @Rule
    public ActivityTestRule<changePass> rule = new ActivityTestRule<>(changePass.class);

    @Before
    public void init(){
        currPass="sos";
        newPass="sos";
        confPass="sos";
        am = getInstrumentation().addMonitor(AccountScreen.class.getName(), null, true);
    }

    // Description: Check if AccountScreen Activity is launched after clicking the back button
    // Rationale: Testing the activity
    @Test
    public void changePassBtnTest(){
        onView(withId(R.id.edt_currPass_changepass)).perform(typeText(currPass));
        onView(withId(R.id.edt_newPass_changepass)).perform(typeText(newPass));
        onView(withId(R.id.edt_confirmPass_changepass)).perform(typeText(confPass));
        closeSoftKeyboard();
        onView(withId(R.id.btn_changePass_changepass)).perform(click());

        assertEquals(1, am.getHits());
    }



}
