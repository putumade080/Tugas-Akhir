package com.example.simpkd.viewHolders

import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.simpkd.R
import com.example.simpkd.activities.NewsDetailActivity
import com.example.simpkd.models.BeritaAcara
import com.example.simpkd.viewModels.NewsViewModel
import kotlinx.android.synthetic.main.layout_letter_news_item.view.*

class NewsViewHolder(
    private val view: View,
    private val viewModel: NewsViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.ViewHolder(view) {
    fun onBindItem(beritaAcara: BeritaAcara) {
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")
        val status = if (viewModel.me.value!!.role == "Kasubag TU")
            beritaAcara.status_kasubag_tu else beritaAcara.status_pegawai
        val isVerified = (viewModel.me.value!!.id == beritaAcara.pegawai.id &&
                listOf(0, 2).contains(beritaAcara.status_kasubag_tu)) ||
                (viewModel.me.value!!.id == beritaAcara.kasubag_tu.id &&
                        beritaAcara.status_kasubag_tu > 0) ||
                beritaAcara.status_pegawai > 0

        view.date.text = beritaAcara.tanggal_surat
        view.status.apply {
            text = statusText[status]
            setChipBackgroundColorResource(statusColors[status])
        }
        view.user.text = beritaAcara.pegawai.nama
        view.letter_number.visibility = View.GONE
        view.plate_number.text = beritaAcara.kendaraan.no_polisi
        view.vehicle_model.text = beritaAcara.kendaraan.model.merk

        view.container.setOnClickListener {
            val intent = Intent(view.context, NewsDetailActivity::class.java)
            intent.putExtra("news", beritaAcara)
            intent.putExtra("is_verified", isVerified)

            if (detailLauncher != null) {
                detailLauncher.launch(intent)
            } else {
                view.context.startActivity(intent)
            }
        }
    }
}