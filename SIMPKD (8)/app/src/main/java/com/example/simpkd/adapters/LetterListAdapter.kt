package com.example.simpkd.adapters

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.viewHolders.LetterListViewHolder
import com.example.simpkd.viewModels.MainViewModel

class LetterListAdapter(
    private val context: Context,
    private val items: List<Parcelable>,
    private val viewModel: MainViewModel,
    private val newsLauncher: ActivityResultLauncher<Intent>?,
    private val letterLauncher: ActivityResultLauncher<Intent>?,
    private val vehicleMaintenanceLauncher: ActivityResultLauncher<Intent>?,
    private val letterOfTaskLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.Adapter<LetterListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_letter_news_item, parent,
            false)
        return LetterListViewHolder(view, viewModel, newsLauncher, letterLauncher,
            vehicleMaintenanceLauncher, letterOfTaskLauncher)
    }

    override fun onBindViewHolder(holder: LetterListViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}