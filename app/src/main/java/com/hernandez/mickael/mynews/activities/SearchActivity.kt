package com.hernandez.mickael.mynews.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.fragments.DatePickerFragment
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton
import android.app.DatePickerDialog
import android.app.PendingIntent.getActivity
import java.util.*


/**
 * Created by Mickael Hernandez on 16/11/2017.
 */
class SearchActivity : AppCompatActivity() {

    lateinit var mCheckLayout : GridLayout
    var beginFragment = DatePickerFragment()
    var endFragment = DatePickerFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        mCheckLayout = findViewById(R.id.search_checkboxes)

        val textView = findViewById<TextView>(R.id.search_query)

        // Filling the checkbox layout
        for(section in Section.values()){
            val cb = CheckBox(applicationContext)
            cb.text = section.name
            // TODO : set gridlayout column size
            mCheckLayout.addView(cb)
        }

        // Checking the checkboxes saved in shared preferences
        for(section in SectionSingleton.sections){
            val cb = mCheckLayout.getChildAt(Section.valueOf(section).id) as CheckBox
            cb.isChecked = true
        }

        // Search button click listener
        findViewById<Button>(R.id.search_button).setOnClickListener{
            var sections : String = "news_desk:("
            for(i in 0..mCheckLayout.childCount) {
                if(mCheckLayout.getChildAt(i) != null){
                    val cb = mCheckLayout.getChildAt(i) as CheckBox
                    if(cb.isChecked){
                        sections += "\"" + Section.values()[i].serialized + "\" "
                    }
                }
            }
            sections += ")"

            if(textView.text.toString() != "" && sections != "" && beginFragment.date != "000" && endFragment.date != "000"){
                // textView.text.toString(), beginFragment.date, endFragment.date, res

                // Result activity intent
                var intent = Intent(baseContext, ResultActivity::class.java)
                val valArray = arrayOf<String>(textView.text.toString(), beginFragment.date, endFragment.date, sections)
                intent.putExtra("values", valArray)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, getString(R.string.missing_parameters), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showBeginDatePickerDialog(v: View) {
        beginFragment.show(supportFragmentManager, "datePicker")
    }
    fun showEndDatePickerDialog(v: View) {
        endFragment.show(supportFragmentManager, "datePicker")
    }
}