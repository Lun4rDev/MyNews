package com.hernandez.mickael.mynews.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.hernandez.mickael.mynews.R
import kotlinx.android.synthetic.main.activity_help.*

/**
 * Created by Mickael Hernandez on 10/01/2018.
 */
class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        for(s in resources.getStringArray(R.array.string_array_help)){
            var tv = TextView(this)
            tv.text = s
            tv.textSize = 17f
            //tv.gravity = Gravity.CENTER_HORIZONTAL
            tv.setPadding(0, 120, 0, 0)
            layout_help.addView(tv)
        }
    }
}