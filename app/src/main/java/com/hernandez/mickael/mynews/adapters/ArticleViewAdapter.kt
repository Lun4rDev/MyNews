package com.hernandez.mickael.mynews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.models.main.Article
import com.squareup.picasso.Picasso


/**
 * Created by Mickael Hernandez on 09/11/2017.
 */

/** Article row adapter */
open class ArticleViewAdapter(context: Context, resource: Int, list: ArrayList<Article>) :
        ArrayAdapter<Article>(context, resource, list) {

    override fun getView(position: Int, originalView: View?, container: ViewGroup?): View {
        val convertView : View = originalView ?: LayoutInflater.from(context).inflate(R.layout.article_row, container, false)

        // The article
        val item = getItem(position)

        // Formatted string of the published date
        val publishedDate = item.publishedDate.substring(8, 10) + "/" + item.publishedDate.substring(5, 7) + "/" + item.publishedDate.substring(0, 4)

        var section = item.section

        // Adding the subsection to the section if it exists
        if(item.subsection != null && item.subsection != "") {
            section += " > " + item.subsection
        }

        // Applying values to the UI elements
        convertView.findViewById<TextView>(R.id.section_text).text = section
        convertView.findViewById<TextView>(R.id.title_text).text = item.title.toString()
        convertView.findViewById<TextView>(R.id.date_text).text = publishedDate

        // If there's an image
        if(item.media != null && item.media is ArrayList && item.media.isNotEmpty()){
                val url : String = if(item.media[0].url != null && item.media[0].url != ""){
                    item.media[0].url
                } else {
                    item.media[0].mediaMetadata[0].url
                }
            if(url != ""){
                // Download image into the view
                Picasso.with(context).load(url).into(convertView.findViewById<ImageView>(R.id.article_img))
            }
        }
        return convertView
    }
}
