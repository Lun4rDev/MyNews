package com.hernandez.mickael.mynews.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.GridLayout
import android.widget.Switch
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton

/**
 * Created by Mickael Hernandez on 12/11/2017.
 */
class NotificationActivity : AppCompatActivity() {

    lateinit var mSharedPrefs : SharedPreferences
    lateinit var mSwitch : Switch
    lateinit var mCheckLayout : GridLayout
    var arrayName = "sections"
    var array = BooleanArray(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // UI elements
        mSwitch = findViewById(R.id.notif_switch)
        mCheckLayout = findViewById(R.id.notif_checkboxes)

        // Shared preferences
        mSharedPrefs = application.getSharedPreferences(getString(R.string.app_name), android.content.Context.MODE_PRIVATE)

        // Set switch state according to shared preferences
        mSwitch.isChecked = SectionSingleton.state

        // Switch check listener
        mSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(fun(_: CompoundButton, state : Boolean) {
            SectionSingleton.state = state
            SectionSingleton.saveSections(applicationContext)
            // TODO : Set up notifications
        }))
        //SectionSingleton.loadSections(applicationContext)
        //SectionSingleton.loadSections(applicationContext)

        // Checkboxes listener
        val listener = CompoundButton.OnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(b){
                SectionSingleton.addFavoriteSection(compoundButton.text.toString(), applicationContext)
            } else {
                SectionSingleton.removeFavoriteSection(compoundButton.text.toString(), applicationContext)
            }
        }
        // Adding a checkbox for each section
        for(section in Section.values()){
            var cb = CheckBox(applicationContext)
            cb.text = section.name
            cb.tag = section.id
            cb.setOnCheckedChangeListener(listener)
            // TODO : set gridlayout column size
            mCheckLayout.addView(cb)
        }

        // Checking the checkboxes saved in shared preferences
        for(section in SectionSingleton.sections){
            val cb = mCheckLayout.getChildAt(Section.valueOf(section).id) as CheckBox
            cb.isChecked = true
        }
    }
}