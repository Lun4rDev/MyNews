package com.hernandez.mickael.mynews.enums

import android.content.Context
import com.hernandez.mickael.mynews.R

/**
 * Created by Mickael Hernandez on 19/11/2017.
 */

/** Singleton set that holds and saves the checked sections, notification query and state into the app shared preferences **/
object NotifSingleton {

    /** Unique set that holds the checked sections **/
    var sections = HashSet<String>()

    /** State of the notification switch **/
    var state = false

    /** Query string */
    var query = ""

    /** Last article ID */
    var lastDocId = ""

    /** SharedPreferences keys */
    val KEY_SECTIONS = "sections"
    val KEY_NOTIFICATION_STATE = "notifstate"
    val KEY_QUERY = "query"
    val KEY_LAST_DOC_ID = "lastDocId"

    /** Retrieves and populates the sections set from shared preferences to this class **/
    fun loadData(ctx : Context){
        if(sections.isEmpty()){
            sections.clear()
            sections = ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).getStringSet(KEY_SECTIONS, HashSet<String>()) as HashSet<String>
            state = ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).getBoolean(KEY_NOTIFICATION_STATE, false)
            query = ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).getString(KEY_QUERY, "")
            lastDocId = ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).getString(KEY_LAST_DOC_ID, "")
        }
    }

    /** Saves the sections set to the shared preferences **/
    fun saveData(ctx : Context) {
        ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).edit().clear()
                .putStringSet(KEY_SECTIONS, sections)
                .putBoolean(KEY_NOTIFICATION_STATE, state)
                .putString(KEY_QUERY, query)
                .putString(KEY_LAST_DOC_ID, lastDocId)
                .apply()
    }

    fun addFavoriteSection(section : String, ctx : Context){
        sections.add(section)
        saveData(ctx)
    }
    fun removeFavoriteSection(section : String, ctx : Context){
        sections.remove(section)
        saveData(ctx)
    }
}