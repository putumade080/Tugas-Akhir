package com.example.simpkd.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.viewHolders.VehicleMaintenanceViewHolder
import com.example.simpkd.viewModels.VehicleMaintenanceViewModel

class VehicleMaintenanceAdapter(
    private val context: Context,
    private val items: List<PemeliharaanKendaraan>,
    private val viewModel: VehicleMaintenanceViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.Adapter<VehicleMaintenanceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleMaintenanceViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_letter_news_item, parent,
            false)
        return VehicleMaintenanceViewHolder(view, viewModel, detailLauncher)
    }

    override fun onBindViewHolder(holder: VehicleMaintenanceViewHolder, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}