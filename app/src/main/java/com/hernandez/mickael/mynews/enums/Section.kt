package com.hernandez.mickael.mynews.enums

/**
 * Created by Mickael Hernandez on 12/11/2017.
 */

/** Enumeration of the available sections in-app */
enum class Section(val id : Int, val serialized : String) {
    Arts(0, "arts"),
    Politics(1, "politics"),
    Business(2, "business"),
    Sports(3, "sports"),
    Entrepreneurs(4, "entrepreneurs"),
    Travel(5, "travel");
}