package com.example.simpkd.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.models.Kendaraan
import kotlinx.android.synthetic.main.layout_detail_item_vehicle.view.*

class DetailVehicleViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {
    fun onBindItem(vehicle: Kendaraan) {
        val vehicleType = "Roda ${vehicle.model.jumlah_roda}"

        view.vehicle_registration_number.text = vehicle.no_polisi
        view.vehicle_brand.text = vehicle.model.merk
        view.vehicle_type.text = vehicleType
        view.vehicle_color.text = vehicle.warna
    }
}