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
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.ViewPagerAdapter
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton
import com.hernandez.mickael.mynews.fragments.MostPopularFragment
import com.hernandez.mickael.mynews.fragments.SectionFragment
import com.hernandez.mickael.mynews.fragments.TopStoriesFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val LOG_TAG = "DebugTag"

    private var mViewPagerAdapter : ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
    lateinit var mSharedPrefs : SharedPreferences
    lateinit var navView : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Shared preferences
        mSharedPrefs = application.getSharedPreferences(getString(R.string.app_name), android.content.Context.MODE_PRIVATE)


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

        mViewPagerAdapter.notifyDataSetChanged()

        supportFragmentManager.beginTransaction().commit()

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
            R.id.action_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
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
        // Handle navigation drawer item clicks here.
        when (item.itemId) {
            R.id.item_mostpopular -> {
                tabLayout.getTabAt(0)?.select()
            }
            R.id.item_topstories -> {
                tabLayout.getTabAt(1)?.select()
            }
            else -> {
                (0..tabLayout.tabCount)
                        .filter { tabLayout.getTabAt(it)?.text == item.title }
                        .forEach { tabLayout.getTabAt(it)?.select() }
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
