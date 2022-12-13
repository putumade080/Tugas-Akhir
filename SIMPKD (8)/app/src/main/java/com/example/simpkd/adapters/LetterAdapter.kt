package com.example.simpkd.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.viewHolders.LetterViewHolder
import com.example.simpkd.viewModels.LetterViewModel

class LetterAdapter(
    private val context: Context,
    private val items: List<SuratTanggungJawab>,
    private val viewModel: LetterViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.Adapter<LetterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_letter_news_item, parent,
            false)
        return LetterViewHolder(view, viewModel, detailLauncher)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}