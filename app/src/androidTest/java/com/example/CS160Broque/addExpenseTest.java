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
public class addExpenseTest {

    private Instrumentation.ActivityMonitor am;
    private String amount;

    @Rule
    public ActivityTestRule<addExpense> rule = new ActivityTestRule<>(addExpense.class);

    @Before
    public void init(){
        amount = "100";
        am = getInstrumentation().addMonitor(Dashboard.class.getName(), null, true);
    }


    // Description: Check if Dashboard Activity is launched after clicking the add expense button
    // Rationale: Testing the add expense functionality
    @Test
    public void addExpenseBtnTest(){

        onView(withId(R.id.editTextNumber2)).perform(typeText(amount));
        onView(withId(R.id.addExpense)).perform(click());

        assertEquals(1, am.getHits());
    }



}