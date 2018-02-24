package com.hernandez.mickael.mynews.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.widget.CheckBox

import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.activities.MainActivity
import com.hernandez.mickael.mynews.activities.ResultActivity
import com.hernandez.mickael.mynews.api.ApiSingleton
import com.hernandez.mickael.mynews.enums.NotifSingleton
import com.hernandez.mickael.mynews.enums.Section
import com.hernandez.mickael.mynews.models.search.Doc
import com.hernandez.mickael.mynews.models.search.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/** Receiver for the notification */
class AlarmReceiver : BroadcastReceiver() {

    /** Custom date format for api */
    var apiFormat = SimpleDateFormat("yyyyMMdd")

    override fun onReceive(context: Context, intent: Intent) {
        val apiService = ApiSingleton.getInstance()
        var sum = 0
        var sections = "news_desk:("
        for(section in NotifSingleton.sections) {
            if(section != ""){
                sections += section + "\" "
            }
        }
        sections += ")"
        if(sections != "news_desk:()"){
            // Result activity intent
            apiService.articleSearch(
                    NotifSingleton.query, // Query string
                    apiFormat.format(GregorianCalendar(1851, 9, 18).timeInMillis), // Begin date
                    apiFormat.format(Calendar.getInstance().timeInMillis), // End date
                    sections) // Sections formatted
                    .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                    //Log.d(LOG_TAG, response?.errorBody().toString())
                    if(response?.body()?.searchSubResponse?.docs != null){
                        var resArray = response.body()!!.searchSubResponse.docs
                        resArray.sortByDescending { it.pubDate }
                        resArray.add(0, Doc())
                        resArray[0].id = "test"
                        var i = 0
                        while(i < resArray.size && resArray[i].id != NotifSingleton.lastDocId){
                            i++
                        }
                        sum = i+1
                        NotifSingleton.lastDocId = resArray[0].id
                        NotifSingleton.saveData(context)

                        // Intent opened by the click on notification
                        val pI = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)

                        // Building the notification
                        val mBuilder = NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
                                .setContentTitle(context.getString(R.string.app_name))
                                .setContentText(context.getString(R.string.notification_text, sum, NotifSingleton.sections.size))
                                .setContentIntent(pI)
                                .setAutoCancel(true) // Removes the notification when clicked

                        // Gets instance of NotificationManager service
                        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                        // Notifies
                        mNotificationManager.notify(0, mBuilder.build())
                    }
                    //Log.d("TABSIZE", mArray.size.toString())
                }
                override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                    //Log.d(LOG_TAG, "MOSTPOPULAR API CALL FAILED : ")
                    t?.printStackTrace()
                }

                    })
        } else {
            // No section checked
        }
    }

    companion object {

        private val DEBUG_TAG = "AlarmReceiver"
    }

}