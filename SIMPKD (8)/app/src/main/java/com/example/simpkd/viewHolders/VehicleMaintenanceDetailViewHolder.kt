package com.example.simpkd.viewHolders

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.activities.FormVehicleMaintenanceActivity
import com.example.simpkd.models.PemeliharaanKendaraanDetail
import com.example.simpkd.utils.toPx
import com.example.simpkd.viewModels.VehicleMaintenanceDetailViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.layout_detail_item_vehicle_maintenance.view.*

class VehicleMaintenanceDetailViewHolder(
    private val view: View,
    private val viewModel: VehicleMaintenanceDetailViewModel,
    private val updateLauncher: ActivityResultLauncher<Intent>,
    private val isEditable: Boolean
): RecyclerView.ViewHolder(view) {
    fun onBindItem(item: PemeliharaanKendaraanDetail, position: Int) {
        val vehicleTotalWheel = "Roda ${item.kendaraan.model.jumlah_roda}"
        view.plate_number.text = item.kendaraan.no_polisi
        view.vehicle_model.text = item.kendaraan.model.merk
        view.vehicle_total_wheel.text = vehicleTotalWheel
        view.problem_types.setChipSpacing((8).toPx())
        view.description.text = item.keterangan
        view.btn_delete.visibility = if (position == 0 || !isEditable) View.GONE else View.VISIBLE
        view.container.isClickable = isEditable
        view.container.isFocusable = isEditable

        for (problemType in item.jenis_kerusakan.split(", ")) {
            val chip = Chip(view.context).apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT,
                    ViewGroup.MarginLayoutParams.WRAP_CONTENT).apply {
                        setMargins(0, (16).toPx(), 0, (16).toPx())
                }
                text = problemType
            }
            view.problem_types.addView(chip)
        }

        view.container.setOnClickListener {
            if (!isEditable) return@setOnClickListener
            val intent = Intent(view.context, FormVehicleMaintenanceActivity::class.java)
            intent.putExtra("vehicle_details",
                arrayListOf(*viewModel.vehicleDetails.value!!.toTypedArray()))
            intent.putExtra("vehicle_detail", item)
            intent.putExtra("vehicle_detail_position", position)
            updateLauncher.launch(intent)
        }
        view.btn_delete.setOnClickListener {
            if (viewModel.vehicleDetails.value!!.size > 1) {
                viewModel.vehicleDetails.value = viewModel.vehicleDetails.value!!.filter {
                    it.kendaraan.id != item.kendaraan.id
                }
            }
        }
    }
}