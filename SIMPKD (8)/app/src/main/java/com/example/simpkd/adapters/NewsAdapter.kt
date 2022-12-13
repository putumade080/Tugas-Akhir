package com.example.simpkd.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.BeritaAcara
import com.example.simpkd.viewHolders.NewsViewHolder
import com.example.simpkd.viewModels.NewsViewModel

class NewsAdapter(
    private val context: Context,
    private val items: List<BeritaAcara>,
    private val viewModel: NewsViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_letter_news_item, parent,
            false)
        return NewsViewHolder(view, viewModel, detailLauncher)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}