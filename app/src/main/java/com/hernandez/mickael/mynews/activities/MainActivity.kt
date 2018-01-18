package com.hernandez.mickael.mynews.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.ProgressBar
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.ViewPagerAdapter
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton
import com.hernandez.mickael.mynews.fragments.MostPopularFragment
import com.hernandez.mickael.mynews.fragments.SectionFragment
import com.hernandez.mickael.mynews.fragments.TopStoriesFragment
import kotlinx.android.synthetic.main.activity_main.*

/** Main activity of the application */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /** ViewPagerAdapter used to manage the fragments */
    private var mViewPagerAdapter : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

    /** Navigation drawer */
    lateinit var navView : NavigationView

    /** On object creation */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflates the activity layout
        setContentView(R.layout.activity_main)

        // Sets the toolbar
        setSupportActionBar(toolbar)

        // UI elements
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.fragmentpager)
        navView = findViewById(R.id.nav_view)
        //val subMenu = navView.menu.ad

        SectionSingleton.loadSections(applicationContext)

        // Setting viewpager adapter, linking to tab layout
        viewPager.adapter = mViewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        // Adding fragments for Most Popular and Top Stories
        mViewPagerAdapter.addFragment(MostPopularFragment(), getString(R.string.most_popular))
        mViewPagerAdapter.addFragment(TopStoriesFragment(), getString(R.string.top_stories))

        // Adding fragment and menu row for each selected sections
        for(s : String in SectionSingleton.sections){
            val bundle = Bundle()
            bundle.putString("section", s)
            val sf = SectionFragment()
            sf.arguments = bundle
            mViewPagerAdapter.addFragment(sf, s)
            navView.menu.add(R.id.nav_sections, 123, Menu.NONE, s)
        }

        // Notifying the adapter that the data changed
        mViewPagerAdapter.notifyDataSetChanged()

        //supportFragmentManager.beginTransaction().commit()

        // Drawer configuration
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    /** When back button is pressed */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /** Inflates the menu - this adds items to the action bar if it is present */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /** Handles clicks on item in the toolbar */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Search
            R.id.action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
            // Notification
            R.id.action_notifications -> {
                startActivity(Intent(this, NotificationActivity::class.java))
                return true
            }
            // Help
            R.id.action_help -> {
                startActivity(Intent(this, HelpActivity::class.java))
                return true
            }
            // About
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    /** Handles clicks on the drawer menu */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Most Popular
            R.id.item_mostpopular -> {
                tabLayout.getTabAt(0)?.select()
            }
            // Top Stories
            R.id.item_topstories -> {
                tabLayout.getTabAt(1)?.select()
            }
            // If a number between 0 and tabCount
            else -> {
                (0..tabLayout.tabCount)
                        // finds the tab with the corresponding title
                        .filter { tabLayout.getTabAt(it)?.text == item.title }
                        .forEach { tabLayout.getTabAt(it)?.select() }
            }
        }
        // Close the drawer
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
