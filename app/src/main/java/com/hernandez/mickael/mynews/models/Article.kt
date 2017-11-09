package com.hernandez.mickael.mynews.models

import android.graphics.drawable.Drawable
import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Mickael Hernandez on 07/11/2017.
 */
class Article {
    @SerializedName("headline")
    @Expose
    private var headline: Headline? = null

    @SerializedName("multimedia")
    @Expose
    private val multimedia: List<Multimedium>? = null

    @SerializedName("published_date")
    @Expose
    private var published_date: String? = null

    @SerializedName("web_url")
    @Expose
    private var webUrl: String? = null

    @SerializedName("image")
    @Expose
    private var image: String? = null


    fun getHeadline(): Headline? {
        return headline
    }

    fun setHeadline(headline: Headline) {
        this.headline = headline
    }

    fun getPublishedDate(): String? {
        return published_date
    }

    fun setPublishedDate(date: String) {
        this.headline = headline
    }

    fun getMultimedia(): List<Multimedium>? {
        return multimedia
    }

    fun getWebUrl(): String? {
        return webUrl
    }

    fun setWebUrl(webUrl: String) {
        this.webUrl = webUrl
    }

    fun getUri() : String? {
        return this.image
    }

    fun setImage(img: String?) {
        this.image = img
    }
}