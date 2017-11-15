package com.hernandez.mickael.mynews.adapters;

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hernandez.mickael.mynews.fragments.MostPopularFragment

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
        /*when (position) {
            0 // Fragment # 0 - This will show FirstFragment
            -> return MostPopularFragment.newInstance(0, "Page # 1")
            1 // Fragment # 0 - This will show FirstFragment different title
            -> return FirstFragment.newInstance(1, "Page # 2")
            2 // Fragment # 1 - This will show SecondFragment
            -> return SecondFragment.newInstance(2, "Page # 3")
            else -> return null
        }*/
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }
}