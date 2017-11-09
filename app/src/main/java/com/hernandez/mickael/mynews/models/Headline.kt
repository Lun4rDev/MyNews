package com.hernandez.mickael.mynews.models

/**
 * Created by Mickael Hernandez on 07/11/2017.
 */
class Headline {

    private var main: String? = null

    private var printHeadline: String? = null

    fun getMain(): String? {
        return main
    }

    fun setMain(main: String) {
        this.main = main
    }

    fun getPrintHeadline(): String? {
        return printHeadline
    }

    fun setPrintHeadline(printHeadline: String) {
        this.printHeadline = printHeadline
    }
}