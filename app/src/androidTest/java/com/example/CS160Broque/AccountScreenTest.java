package com.example.CS160Broque;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class AccountScreenTest {

    private Instrumentation.ActivityMonitor am;

    @Rule
    public ActivityTestRule<AccountScreen> rule = new ActivityTestRule<>(AccountScreen.class);

    @Before
    public void init(){
        am = getInstrumentation().addMonitor(Login.class.getName(), null, true);
    }

    // Description: Check if Login Activity is launched after clicking the logout menu
    // Rationale: Testing the logout functionality
    @Test
    public void logoutTest(){

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Logout")).perform(click());

        assertEquals(1, am.getHits());
    }



}