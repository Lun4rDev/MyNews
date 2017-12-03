package com.hernandez.mickael.mynews.activities

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.ArticleViewAdapter
import com.hernandez.mickael.mynews.api.ApiSingleton
import com.hernandez.mickael.mynews.models.ApiResponse
import com.hernandez.mickael.mynews.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mickael Hernandez on 17/11/2017.
 */
class ResultActivity : ListActivity() {

    private lateinit var mList : ListView
    private lateinit var mAdapter : ArticleViewAdapter
    private var mArray : ArrayList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        mList = findViewById(android.R.id.list)
        mAdapter = ArticleViewAdapter(applicationContext, R.layout.article_row, mArray)
        mList.adapter = mAdapter

        var values = intent.extras.getStringArray("values")
        if(values.size < 4) {
            finish()
        }

        ApiSingleton.getInstance().articleSearch(values[0], values[1], values[2], values[3]).enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, "The research failed.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                var res = response?.body()?.articles
                if(res != null && res.count() >= 1){
                    mArray.addAll(response?.body()?.articles!!.asIterable())
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "No results", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val intent = Intent(applicationContext, WebViewActivity::class.java)
        intent.putExtra("url", mArray[position].url)
        startActivity(intent)
    }
}