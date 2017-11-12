package com.hernandez.mickael.mynews.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.ListView
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.ArticleViewAdapter
import com.hernandez.mickael.mynews.interfaces.MostPopularInterface
import com.hernandez.mickael.mynews.models.Article
import com.hernandez.mickael.mynews.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var url = "https://api.nytimes.com/svc/"
    lateinit var response : Response<Result>

    private lateinit var mostPopInterface : MostPopularInterface
    private var mostPopularArray : ArrayList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var topStoriesList = findViewById<ListView>(R.id.topStoriesList)
        var mostPopularList = findViewById<ListView>(R.id.mostPopularList)
        if(mostPopularList != null){

            mostPopularList.adapter = ArticleViewAdapter(this.baseContext, R.layout.article_row, mostPopularArray)
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        var retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
        mostPopInterface = retrofit.create(MostPopularInterface::class.java)

        var tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 1")); way to add other categories tabs

        loadMostPopular()
        //Log.d("RESULT", result.get(1).title))
    }

    fun loadMostPopular() {
        var call : Call<Result> = mostPopInterface.all()
        val callback = object : Callback<Result> {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>) {
                //mostPopularArray = response.body()
                //Log.d("RESULT", result.toString())
            }

        }
        call.enqueue(callback)
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
