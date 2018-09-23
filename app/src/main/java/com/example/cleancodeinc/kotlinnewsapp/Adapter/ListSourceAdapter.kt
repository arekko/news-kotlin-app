package com.example.cleancodeinc.kotlinnewsapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cleancodeinc.kotlinnewsapp.Adapter.ViewHolder.ListHolderViewHolder
import com.example.cleancodeinc.kotlinnewsapp.Interface.ItemClickListener
import com.example.cleancodeinc.kotlinnewsapp.Model.WebSite
import com.example.cleancodeinc.kotlinnewsapp.R
import kotlinx.android.synthetic.main.source_news_layout.view.*

class ListSourceAdapter(val context: Context, val webSite: WebSite) : RecyclerView.Adapter<ListHolderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListHolderViewHolder {
        // Setting up the layout
        val inflater = LayoutInflater.from(parent!!.context)
        val itenView = inflater.inflate(R.layout.source_news_layout, parent, false)

        return ListHolderViewHolder(itenView)

    }

    override fun getItemCount(): Int {
        return webSite.sources!!.size  // In our case is return length of list items
    }

    override fun onBindViewHolder(holder: ListHolderViewHolder?, position: Int) {

        holder!!.source_title.text = webSite.sources!![position].name


        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context, "Will be implemented in next tutorial", Toast.LENGTH_SHORT).show()
            }

        })
    }


}