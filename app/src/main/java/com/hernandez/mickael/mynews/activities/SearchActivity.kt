package com.hernandez.mickael.mynews.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.PendingIntent.getActivity
import android.content.DialogInterface
import android.util.Log
import android.view.View.OnClickListener
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Mickael Hernandez on 16/11/2017.
 */
class SearchActivity : AppCompatActivity() {
    var displayFormat = SimpleDateFormat("dd/MM/yyyy")
    var apiFormat = SimpleDateFormat("yyyyMMdd")

    lateinit var mCheckLayout : GridLayout

    lateinit var beginText : TextView
    lateinit var endText : TextView

    var beginDate = GregorianCalendar(1851, 9, 18) // first NYT publication
    var endDate = Calendar.getInstance() // Today

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        mCheckLayout = findViewById(R.id.search_checkboxes)

        val textView = findViewById<TextView>(R.id.search_query)
        beginText = findViewById(R.id.search_begin_date)
        endText = findViewById(R.id.search_end_date)

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

        var listener = OnClickListener { view: View ->

                val mDatePicker = DatePickerDialog(view.context,
                        OnDateSetListener { datepicker, year, month, day ->
                            // TODO Auto-generated method stub
                            /* Your code to get date and time */
                            if (datepicker.tag == "begin") {
                                beginDate = GregorianCalendar(year, month, day)
                                beginText.text = displayFormat.format(Date(beginDate.timeInMillis))
                            } else {
                                endDate = GregorianCalendar(year, month, day)
                                endText.text = displayFormat.format(Date(endDate.timeInMillis))
                            }
                        },
                        endDate.get(Calendar.YEAR),
                        endDate.get(Calendar.MONTH),
                        endDate.get(Calendar.DAY_OF_MONTH))
                if(view.tag == "begin"){
                    mDatePicker.datePicker.tag = "begin"
                } else {
                    mDatePicker.datePicker.tag = "end"
                }
                mDatePicker.setTitle("Select date")
                mDatePicker.show()
        }
        beginText.setOnClickListener(listener)
        endText.setOnClickListener(listener)

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
            if(textView.text.toString() != "" && sections != ""){
                // textView.text.toString(), beginFragment.date, endFragment.date, res

                // Result activity intent
                var intent = Intent(baseContext, ResultActivity::class.java)
                val valArray = arrayOf<String>(textView.text.toString(), apiFormat.format(beginDate.timeInMillis), apiFormat.format(endDate.timeInMillis), sections)
                intent.putExtra("values", valArray)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, getString(R.string.missing_parameters), Toast.LENGTH_LONG).show()
            }
        }
    }
}