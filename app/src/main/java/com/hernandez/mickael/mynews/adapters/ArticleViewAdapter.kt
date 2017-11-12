package com.hernandez.mickael.mynews.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.models.Article
import java.net.URI
import java.util.zip.Inflater


/**
 * Created by Mickael Hernandez on 09/11/2017.
 */
open class ArticleViewAdapter(context: Context, resource: Int, list: ArrayList<Article>) :
        ArrayAdapter<Article>(context, resource, list) {

    override fun getView(position: Int, originalView: View?, container: ViewGroup?): View {
        val convertView : View = originalView ?: LayoutInflater.from(context).inflate(R.layout.article_row, container, false)
        convertView.findViewById<TextView>(R.id.section_text).text = getItem(position).getMultimedia().toString()
        convertView.findViewById<TextView>(R.id.title_text).text = getItem(position).getHeadline().toString()
        convertView.findViewById<TextView>(R.id.date_text).text = getItem(position).getPublishedDate().toString()
        convertView.findViewById<ImageView>(R.id.article_img).setImageURI(Uri.parse(getItem(position).getUri()))
        return convertView
    }

}

private fun TextView.setText(item: String) {

}
