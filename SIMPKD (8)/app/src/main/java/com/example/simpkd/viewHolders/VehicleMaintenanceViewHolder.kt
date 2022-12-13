package com.example.simpkd.viewHolders

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.activities.VehicleMaintenanceDetailActivity
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.utils.toPx
import com.example.simpkd.viewModels.VehicleMaintenanceViewModel
import kotlinx.android.synthetic.main.layout_letter_news_item.view.*

class VehicleMaintenanceViewHolder(
    private val view: View,
    private val viewModel: VehicleMaintenanceViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.ViewHolder(view) {
    fun onBindItem(pemeliharaanKendaraan: PemeliharaanKendaraan) {
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")
        val status: Int = if (pemeliharaanKendaraan.status_kasubagTU == 0 ||
            pemeliharaanKendaraan.status_kepalaUPT == 0 || pemeliharaanKendaraan.status_pptk == 0) {
            0
        } else if (pemeliharaanKendaraan.status_kasubagTU == 2 ||
            pemeliharaanKendaraan.status_kepalaUPT == 2 || pemeliharaanKendaraan.status_pptk == 2) {
            2
        } else {
            1
        }

        view.date.text = pemeliharaanKendaraan.tanggal
        view.status.apply {
            text = statusText[status]
            setChipBackgroundColorResource(statusColors[status])
        }
        Glide.with(view).load(R.drawable.ic_car_repair_car_svgrepo_com).centerCrop().into(view.illustration)
        view.user.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                setMargins(0, 0, 0, (16).toPx())
        }
        view.user.text = pemeliharaanKendaraan.staf.nama
        view.letter_number.text = pemeliharaanKendaraan.nomor_surat
        view.plate_number.visibility = View.GONE
        view.vehicle_model.visibility = View.GONE

        view.container.setOnClickListener {
            val intent = Intent(view.context, VehicleMaintenanceDetailActivity::class.java)
            intent.putExtra("vehicle_maintenance", pemeliharaanKendaraan)
            intent.putExtra("me", viewModel.me.value!!)

            if (detailLauncher != null) {
                detailLauncher.launch(intent)
            } else {
                view.context.startActivity(intent)
            }
        }
    }
}