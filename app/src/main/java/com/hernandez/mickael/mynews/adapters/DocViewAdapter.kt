package com.hernandez.mickael.mynews.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.models.main.Article
import com.hernandez.mickael.mynews.models.search.Doc
import com.squareup.picasso.Picasso


/**
 * Created by Mickael Hernandez on 09/11/2017.
 */

/** Convert each doc item into a row */
open class DocViewAdapter(context: Context, resource: Int, list: ArrayList<Doc>) :
        ArrayAdapter<Doc>(context, resource, list) {

    /** prefix of the images url */
    private val urlPrefix = "https://static01.nyt.com/"

    override fun getView(position: Int, originalView: View?, container: ViewGroup?): View {
        val convertView : View = originalView ?: LayoutInflater.from(context).inflate(R.layout.article_row, container, false)
        val item = getItem(position)

        // Formatting the published date
        val publishedDate = item.pubDate.substring(8, 10) + "/" + item.pubDate.substring(5, 7) + "/" + item.pubDate.substring(0, 4)

        // Section name empty by default
        var section = context.getString(R.string.nocategory)

        if(item.section != null /*&& item.section != "null"*/){
            section = item.section
        }
        // Additional category
        if(item.newDesk != null && item.newDesk != "None") {
            if(section != context.getString(R.string.nocategory)) {
                section += " > " + item.newDesk
            } else {
                section = item.newDesk
            }
        }

        // Setting the section (category) in the row layout
        convertView.findViewById<TextView>(R.id.section_text).text = section

        // Setting the headline
        convertView.findViewById<TextView>(R.id.title_text).text = item.headline.main.toString()

        // Setting the published date
        convertView.findViewById<TextView>(R.id.date_text).text = publishedDate

        if(item.multimedia != null && item.multimedia is ArrayList && item.multimedia.isNotEmpty()){
            /*val url : String = if(item.multimedia[0].url != null && item.media[0].url != ""){
                item.multimedia[0].url
            } else {
                item.multimedia[0].mediaMetadata[0].url
            }*/
            Picasso.with(context).load(urlPrefix + item.multimedia[0].url).into(convertView.findViewById<ImageView>(R.id.article_img))
        }
        return convertView
    }
}
