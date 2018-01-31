package com.hernandez.mickael.mynews.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
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
class SectionFragment : Fragment() {
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
        val view = inflater!!.inflate(R.layout.fragment_section, container, false)
        mSection = this.arguments.getString("section")
        mList = view.findViewById(R.id.list_section)
        mAdapter = DocViewAdapter(context, R.layout.article_row, mArray)
        mList.adapter = mAdapter
        var spinner = view.findViewById<ProgressBar>(R.id.progressBar_s)
        // Item click listener
        mList.onItemClickListener = AdapterView.OnItemClickListener{ a: AdapterView<*>, v: View, i: Int, l: Long ->
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra("url", mArray[i].webUrl)
            intent.putExtra("title", mArray[i].headline.main)
            startActivity(intent)
        }

        mList.onItemLongClickListener = AdapterView.OnItemLongClickListener{ adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("url", mArray[i].webUrl)
            clipboard.primaryClip = clip
            Toast.makeText(context, getString(R.string.urlcopied), Toast.LENGTH_SHORT).show()
            true
        }

        val apiService = ApiSingleton.getInstance()
        apiService.section(mSection).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.d(LOG_TAG, response?.errorBody().toString())
                if(response?.body()?.searchSubResponse?.docs != null){
                    mArray.addAll(response.body()?.searchSubResponse!!.docs.asIterable())
                }

                Log.d("TABSIZE", mArray.size.toString())
                mAdapter.notifyDataSetChanged()
                spinner.visibility = View.GONE
                mList.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                Log.d(LOG_TAG, "MOSTPOPULAR API CALL FAILED : ")
                t?.printStackTrace()
                spinner.visibility = View.GONE
                Toast.makeText(context, getString(R.string.text_empty), Toast.LENGTH_SHORT).show()
            }

        })
        return view
    }
}
