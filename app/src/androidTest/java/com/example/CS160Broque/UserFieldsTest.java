package com.example.CS160Broque;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.not;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import android.app.Instrumentation;

@RunWith(AndroidJUnit4.class)
public class UserFieldsTest {

    private Instrumentation.ActivityMonitor am;

    private String moIncome,choice, choice2;

    @Rule
    public ActivityTestRule<UserFields> rule = new ActivityTestRule<>(UserFields.class);

    @Before
    public void init(){
        moIncome = "10000";
        choice = "Default";
        choice2 = "Manual Input";
        am = getInstrumentation().addMonitor(Login.class.getName(), null, true);
    }
    
    // Description: Check if Login Activity is launched
    // Rationale: Test the activity
    @Test
    public void UserFieldsBtnTest(){
        onView(withId(R.id.btn_finish_userfields)).perform(click());

        assertEquals(1, am.getHits());
    }
    
    
    //Desc: Check if user can choose the default option
    // Rationale: Making sure functionality works
    @Test
    public void defaultOptionTest(){
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choice))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(choice))));
    }
    
    // Desc: Check if fields are disabled if user choose default option
    // Rationale: Making sure user can't enter budget manually
    @Test
    public void defaultOptionTest2(){
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choice))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(choice))));
        onView(withId(R.id.edt_total_userfields)).check(matches(not(isEnabled())));
        onView(withId(R.id.edt_bill_userfields)).check(matches(not(isEnabled())));
        onView(withId(R.id.edt_food_userfields)).check(matches(not(isEnabled())));
        onView(withId(R.id.edt_entertainment_userfields)).check(matches(not(isEnabled())));
        onView(withId(R.id.edt_other_userfields)).check(matches(not(isEnabled())));
    }
    
    
    //Desc: Check if user can choose the manual option
    // Rationale: Making sure functionality works
    @Test
    public void manualOptionTest(){
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choice2))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(choice2))));
    }

    
    // Desc: Check if fields are enabled if user choose manual option
    // Rationale: Making sure user can enter budget manually
    @Test
    public void manualOptionTest2(){
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(choice2))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString(choice2))));
        onView(withId(R.id.edt_total_userfields)).check(matches(isEnabled()));
        onView(withId(R.id.edt_bill_userfields)).check(matches(isEnabled()));
        onView(withId(R.id.edt_food_userfields)).check(matches(isEnabled()));
        onView(withId(R.id.edt_entertainment_userfields)).check(matches(isEnabled()));
        onView(withId(R.id.edt_other_userfields)).check(matches(isEnabled()));
        onView(withId(R.id.edt_monthlyincome_userfields)).check(matches(not(isEnabled())));
}



}
