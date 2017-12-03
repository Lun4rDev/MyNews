package com.hernandez.mickael.mynews.enums

import android.content.Context
import com.hernandez.mickael.mynews.R

/**
 * Created by Mickael Hernandez on 19/11/2017.
 */

/** Singleton set that holds and saves the checked sections into the app shared preferences **/
object SectionSingleton {

    /** Unique set that holds the checked sections **/
    var sections = HashSet<String>()

    /** State of the notification switch **/
    var state = false

    val KEY_SECTIONS = "sections"
    val KEY_NOTIFICATION_STATE = "notifstate"

    /** Retrieves and populates the sections set from shared preferences to this class **/
    fun loadSections(ctx : Context){
        if(sections.isEmpty()){
            sections.clear()
            sections = ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).getStringSet(KEY_SECTIONS, HashSet<String>()) as HashSet<String>
            state = ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).getBoolean(KEY_NOTIFICATION_STATE, false)
        }
    }

    /** Saves the sections set to the shared preferences **/
    fun saveSections(ctx : Context) {
        ctx.getSharedPreferences(ctx.getString(R.string.app_name), android.content.Context.MODE_PRIVATE).edit().clear().putStringSet(KEY_SECTIONS, sections).putBoolean(KEY_NOTIFICATION_STATE, state).apply()
    }

    fun addFavoriteSection(section : String, ctx : Context){
        sections.add(section)
        saveSections(ctx)
    }
    fun removeFavoriteSection(section : String, ctx : Context){
        sections.remove(section)
        saveSections(ctx)
    }
}