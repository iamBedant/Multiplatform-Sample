package com.iambedant.multiplatformsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import data.NewsArticle
import kotlinx.android.synthetic.main.rv_item_news.view.*

/**
 * Created by @iamBedant on 01,May,2019
 */
class NewsAdapter(private var dataSource: List<NewsArticle>, private val itemClick:(NewsArticle)->Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_news, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(dataSource[position]){
            holder.title.text = title
            holder.description.text = description
            holder.time.text = publishedAt
            holder.source.text = source
            Glide.with(holder.coverImage.context)
                .load(urlToImage)
                .into(holder.coverImage)
            holder.bookmark.setOnClickListener { itemClick.invoke(this) }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    fun addItems(it: List<NewsArticle>) {
        //Todo: Add diffutils
        dataSource = it
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.tvTitle
    val coverImage= view.ivCover
    val time = view.tvTime
    val description= view.tvDescription
    val source = view.tvSource
    val bookmark = view.ibBookmark
}