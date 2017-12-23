package com.hernandez.mickael.mynews.adapters

import android.content.Context
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

open class DocViewAdapter(context: Context, resource: Int, list: ArrayList<Doc>) :
        ArrayAdapter<Doc>(context, resource, list) {

    override fun getView(position: Int, originalView: View?, container: ViewGroup?): View {
        val convertView : View = originalView ?: LayoutInflater.from(context).inflate(R.layout.article_row, container, false)
        val item = getItem(position)
        val publishedDate = item.pubDate.substring(8, 10) + "/" + item.pubDate.substring(5, 7) + "/" + item.pubDate.substring(0, 4)

        if(item.newDesk != null && item.newDesk != "") {
            var section = item.newDesk.toString()
            convertView.findViewById<TextView>(R.id.section_text).text = section
        }
        convertView.findViewById<TextView>(R.id.title_text).text = item.headline.main.toString()
        convertView.findViewById<TextView>(R.id.date_text).text = publishedDate

        if(item.multimedia != null && item.multimedia is ArrayList && item.multimedia.isNotEmpty()){
            /*val url : String = if(item.multimedia[0].url != null && item.media[0].url != ""){
                item.multimedia[0].url
            } else {
                item.multimedia[0].mediaMetadata[0].url
            }*/

            Picasso.with(context).load(item.multimedia[0].url).into(convertView.findViewById<ImageView>(R.id.article_img))
        }
        return convertView
    }
}
