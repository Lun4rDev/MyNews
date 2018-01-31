package com.hernandez.mickael.mynews.activities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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

/** Displays results of the search from SearchActivity. Extras contains the parameters to make the API call via the ApiSingleton */
class ResultActivity : AppCompatActivity() {

    /** Docs list */
    private lateinit var mList : ListView

    /** Custom list adapter */
    private lateinit var mAdapter : DocViewAdapter

    /** Docs array */
    private var mArray : ArrayList<Doc> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Setting up list and adapter
        mList = findViewById(R.id.resultList)
        mAdapter = DocViewAdapter(applicationContext, R.layout.article_row, mArray)
        mList.adapter = mAdapter

        // If any argument is missing, quit the activity
        var values = intent.extras.getStringArray("values")
        if(values.size < 4) {
            finish()
        }

        title = values[0]

        // Item click listener
        mList.onItemClickListener = AdapterView.OnItemClickListener{ _: AdapterView<*>, _: View, pos: Int, _: Long ->
            val intent = Intent(applicationContext, WebViewActivity::class.java)
            intent.putExtra("url", mArray[pos].webUrl)
            intent.putExtra("title", mArray[pos].headline.main)
            startActivityForResult(intent, 1)
        }

        // Item long click listener
        mList.onItemLongClickListener = AdapterView.OnItemLongClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("url", mArray[i].webUrl)
            clipboard.primaryClip = clip
            Toast.makeText(applicationContext, getString(R.string.urlcopied), Toast.LENGTH_SHORT).show()
            true
        }

        ApiSingleton.getInstance().articleSearch(values[0], values[1], values[2], values[3]).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                Toast.makeText(applicationContext, "The research failed.", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
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