package com.example.simpkd.viewHolders

import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpkd.R
import com.example.simpkd.activities.LetterDetailActivity
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.viewModels.LetterViewModel
import kotlinx.android.synthetic.main.layout_letter_news_item.view.*

class LetterViewHolder(
    private val view: View,
    private val viewModel: LetterViewModel,
    private val detailLauncher: ActivityResultLauncher<Intent>?
): RecyclerView.ViewHolder(view) {
    fun onBindItem(suratTanggungJawab: SuratTanggungJawab) {
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")
        val status = if (viewModel.me.value!!.id == suratTanggungJawab.pemberi.id)
            suratTanggungJawab.status_pemberi else suratTanggungJawab.status_penerima
        val isVerified = (viewModel.me.value!!.id == suratTanggungJawab.penerima.id &&
                listOf(0, 2).contains(suratTanggungJawab.status_pemberi)) ||
            (viewModel.me.value!!.id == suratTanggungJawab.pemberi.id &&
                    suratTanggungJawab.status_pemberi > 0) ||
            suratTanggungJawab.status_penerima > 0

        view.date.text = suratTanggungJawab.tanggal_surat
        view.status.apply {
            text = statusText[status]
            setChipBackgroundColorResource(statusColors[status])
        }
        Glide.with(view).load(R.drawable.ic_documents_svgrepo_com).centerCrop().into(view.illustration)
        view.user.text = suratTanggungJawab.penerima.nama
        view.letter_number.text = suratTanggungJawab.no_surat
        view.plate_number.visibility = View.GONE
        view.vehicle_model.visibility = View.GONE

        view.container.setOnClickListener {
            val intent = Intent(view.context, LetterDetailActivity::class.java)
            intent.putExtra("letter", suratTanggungJawab)
            intent.putExtra("is_verified", isVerified)

            if (detailLauncher != null) {
                detailLauncher.launch(intent)
            } else {
                view.context.startActivity(intent)
            }
        }
    }
}