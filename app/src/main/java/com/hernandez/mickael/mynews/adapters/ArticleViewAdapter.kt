package com.hernandez.mickael.mynews.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.hernandez.mickael.mynews.R
import com.hernandez.mickael.mynews.models.Article


/**
 * Created by Mickael Hernandez on 09/11/2017.
 */
/*class ArticleViewAdapter : BaseAdapter() {

    var list: List<Result> = ArrayList()

    override fun getCount(): Int {
        return list.size
    }

    @Override
    override fun getItem(i: Int): Result {
        return list[i]
    }

    @Override
    override fun getItemId(i: Int): Long {
        return list[i].id
    }

    override fun getView(position: Int, view: View, viewGroup: ViewGroup): View {
        val convertView: View = LayoutInflater.from(view.context).inflate(R.layout.article_row, null)
        convertView.findViewById<TextView>(R.id.section_text).text = getItem(position).section.toString()
        convertView.findViewById<TextView>(R.id.title_text).text = getItem(position).title.toString()
        convertView.findViewById<TextView>(R.id.date_text).text = getItem(position).publishedDate.toString()
        return convertView
    }
}*/
open class ArticleViewAdapter(context: Context, resource: Int, list: ArrayList<Article>) :
        ArrayAdapter<Article>(context, resource, list) {

    override fun getView(position: Int, originalView: View?, container: ViewGroup?): View {
        val convertView : View = originalView ?: LayoutInflater.from(context).inflate(R.layout.article_row, container, false)
        convertView.findViewById<TextView>(R.id.section_text).text = getItem(position).section.toString()
        convertView.findViewById<TextView>(R.id.title_text).text = getItem(position).title.toString()
        convertView.findViewById<TextView>(R.id.date_text).text = getItem(position).publishedDate.toString().substring(0, 10)
        //convertView.findViewById<ImageView>(R.id.article_img).setImageURI(Uri.parse(getItem(position).media[0].mediaMetadata[0].url))
        return convertView
    }
}
