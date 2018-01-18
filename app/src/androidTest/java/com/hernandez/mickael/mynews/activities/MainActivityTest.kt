package com.hernandez.mickael.mynews.activities

import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.widget.ListView
import com.hernandez.mickael.mynews.R
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Mickael Hernandez on 08/01/2018.
 */
class MainActivityTest {

    private val maxWaitingTime = 2000L

    @Rule @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var mActivity : MainActivity

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

    /** Tests that the tab lists are not empty, hence that both api call and gson conversion are ok */
    @Test
    fun articles() {
        // register next activity that need to be monitored.
        val activityMonitor = getInstrumentation().addMonitor(WebViewActivity::class.java.name, null, false)

        // Waiting max time for the articles to appear
        Thread.sleep(maxWaitingTime)

        // Click on the first item of the list with corresponding id
        onData(anything()).inAdapterView(allOf(withId(R.id.list_mostpopular))).atPosition(0).perform(click())

        //Watch for the timeout
        val nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500)

        // if nextActivity isn't null, a SearchActivity has opened
        assertNotNull(nextActivity)
        nextActivity.finish()
    }

    /** Tests the navigation drawer and its elements */
    @Test
    fun navigationDrawer() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()) // opens the navigation drawer
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.item_topstories)) // clicks on the top stories item in drawer
        assert(mActivity.tabLayout.selectedTabPosition == 1) // assert that the selected tab is the second (top stories tab)
    }

    /** Tests that the search button opens a SearchActivity */
    @Test
    fun search() {
        // register next activity that need to be monitored.
        val activityMonitor = getInstrumentation().addMonitor(SearchActivity::class.java.name, null, false)

        // click on the search button
        onView(withId(R.id.action_search)).perform(click())

        //Watch for the timeout
        val nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000)

        // if nextActivity isn't null, a SearchActivity has opened
        assertNotNull(nextActivity)
        nextActivity.finish()
    }

    /** Tests that the notification menu option opens a NotificationActivity */
    @Test
    fun notifications() {
        // opens the actionbar menu
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext())

        // register next activity that need to be monitored.
        val activityMonitor = getInstrumentation().addMonitor(NotificationActivity::class.java.name, null, false)

        // click on the notifications menu item
        onView(withText(mActivity.getString(R.string.action_notifications))).perform(click())

        //Watch for the timeout
        val nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000)

        // if nextActivity isn't null, a NotificationActivity has opened
        assertNotNull(nextActivity)
        nextActivity.finish()
    }

}