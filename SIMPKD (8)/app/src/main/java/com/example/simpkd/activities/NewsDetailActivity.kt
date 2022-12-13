package com.example.simpkd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.simpkd.R
import com.example.simpkd.apis.verifyBeritaAcara
import com.example.simpkd.models.BeritaAcara
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {
    private var resultCode = RESULT_CANCELED

    private fun onSuccessVerify(response: Any?) {
        if (response == null) {
            btn_container.visibility = View.GONE
            resultCode = RESULT_OK
        }
    }

    private fun onAction(id: Long, isAccepted: Boolean) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Konfirmasi Aksi")
            .setMessage("Apakah Anda yakin ingin ${if (isAccepted) "menyetujui" else "menolak"} berkas ini?")
            .setPositiveButton("Yakin") { dialog, _ ->
                val requestParams = HashMap<String, String>()
                requestParams["id"] = "$id"
                requestParams["status"] = if (isAccepted) "1" else "2"
                verifyBeritaAcara(root, requestParams, hashMapOf(), this::onSuccessVerify, {})
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val news: BeritaAcara = intent.getParcelableExtra("news")!!
        val isVerified = intent.getBooleanExtra("is_verified", false)
        val vehicleType = "Roda ${news.kendaraan.model.jumlah_roda}"
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")

        usage_letter_number.text = news.no_surat[0]
        handover_number.text = news.no_surat[1]
        letter_date.text = news.tanggal_surat
        person_responsible_name.text = news.pegawai.nama
        person_responsible_nik.text = news.pegawai.nik
        person_responsible_position.text = news.pegawai.jabatan
        person_responsible_address.text = news.pegawai.alamat
        person_responsible_phone.text = news.pegawai.no_hp
        admin_name.text = news.admin.nama
        admin_nik.text = news.admin.nik
        admin_position.text = news.admin.jabatan
        admin_address.text = news.admin.alamat
        admin_phone.text = news.admin.no_hp
        vehicle_registration_number.text = news.kendaraan.no_polisi
        vehicle_brand.text = news.kendaraan.model.merk
        vehicle_type.text = vehicleType
        vehicle_color.text = news.kendaraan.warna

        kasubag_tu_status.apply {
            text = statusText[news.status_kasubag_tu]
            setChipBackgroundColorResource(statusColors[news.status_kasubag_tu])
        }

        person_responsible_status.apply {
            text = statusText[news.status_pegawai]
            setChipBackgroundColorResource(statusColors[news.status_pegawai])
        }

        btn_back.setOnClickListener {
            setResult(resultCode)
            finish()
        }
        btn_container.visibility = if (isVerified) View.GONE else View.VISIBLE
        btn_rejected.setOnClickListener { onAction(news.id, false) }
        btn_approved.setOnClickListener { onAction(news.id, true) }
    }

    override fun onBackPressed() {
        setResult(resultCode)
        finish()
    }
}