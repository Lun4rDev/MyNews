package com.hernandez.mickael.mynews.activities

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isNotChecked
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.enums.Section
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Mickael Hernandez on 12/01/2018.
 */
class SearchActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchActivity::class.java)

    private lateinit var mActivity : SearchActivity

    /** Prepares the activity */
    @Before
    fun setUp()
    {
        mActivity = mActivityTestRule.activity
    }

    /** Finishes the activity  */
    @After
    @Throws(Exception::class)
    fun tearDown() {
        mActivity.finish()
    }

    /** Tests that the search button is opening a ResultActivity */
    @Test
    fun search(){
        // register next activity that need to be monitored.
        val activityMonitor = InstrumentationRegistry.getInstrumentation().addMonitor(ResultActivity::class.java.name, null, false)

        // Input "e" as query string
        Espresso.onView(ViewMatchers.withId(R.id.search_query)).perform(replaceText("e"))

        // Checks the Politics checkbox
        onView(allOf(withText(Section.Politics.name))).perform(click())

        // click on the search button
        Espresso.onView(ViewMatchers.withId(R.id.search_button)).perform(ViewActions.click())

        //Watch for the timeout
        val nextActivity = InstrumentationRegistry.getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000)

        // making sure that at least a box is checked
        if(nextActivity == null){
            // Checks the Politics checkbox another time
            onView(allOf(withText(Section.Politics.name))).perform(click())

            // click on the search button another time
            Espresso.onView(ViewMatchers.withId(R.id.search_button)).perform(ViewActions.click())
        }

        // if nextActivity isn't null, a SearchActivity has opened
        assertNotNull(nextActivity)
        nextActivity.finish()
    }
}