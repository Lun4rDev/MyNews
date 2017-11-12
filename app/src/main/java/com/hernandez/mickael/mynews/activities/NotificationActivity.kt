package com.hernandez.mickael.mynews.activities

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.GridLayout
import android.widget.Switch
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.models.Section
import kotlinx.android.synthetic.main.activity_notification.*

/**
 * Created by Mickael Hernandez on 12/11/2017.
 */
class NotificationActivity : AppCompatActivity() {

    val KEY_NOTIFICATION_STATE = "NOTIFICATION_STATE"
    lateinit var mSharedPrefs : SharedPreferences
    lateinit var mSwitch : Switch
    lateinit var mCheckLayout : GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // UI elements
        mSwitch = findViewById(R.id.notification_switch)
        mCheckLayout = findViewById(R.id.notif_checkboxes)

        // Shared preferences
        mSharedPrefs = getSharedPreferences(getString(R.string.app_name), android.content.Context.MODE_PRIVATE)

        // Set switch state according to shared preferences
        mSwitch.isChecked = mSharedPrefs.getBoolean(KEY_NOTIFICATION_STATE, false)

        // Switch check listener
        mSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(fun(btn : CompoundButton, state : Boolean) {
            mSharedPrefs.edit().putBoolean(KEY_NOTIFICATION_STATE, state).apply()
            // TODO : Set up notifications
        }))
        for(section in Section.values()){
            var cb = CheckBox(applicationContext)
            cb.text = section.name
            // TODO : set gridlayout column size
            mCheckLayout.addView(cb)
        }
    }
}