package com.hernandez.mickael.mynews.activities

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Mickael Hernandez on 16/01/2018.
 */
class NotificationActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(NotificationActivity::class.java)

    private lateinit var mActivity : NotificationActivity

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

    /** Tests that the checkboxes are updating the sections in singleton */
    @Test
    fun section(){
        val size = SectionSingleton.sections.size
        onView(CoreMatchers.allOf(ViewMatchers.withText(Section.Politics.name))).perform(click())
        assertNotEquals(size, SectionSingleton.sections.size)
    }

    /** Tests that the switch is enabling/disabling the notifications state in singleton */
    @Test
    fun switchNotifications(){
        val state = SectionSingleton.state
        onView(withId(R.id.notif_switch)).perform(click())
        assertNotEquals(state, SectionSingleton.state)
    }
}