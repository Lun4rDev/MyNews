package com.hernandez.mickael.mynews.models

/**
 * Created by Mickael Hernandez on 07/11/2017.
 */
class Legacy {

    private var thumbnailheight: Int? = null

    private var thumbnail: String? = null

    private var thumbnailwidth: Int? = null

    private var xlargewidth: Int? = null

    private var xlarge: String? = null

    private var xlargeheight: Int? = null

    private var wide: String? = null

    private var widewidth: Int? = null

    private var wideheight: Int? = null

    fun getThumbnailheight(): Int? {
        return thumbnailheight
    }

    fun setThumbnailheight(thumbnailheight: Int?) {
        this.thumbnailheight = thumbnailheight
    }

    fun getThumbnail(): String? {
        return thumbnail
    }

    fun setThumbnail(thumbnail: String) {
        this.thumbnail = thumbnail
    }

    fun getThumbnailwidth(): Int? {
        return thumbnailwidth
    }

    fun setThumbnailwidth(thumbnailwidth: Int?) {
        this.thumbnailwidth = thumbnailwidth
    }

    fun getXlargewidth(): Int? {
        return xlargewidth
    }

    fun setXlargewidth(xlargewidth: Int?) {
        this.xlargewidth = xlargewidth
    }

    fun getXlarge(): String? {
        return xlarge
    }

    fun setXlarge(xlarge: String) {
        this.xlarge = xlarge
    }

    fun getXlargeheight(): Int? {
        return xlargeheight
    }

    fun setXlargeheight(xlargeheight: Int?) {
        this.xlargeheight = xlargeheight
    }

    fun getWide(): String? {
        return wide
    }

    fun setWide(wide: String) {
        this.wide = wide
    }

    fun getWidewidth(): Int? {
        return widewidth
    }

    fun setWidewidth(widewidth: Int?) {
        this.widewidth = widewidth
    }

    fun getWideheight(): Int? {
        return wideheight
    }

    fun setWideheight(wideheight: Int?) {
        this.wideheight = wideheight
    }
}