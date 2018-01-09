package com.hernandez.mickael.mynews.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.SectionSingleton
import com.hernandez.mickael.mynews.receiver.AlarmReceiver
import java.util.*


/**
 * Created by Mickael Hernandez on 12/11/2017.
 */
class NotificationActivity : AppCompatActivity() {

    lateinit var mSharedPrefs : SharedPreferences
    lateinit var mSwitch : Switch
    lateinit var mCheckLayout : GridLayout
    lateinit var alarms : AlarmManager
    lateinit var recurringNotif : PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // UI elements
        mSwitch = findViewById(R.id.notif_switch)
        mCheckLayout = findViewById(R.id.notif_checkboxes)

        // Shared preferences
        mSharedPrefs = application.getSharedPreferences(getString(R.string.app_name), android.content.Context.MODE_PRIVATE)

        // Get instance of AlarmManager
        alarms = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Set switch state according to shared preferences
        mSwitch.isChecked = SectionSingleton.state

        // Creates pending intent for notification if enabled
        if(mSwitch.isChecked) setRecurringAlarm(this)

        // Switch check listener
        mSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(fun(_: CompoundButton, state : Boolean) {

            SectionSingleton.state = state
            SectionSingleton.saveSections(applicationContext)
            if(state){
                setRecurringAlarm(this)
                //mNotificationManager.notify(0, mBuilder.build())
            } else {
                alarms.cancel(recurringNotif)
            }
        }))
        //SectionSingleton.loadSections(applicationContext)*

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
            val cb = CheckBox(applicationContext)
            cb.text = section.name
            cb.tag = section.id
            cb.setOnCheckedChangeListener(listener)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val m = 25
            params.setMargins(m, m, m, m)
            mCheckLayout.addView(cb, params)
        }

        // Checking the checkboxes saved in shared preferences
        for(section in SectionSingleton.sections){
            val cb = mCheckLayout.getChildAt(Section.valueOf(section).id) as CheckBox
            cb.isChecked = true
        }
    }

    private fun setRecurringAlarm(context: Context) {
        val updateTime = Calendar.getInstance()
        updateTime.timeZone = TimeZone.getTimeZone("GMT")
        updateTime.set(Calendar.HOUR_OF_DAY, 11)
        updateTime.set(Calendar.MINUTE, 45)

        val intent = Intent(context, AlarmReceiver::class.java)
        recurringNotif = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                updateTime.timeInMillis,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, recurringNotif)
    }
}