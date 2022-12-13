package com.example.simpkd.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.models.PemeliharaanKendaraanDetail
import com.example.simpkd.viewHolders.VehicleMaintenanceDetailViewHolder
import com.example.simpkd.viewModels.VehicleMaintenanceDetailViewModel

class VehicleMaintenanceDetailAdapter(
    private val context: Context,
    private val items: List<PemeliharaanKendaraanDetail>,
    private val viewModel: VehicleMaintenanceDetailViewModel,
    private val updateLauncher: ActivityResultLauncher<Intent>,
    private val isEditable: Boolean
): RecyclerView.Adapter<VehicleMaintenanceDetailViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehicleMaintenanceDetailViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_detail_item_vehicle_maintenance, parent, false)
        return VehicleMaintenanceDetailViewHolder(view, viewModel, updateLauncher, isEditable)
    }

    override fun onBindViewHolder(holder: VehicleMaintenanceDetailViewHolder, position: Int) {
        holder.onBindItem(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}