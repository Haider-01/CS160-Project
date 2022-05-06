package com.example.CS160Broque;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    private Instrumentation.ActivityMonitor am;

    @Rule
    public ActivityTestRule<Settings> rule = new ActivityTestRule<>(Settings.class);

    @Before
    public void init(){
        am = getInstrumentation().addMonitor(Dashboard.class.getName(), null, true);
    }
    
    // Description: check if dashboard activity is launched after clicking the back button
    // Rationale: Test the activity
    @Test
    public void SettingsBtnTest(){
        onView(withId(R.id.edt_update_budget)).perform(click());

        assertEquals(1, am.getHits());
    }



}
