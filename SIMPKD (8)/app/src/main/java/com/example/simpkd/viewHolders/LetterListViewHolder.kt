package com.example.simpkd.viewHolders

import android.content.Intent
import android.os.Parcelable
import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.activities.LetterDetailActivity
import com.example.simpkd.activities.LetterOfTaskDetailActivity
import com.example.simpkd.activities.NewsDetailActivity
import com.example.simpkd.activities.VehicleMaintenanceDetailActivity
import com.example.simpkd.models.BeritaAcara
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.utils.toPx
import com.example.simpkd.viewModels.MainViewModel
import kotlinx.android.synthetic.main.layout_letter_news_item.view.*

class LetterListViewHolder(
    private val view: View,
    private val viewModel: MainViewModel,
    private val newsLauncher: ActivityResultLauncher<Intent>?,
    private val letterLauncher: ActivityResultLauncher<Intent>?,
    private val vehicleMaintenanceLauncher: ActivityResultLauncher<Intent>?,
    private val letterOfTaskLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.ViewHolder(view) {
    fun onBindItem(item: Parcelable) {
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")

        when (item) {
            is BeritaAcara -> {
                val status = if (viewModel.me.value!!.role == "Kasubag TU")
                    item.status_kasubag_tu else item.status_pegawai
                val isVerified = (viewModel.me.value!!.id == item.pegawai.id &&
                        listOf(0, 2).contains(item.status_kasubag_tu)) ||
                        (viewModel.me.value!!.id == item.kasubag_tu.id &&
                                item.status_kasubag_tu > 0) ||
                        item.status_pegawai > 0

                view.date.text = item.tanggal_surat
                view.status.apply {
                    text = statusText[status]
                    setChipBackgroundColorResource(statusColors[status])
                }
                view.user.text = item.pegawai.nama
                view.letter_number.visibility = View.GONE
                view.plate_number.text = item.kendaraan.no_polisi
                view.vehicle_model.text = item.kendaraan.model.merk

                view.container.setOnClickListener {
                    val intent = Intent(view.context, NewsDetailActivity::class.java)
                    intent.putExtra("news", item)
                    intent.putExtra("is_verified", isVerified)

                    if (newsLauncher != null) {
                        newsLauncher.launch(intent)
                    } else {
                        view.context.startActivity(intent)
                    }
                }
            }
            is SuratTanggungJawab -> {
                val status = if (viewModel.me.value!!.id == item.pemberi.id)
                    item.status_pemberi else item.status_penerima
                val isVerified = (viewModel.me.value!!.id == item.penerima.id &&
                        listOf(0, 2).contains(item.status_pemberi)) ||
                        (viewModel.me.value!!.id == item.pemberi.id &&
                                item.status_pemberi > 0) ||
                        item.status_penerima > 0

                view.date.text = item.tanggal_surat
                view.status.apply {
                    text = statusText[status]
                    setChipBackgroundColorResource(statusColors[status])
                }
                Glide.with(view).load(R.drawable.ic_documents_svgrepo_com).centerCrop().into(view.illustration)
                view.user.text = item.penerima.nama
                view.letter_number.text = item.no_surat
                view.plate_number.visibility = View.GONE
                view.vehicle_model.visibility = View.GONE

                view.container.setOnClickListener {
                    val intent = Intent(view.context, LetterDetailActivity::class.java)
                    intent.putExtra("letter", item)
                    intent.putExtra("is_verified", isVerified)

                    if (letterLauncher != null) {
                        letterLauncher.launch(intent)
                    } else {
                        view.context.startActivity(intent)
                    }
                }
            }
            is SuratPerintahTugas -> {
                val status: Int = if (item.status_pemberi_tugas == 0 || item.status_kasubagTU == 0) {
                    0
                } else if (item.status_pemberi_tugas == 2 || item.status_kasubagTU == 2) {
                    2
                } else {
                    1
                }

                view.date.text = item.tanggal_mulai
                view.status.apply {
                    text = statusText[status]
                    setChipBackgroundColorResource(statusColors[status])
                }
                Glide.with(view).load(R.drawable.ic_foldertask).centerCrop().into(view.illustration)
                view.user.text = if (viewModel.me.value!!.id == item.pemberi_tugas.id)
                    "Pemberi Tugas" else "Staf Bertugas"
                view.user.setTextColor(ContextCompat.getColor(view.context, R.color.primaryColor))
                view.letter_number.text = item.no_surat
                view.plate_number.text = item.kendaraan.no_polisi
                view.vehicle_model.text = item.kendaraan.model.merk

                view.container.setOnClickListener {
                    val intent = Intent(view.context, LetterOfTaskDetailActivity::class.java)
                    intent.putExtra("me", viewModel.me.value!!)
                    intent.putExtra("letter", item)

                    if (letterOfTaskLauncher != null) {
                        letterOfTaskLauncher.launch(intent)
                    } else {
                        view.context.startActivity(intent)
                    }
                }
            }
            is PemeliharaanKendaraan -> {
                val status: Int = if (item.status_kasubagTU == 0 ||
                    item.status_kepalaUPT == 0 || item.status_pptk == 0) {
                    0
                } else if (item.status_kasubagTU == 2 ||
                    item.status_kepalaUPT == 2 || item.status_pptk == 2) {
                    2
                } else {
                    1
                }

                view.date.text = item.tanggal
                view.status.apply {
                    text = statusText[status]
                    setChipBackgroundColorResource(statusColors[status])
                }
                Glide.with(view).load(R.drawable.ic_car_repair_car_svgrepo_com).centerCrop().into(view.illustration)
                view.user.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0, 0, 0, (16).toPx())
                }
                view.user.text = item.staf.nama
                view.letter_number.text = item.nomor_surat
                view.plate_number.visibility = View.GONE
                view.vehicle_model.visibility = View.GONE

                view.container.setOnClickListener {
                    val intent = Intent(view.context, VehicleMaintenanceDetailActivity::class.java)
                    intent.putExtra("vehicle_maintenance", item)
                    intent.putExtra("me", viewModel.me.value!!)

                    if (vehicleMaintenanceLauncher != null) {
                        vehicleMaintenanceLauncher.launch(intent)
                    } else {
                        view.context.startActivity(intent)
                    }
                }
            }
        }
    }
}