package com.hernandez.mickael.mynews.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.DocViewAdapter
import com.hernandez.mickael.mynews.api.ApiSingleton
import com.hernandez.mickael.mynews.models.search.Doc
import com.hernandez.mickael.mynews.models.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Mickael Hernandez on 17/11/2017.
 */
class ResultActivity : AppCompatActivity() {

    private lateinit var mList : ListView
    private lateinit var mAdapter : DocViewAdapter
    private var mArray : ArrayList<Doc> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        mList = findViewById(R.id.resultList)
        mAdapter = DocViewAdapter(applicationContext, R.layout.article_row, mArray)
        mList.adapter = mAdapter

        var values = intent.extras.getStringArray("values")
        if(values.size < 4) {
            finish()
        }

        title = values[0]

        mList.onItemClickListener = AdapterView.OnItemClickListener{ l: AdapterView<*>, v: View, pos: Int, id: Long ->
            //super.onListItemClick(l, v, position, id)
            val intent = Intent(applicationContext, WebViewActivity::class.java)
            intent.putExtra("url", mArray[pos].webUrl)
            intent.putExtra("title", mArray[pos].headline.main)
            startActivityForResult(intent, 1)
        }

        ApiSingleton.getInstance().articleSearch(values[0], values[1], values[2], values[3]).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, "The research failed.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                var res = response?.body()?.searchSubResponse?.docs
                //if(res != null){
                    mArray.addAll(response?.body()?.searchSubResponse?.docs!!.asIterable())
                    mAdapter.notifyDataSetChanged()
                    findViewById<TextView>(R.id.resultEmpty).visibility = View.GONE
                //} else {
                //    Toast.makeText(applicationContext, "No results", Toast.LENGTH_SHORT).show()
                //}
            }
        })
    }
}