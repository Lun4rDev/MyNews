package com.hernandez.mickael.mynews.activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.api.ApiSingleton
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.enums.NotifSingleton
import com.hernandez.mickael.mynews.models.search.SearchResponse
import com.hernandez.mickael.mynews.receiver.AlarmReceiver
import kotlinx.android.synthetic.main.activity_notification.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Mickael Hernandez on 12/11/2017.
 */

/** Activity used to configure the recurring notification */
class NotificationActivity : AppCompatActivity() {

    /** Custom date format for api */
    var apiFormat = SimpleDateFormat("yyyyMMdd")

    /** Shared preferences instance */
    lateinit var mSharedPrefs : SharedPreferences

    /** Switch button to toggle the notification */
    lateinit var mSwitch : Switch

    /** Layout containing the sections checkboxes */
    lateinit var mCheckLayout : GridLayout

    /** Alarm manager instance */
    lateinit var alarms : AlarmManager

    /** Intent for the notification */
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
        mSwitch.isChecked = NotifSingleton.state
        notif_query.setText(NotifSingleton.query)

        // Creates pending intent for notification if enabled
        if(mSwitch.isChecked) setRecurringAlarm(this)

        // Switch check listener
        mSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener(fun(_: CompoundButton, state : Boolean) {
            if(state){
                setRecurringAlarm(this)
                //mNotificationManager.notify(0, mBuilder.build())
            } else {
                alarms.cancel(recurringNotif)
            }
        }))
        //NotifSingleton.loadSections(applicationContext)

        // Checkboxes listener
        val listener = CompoundButton.OnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
            if(b){
                NotifSingleton.addFavoriteSection(compoundButton.text.toString(), applicationContext)
            } else {
                NotifSingleton.removeFavoriteSection(compoundButton.text.toString(), applicationContext)
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
        for(section in NotifSingleton.sections){
            val cb = mCheckLayout.getChildAt(Section.valueOf(section).id) as CheckBox
            cb.isChecked = true
        }
    }

    override fun onPause() {
        super.onPause()
        NotifSingleton.query = notif_query.text.toString()
        NotifSingleton.saveData(applicationContext)
    }

    /** Sets the recurring alarm after gathering the appropriate data */
    private fun setRecurringAlarm(context: Context) {
        /*val updateTime = Calendar.getInstance()
        updateTime.timeZone = TimeZone.getTimeZone("GMT")
        updateTime.set(Calendar.HOUR_OF_DAY, 12)
        updateTime.set(Calendar.MINUTE, 13)*/
        var sections = "news_desk:("
        for(section in NotifSingleton.sections) {
            if(section != ""){
                sections += section + "\" "
            }
        }
        sections += ")"
        if(sections != "news_desk:()"){
            // Result activity intent
            ApiSingleton.getInstance().articleSearch(
                    NotifSingleton.query, // Query string
                    apiFormat.format(GregorianCalendar(1851, 9, 18).timeInMillis), // Begin date
                    apiFormat.format(Calendar.getInstance().timeInMillis), // End date
                    sections) // Sections formatted
                    .enqueue(object : Callback<SearchResponse> {
                        override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                            //Log.d(LOG_TAG, response?.errorBody().toString())
                            if(response?.body()?.searchSubResponse?.docs != null && response.body()?.searchSubResponse?.docs!!.size > 0){
                                //mArray.addAll(response.body()?.searchSubResponse!!.docs.asIterable())
                                NotifSingleton.lastDocId = response.body()?.searchSubResponse!!.docs[0].id
                            }
                            //Log.d("TABSIZE", mArray.size.toString())
                            // Alarm intent
                            val intent = Intent(context, AlarmReceiver::class.java)
                            recurringNotif = PendingIntent.getBroadcast(context,
                                    0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

                            // Repeats the alarm in an hour everyday
                            alarms.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, Calendar.getInstance().timeInMillis, AlarmManager.INTERVAL_DAY, recurringNotif)

                            NotificationCompat.Builder(context, "MyNews")
                            /*alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                                    updateTime.timeInMillis,
                                    AlarmManager.INTERVAL_FIFTEEN_MINUTES, recurringNotif)*/
                        }
                        override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                            //Log.d(LOG_TAG, "MOSTPOPULAR API CALL FAILED : ")
                            t?.printStackTrace()
                        }


                    })
        }

    }
}