package com.hernandez.mickael.mynews.fragments

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.hernandez.mickael.mynews.R


/**
 * Created by Mickaël Hernandez on 25/10/2017.
 */
class MostPopularFragment : ListFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_topstories, container, false)
    }
}