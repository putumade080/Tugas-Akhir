package com.example.simpkd.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.viewHolders.LetterOfTaskViewHolder
import com.example.simpkd.viewModels.LetterOfTaskViewModel

class LetterOfTaskAdapter(
    private val context: Context,
    private val items: List<SuratPerintahTugas>,
    private val viewModel: LetterOfTaskViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.Adapter<LetterOfTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterOfTaskViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_letter_news_item, parent,
            false)
        return LetterOfTaskViewHolder(view, viewModel, detailLauncher)
    }

    override fun onBindViewHolder(holder: LetterOfTaskViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}