package com.example.simpkd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.Pegawai
import com.example.simpkd.viewHolders.DetailTaskReceiverViewHolder

class DetailTaskReceiverAdapter(
    private val context: Context,
    private val items: List<Pegawai>
): RecyclerView.Adapter<DetailTaskReceiverViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailTaskReceiverViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_detail_item_task_receiver,
            parent, false)
        return DetailTaskReceiverViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailTaskReceiverViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}