package com.example.simpkd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.Kendaraan
import com.example.simpkd.viewHolders.DetailVehicleViewHolder

class DetailVehicleAdapter(
    private val context: Context,
    private val items: List<Kendaraan>
): RecyclerView.Adapter<DetailVehicleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVehicleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_detail_item_vehicle, parent,
            false)
        return DetailVehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailVehicleViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}