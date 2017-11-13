package com.hernandez.mickael.mynews.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.models.Article
import com.hernandez.mickael.mynews.models.Result
import java.net.URI
import java.util.zip.Inflater


/**
 * Created by Mickael Hernandez on 09/11/2017.
 */
open class ArticleViewAdapter(context: Context, resource: Int, list: ArrayList<Result>) :
        ArrayAdapter<Result>(context, resource, list) {

    override fun getView(position: Int, originalView: View?, container: ViewGroup?): View {
        val convertView : View = originalView ?: LayoutInflater.from(context).inflate(R.layout.article_row, container, false)
        convertView.findViewById<TextView>(R.id.section_text).text = getItem(position).section.toString()
        convertView.findViewById<TextView>(R.id.title_text).text = getItem(position).title.toString()
        convertView.findViewById<TextView>(R.id.date_text).text = getItem(position).publishedDate.toString()
        convertView.findViewById<ImageView>(R.id.article_img).setImageURI(Uri.parse(getItem(position).media[0].mediaMetadata[0].url))
        return convertView
    }

}

private fun TextView.setText(item: String) {

}
