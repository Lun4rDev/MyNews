package com.hernandez.mickael.mynews.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.activities.WebViewActivity
import com.hernandez.mickael.mynews.adapters.DocViewAdapter
import com.hernandez.mickael.mynews.api.ApiSingleton
import com.hernandez.mickael.mynews.models.search.Doc
import com.hernandez.mickael.mynews.models.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class SectionFragment : ListFragment(), AdapterView.OnItemLongClickListener {
    val LOG_TAG = "DebugTag"

    /** Section name */
    private lateinit var mSection : String

    /** ArrayList of articles */
    private var mArray : ArrayList<Doc> = ArrayList()

    /** ListView from the layout */
    private lateinit var mList : ListView

    /** Adapter between ArrayList and ListView */
    private lateinit var mAdapter : DocViewAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list, container, false)
        mSection = this.arguments.getString("section")
        mList = view.findViewById(android.R.id.list)
        mAdapter = DocViewAdapter(context, R.layout.article_row, mArray)
        mList.adapter = mAdapter

        val apiService = ApiSingleton.getInstance()
        apiService.section(mSection).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(LOG_TAG, response?.errorBody().toString())
                if(response?.body()?.searchSubResponse?.docs != null){
                    mArray.addAll(response.body()?.searchSubResponse!!.docs.asIterable())
                }

                Log.d("TABSIZE", mArray.size.toString())
                mAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
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
        intent.putExtra("url", mArray[position].webUrl)
        intent.putExtra("title", mArray[position].headline.main)
        startActivity(intent)
    }

    override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long): Boolean {
        val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("url", mArray[pos].webUrl)
        clipboard.primaryClip = clip
        Toast.makeText(context, "Article url copied into clipboard.", Toast.LENGTH_SHORT).show()
        return true
    }
}
