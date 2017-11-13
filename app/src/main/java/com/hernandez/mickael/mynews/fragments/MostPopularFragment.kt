package com.hernandez.mickael.mynews.fragments

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.adapters.ArticleViewAdapter
import com.hernandez.mickael.mynews.api.ApiServiceSingleton
import com.hernandez.mickael.mynews.models.ApiResponse
import com.hernandez.mickael.mynews.models.Article
import com.hernandez.mickael.mynews.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class MostPopularFragment : ListFragment() {

    val LOG_TAG = "DebugTag"
    var url = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json"
    private lateinit var mList : ListView
    private lateinit var mAdapter : ArticleViewAdapter
    private var mArray : ArrayList<Result> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_topstories, container, false)

        mList = view.findViewById<ListView>(android.R.id.list)

        mAdapter = ArticleViewAdapter(context, R.layout.article_row, mArray)
        mList.adapter = mAdapter

        var apiService = ApiServiceSingleton.getInstance()
        apiService.mostPopular().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                mAdapter.addAll(response?.body()?.results)
                mAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                Log.d(LOG_TAG, "MOSTPOPULAR API CALL FAILED")
            }

        })
        return view
    }

    /*private fun query(q:String, fq: String?, beginDate: String?, endDate: String?, sort: String?, page: Int?, pAdapter: ArticleViewAdapter) {
        //mProgressDialog.show()
        val call = ApiServiceSingleton.getInstance().query(q, fq, beginDate, endDate, sort, page)
        call.enqueue(object: Callback<ApiResponse> {
            override fun onResponse(call:Call<ApiResponse>, response:Response<ApiResponse>) {
                // API rate limits: 1000 requests per day, 1 request per second (check X-RateLimit
                // fields in HTTP response).
                if (response.code() === 429)
                {
                    Log.v(LOG_TAG, response.code().toString() + ": rate limit exceeded")
                    return
                }
                try
                {
                    val articles = response.body()?.articles
                    Log.d(LOG_TAG, articles.toString())
                    if (articles!!.isEmpty())
                    {Log.v(LOG_TAG, response.code().toString() + "Empty response")}
                    else
                        pAdapter.addAll(articles)//Util.toastLong(mActivity, getString(R.string.toast_no_results));
                }
                catch (e:NullPointerException) {
                    fail(e)
                }
                //mProgressDialog.dismiss()
            }
            override fun onFailure(call:Call<ApiResponse>, t:Throwable) {
                //mProgressDialog.dismiss()
                fail(t)
            }
            private fun fail(t:Throwable) {
                // TODO: check for SocketTimeoutException (if connection is slow)
                // TODO: check for UnknownHostException (if there is no Internet connection)
                //Util.toastLong(mActivity, "Query failed: " + t.javaClass.getSimpleName())
                t.printStackTrace()
            }
        })
    }*/
}
