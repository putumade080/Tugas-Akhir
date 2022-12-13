package com.example.simpkd.viewHolders

import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.activities.LetterOfTaskDetailActivity
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.viewModels.LetterOfTaskViewModel
import kotlinx.android.synthetic.main.layout_letter_news_item.view.*

class LetterOfTaskViewHolder(
    private val view: View,
    private val viewModel: LetterOfTaskViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.ViewHolder(view) {
    fun onBindItem(suratPerintahTugas: SuratPerintahTugas) {
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")
        val status: Int = if (suratPerintahTugas.status_pemberi_tugas == 0 || suratPerintahTugas.status_kasubagTU == 0) {
            0
        } else if (suratPerintahTugas.status_pemberi_tugas == 2 || suratPerintahTugas.status_kasubagTU == 2) {
            2
        } else {
            1
        }

        view.date.text = suratPerintahTugas.tanggal_mulai
        view.status.apply {
            text = statusText[status]
            setChipBackgroundColorResource(statusColors[status])
        }
        Glide.with(view).load(R.drawable.ic_foldertask).centerCrop().into(view.illustration)
        view.user.text = if (viewModel.me.value!!.id == suratPerintahTugas.pemberi_tugas.id)
            "Pemberi Tugas" else "Staf Bertugas"
        view.user.setTextColor(ContextCompat.getColor(view.context, R.color.primaryColor))
        view.letter_number.text = suratPerintahTugas.no_surat
        view.plate_number.text = suratPerintahTugas.kendaraan.no_polisi
        view.vehicle_model.text = suratPerintahTugas.kendaraan.model.merk

        view.container.setOnClickListener {
            val intent = Intent(view.context, LetterOfTaskDetailActivity::class.java)
            intent.putExtra("me", viewModel.me.value!!)
            intent.putExtra("letter", suratPerintahTugas)

            if (detailLauncher != null) {
                detailLauncher.launch(intent)
            } else {
                view.context.startActivity(intent)
            }
        }
    }
}