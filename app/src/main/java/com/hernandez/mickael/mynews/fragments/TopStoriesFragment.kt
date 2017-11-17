package com.hernandez.mickael.mynews.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.activities.WebViewActivity
import com.hernandez.mickael.mynews.adapters.ArticleViewAdapter
import com.hernandez.mickael.mynews.api.ApiServiceSingleton
import com.hernandez.mickael.mynews.models.ApiResponse
import com.hernandez.mickael.mynews.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class TopStoriesFragment : ListFragment() {
    val LOG_TAG = "DebugTag"
    private lateinit var mList : ListView
    private lateinit var mAdapter : ArticleViewAdapter
    private var mArray : ArrayList<Article> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_mostpopular, container, false)
        mList = view.findViewById(android.R.id.list)
        mAdapter = ArticleViewAdapter(context, R.layout.article_row, mArray)
        mList.adapter = mAdapter

        val apiService = ApiServiceSingleton.getInstance()
        apiService.topStories().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                //Log.d(LOG_TAG, response?.errorBody().toString())
                mArray.addAll(response?.body()?.articles!!.asIterable())
                Log.d("TABSIZE", mArray.size.toString())
                mAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                Log.d(LOG_TAG, "MOSTPOPULAR API CALL FAILED : ")
                t?.printStackTrace()
            }

        })
        return view
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        Log.d(LOG_TAG, "ONLISTITEMCLICKTRIGGERED")
        super.onListItemClick(l, v, position, id)
        val intent = Intent(activity, WebViewActivity::class.java)
        intent.putExtra("url", mArray[position].url)
        startActivity(intent)
    }
}