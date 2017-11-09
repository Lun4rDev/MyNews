package com.hernandez.mickael.mynews.fragments

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hernandez.mickael.mynews.R

/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class TopStoriesFragment : ListFragment() {

    var url = "https://api.nytimes.com/svc/topstories/v2/home.json"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_topstories, container, false)
    }
}