package com.example.broque;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String STRING_TO_BE_TYPED = "user";
    private static final String FULLNAME = "user";

    @Rule public ActivityScenarioRule<SignUp> activityScenarioRule
            = new ActivityScenarioRule<>(SignUp.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.broque", appContext.getPackageName());

    }

    @Test
    public void signUpText_signupActivity() {
        // Type something
        onView(withId(R.id.fullnameSignup))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        // Press Button
        onView(withId(R.id.btn_createSignup)).perform(click());

        // Check if matches
        onView(withId(R.id.fullnameSignup)).check(matches(withText(FULLNAME)));
    }
}
