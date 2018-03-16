package com.hernandez.mickael.mynews.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.NotifSingleton
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.AppCompatCheckBox
import android.view.View.OnClickListener
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Mickael Hernandez on 16/11/2017.
 */
class SearchActivity : AppCompatActivity() {
    /** Cusstom date format for display */
    var displayFormat = SimpleDateFormat("dd/MM/yyyy")

    /** Cusstom date format for api */
    var apiFormat = SimpleDateFormat("yyyyMMdd")

    /** checkboxes layout */
    lateinit var mCheckLayout : GridLayout

    /** begin date textView */
    lateinit var beginText : TextView

    /** end date textView */
    lateinit var endText : TextView

    /** begin date initialized as first NYT publication */
    var beginDate = GregorianCalendar(1851, 9, 18)

    /** end date initialized as today*/
    var endDate = Calendar.getInstance() // Today

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Initializing class members
        mCheckLayout = findViewById(R.id.search_checkboxes)
        beginText = findViewById(R.id.search_begin_date)
        endText = findViewById(R.id.search_end_date)

        // Search query field
        val textView = findViewById<EditText>(R.id.search_query)

        // Filling the checkbox layout
        for(section in Section.values()){
            val cb = CheckBox(this)
            cb.text = section.name
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val m = 25
            params.setMargins(m, m, m, m)
            mCheckLayout.addView(cb, params)
        }

        // Checking the checkboxes saved in shared preferences
        for(section in NotifSingleton.sections){
            val cb = mCheckLayout.getChildAt(Section.valueOf(section).id) as CheckBox
            cb.isChecked = true
        }

        // Begin and end textViews click listener
        val listener = OnClickListener { view: View ->
            // Create date picker dialog
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
                mDatePicker.setTitle("Select date") // set picker title
                mDatePicker.show() // show picker
        }
        beginText.setOnClickListener(listener)
        endText.setOnClickListener(listener)

        // Search button click listener
        findViewById<Button>(R.id.search_button).setOnClickListener{
            var sections = "news_desk:("
            for(i in 0..mCheckLayout.childCount) {
                if(mCheckLayout.getChildAt(i) != null){
                    val cb = mCheckLayout.getChildAt(i) as CheckBox
                    if(cb.isChecked){
                        sections += "\"" + Section.values()[i].serialized + "\" "
                    }
                }
            }
            sections += ")"
            if(textView.text.toString() != "" && sections != "news_desk:()"){
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