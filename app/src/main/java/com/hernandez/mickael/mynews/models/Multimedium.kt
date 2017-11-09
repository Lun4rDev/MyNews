package com.hernandez.mickael.mynews.models

import android.text.TextUtils



/**
 * Created by Mickael Hernandez on 07/11/2017.
 */
class Multimedium {

    private var width: Int? = null

    private var url: String? = null

    private var rank: Int? = null

    private var height: Int? = null

    private var subtype: String? = null

    private var legacy: Legacy? = null

    private var type: String? = null

    fun getWidth(): Int? {
        return width
    }

    fun setWidth(width: Int?) {
        this.width = width
    }

    fun getUrl(): String? {
        return if (!TextUtils.isEmpty(url)) "http://www.nytimes.com/" + url!! else url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getRank(): Int? {
        return rank
    }

    fun setRank(rank: Int?) {
        this.rank = rank
    }

    fun getHeight(): Int? {
        return height
    }

    fun setHeight(height: Int?) {
        this.height = height
    }

    fun getSubtype(): String? {
        return subtype
    }

    fun setSubtype(subtype: String) {
        this.subtype = subtype
    }

    fun getLegacy(): Legacy? {
        return legacy
    }

    fun setLegacy(legacy: Legacy) {
        this.legacy = legacy
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }
}