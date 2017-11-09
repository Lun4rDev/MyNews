package com.hernandez.mickael.mynews.fragments

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.interfaces.MostPopularInterface
import com.hernandez.mickael.mynews.models.Article
import com.hernandez.mickael.mynews.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class MostPopularFragment : ListFragment() {

    var url = "https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/1.json"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_topstories, container, false)
    }
}