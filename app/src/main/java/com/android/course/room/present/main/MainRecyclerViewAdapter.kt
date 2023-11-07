package com.android.course.room.present.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.course.room.R
import com.android.course.room.domain.PreviewFilmInfo

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {

    var itemList: List<PreviewFilmInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recycler_view_item, parent, false)

        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MainViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val titleTextView: TextView by lazy { item.findViewById(R.id.main_recycler_item_title) }
        private val genreTextView: TextView by lazy { item.findViewById(R.id.main_recycler_item_genre) }

        fun bind(position: Int) {
            titleTextView.text = itemList[position].title
            genreTextView.text = itemList[position].genre
        }
    }

}
