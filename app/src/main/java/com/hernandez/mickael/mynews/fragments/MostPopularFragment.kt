package com.hernandez.mickael.mynews.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.activities.WebViewActivity
import com.hernandez.mickael.mynews.adapters.ArticleViewAdapter
import com.hernandez.mickael.mynews.api.ApiSingleton
import com.hernandez.mickael.mynews.models.main.MainResponse
import com.hernandez.mickael.mynews.models.main.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class MostPopularFragment : Fragment() {
    val LOG_TAG = "DebugTag"

    /** ArrayList of articles */
    private var mArray : ArrayList<Article> = ArrayList()

    /** ListView from the layout */
    private lateinit var mList : ListView

    /** Adapter between ArrayList and ListView */
    private lateinit var mAdapter : ArticleViewAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_mostpopular, container, false)
        mList = view.findViewById(R.id.list_mostpopular)
        mAdapter = ArticleViewAdapter(context, R.layout.article_row, mArray)
        mList.adapter = mAdapter
        var spinner = view.findViewById<ProgressBar>(R.id.progressBar_mp)

        // Item click listener
        mList.onItemClickListener = AdapterView.OnItemClickListener{ a: AdapterView<*>, v: View, i: Int, l: Long ->
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("url", mArray[i].url)
            intent.putExtra("title", mArray[i].title)
            startActivity(intent)
        }

        mList.onItemLongClickListener = AdapterView.OnItemLongClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("url", mArray[i].url)
            clipboard.primaryClip = clip
            Toast.makeText(context, getString(R.string.urlcopied), Toast.LENGTH_SHORT).show()
            true
        }

        // API Call
        val apiService = ApiSingleton.getInstance()
        apiService.mostPopular().enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>?, response: Response<MainResponse>?) {
                //Log.d(LOG_TAG, response?.errorBody().toString())
                mArray.addAll(response?.body()?.articles!!.asIterable())
                Log.d("TABSIZE", mArray.size.toString())
                mAdapter.notifyDataSetChanged()
                spinner.visibility = View.GONE
                mList.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<MainResponse>?, t: Throwable?) {
                t?.printStackTrace()
                spinner.visibility = View.GONE
                Toast.makeText(context, getString(R.string.text_empty), Toast.LENGTH_SHORT).show()
            }

        })
        return view
    }
}
