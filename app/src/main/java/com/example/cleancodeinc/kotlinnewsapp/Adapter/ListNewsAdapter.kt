package com.example.cleancodeinc.kotlinnewsapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.cleancodeinc.kotlinnewsapp.Adapter.ViewHolder.ListHolderViewHolder
import com.example.cleancodeinc.kotlinnewsapp.Adapter.ViewHolder.ListNewsViewHolder
import com.example.cleancodeinc.kotlinnewsapp.Model.Article
import com.example.cleancodeinc.kotlinnewsapp.R
import com.squareup.picasso.Picasso
import java.util.*
import java.util.zip.Inflater

class ListNewsAdapter(val articleList: List<Article>, private val context: Context) : RecyclerView.Adapter<ListNewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListNewsViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val itemView = inflater.inflate(R.layout.news_layout, parent, false)


        return ListNewsViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ListNewsViewHolder?, position: Int) {

        // Load image

        Picasso.get().load(articleList[position].urlToImage).into(holder!!.article_image)

        if (articleList[position].title!!.length > 65) {
            holder.article_title.text = articleList[position].title!!.substring(0, 65) + "..."

        } else {
            holder.article_title.text = articleList[position].title!!
        }


        if (articleList[position].publishedAt != null) {
            var date: Date? = null

        }

    }


}