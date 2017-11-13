package com.hernandez.mickael.mynews.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.ViewPagerAdapter
import com.hernandez.mickael.mynews.fragments.MostPopularFragment
import com.hernandez.mickael.mynews.fragments.TopStoriesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val LOG_TAG = "DebugTag"

    private var mViewPagerAdapter : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // UI elements
        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        var viewPager = findViewById<ViewPager>(R.id.viewpager)

        // Setting viewpager adapter, linking to tab layout
        viewPager.adapter = mViewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        // Adding fragments
        mViewPagerAdapter.addFragment(MostPopularFragment(), getString(R.string.most_popular))
        mViewPagerAdapter.addFragment(TopStoriesFragment(), getString(R.string.top_stories))
        mViewPagerAdapter.notifyDataSetChanged()

        // Drawer
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /** Handle action bar item clicks */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_nofitications -> {
                startActivity(Intent(this, NotificationActivity::class.java))
                return true
            }
            R.id.action_help -> return true
            R.id.action_about -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_all -> {
                // Handle the camera action
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
