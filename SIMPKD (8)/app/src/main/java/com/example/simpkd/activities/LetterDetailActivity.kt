package com.example.simpkd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.simpkd.R
import com.example.simpkd.adapters.DetailVehicleAdapter
import com.example.simpkd.apis.verifySuratTanggungJawab
import com.example.simpkd.models.SuratTanggungJawab
import com.example.simpkd.utils.toPx
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_letter_detail.*

class LetterDetailActivity : AppCompatActivity() {
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
                verifySuratTanggungJawab(root, requestParams, hashMapOf(), this::onSuccessVerify, {})
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_detail)

        val letter: SuratTanggungJawab = intent.getParcelableExtra("letter")!!
        val isVerified = intent.getBooleanExtra("is_verified", false)
        val linearSnapHelper = LinearSnapHelper()
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")

        letter_number.text = letter.no_surat
        letter_date.text = letter.tanggal_surat
        giver_name.text = letter.pemberi.nama
        giver_nik.text = letter.pemberi.nik
        giver_position.text = letter.pemberi.jabatan
        giver_address.text = letter.pemberi.alamat
        giver_phone.text = letter.pemberi.no_hp
        recipient_name.text = letter.penerima.nama
        recipient_nik.text = letter.penerima.nik
        recipient_position.text = letter.penerima.jabatan
        recipient_address.text = letter.penerima.alamat
        recipient_phone.text = letter.penerima.no_hp
        admin_name.text = letter.admin.nama
        admin_nik.text = letter.admin.nik
        admin_position.text = letter.admin.jabatan
        admin_address.text = letter.admin.alamat
        admin_phone.text = letter.admin.no_hp
        recycler_view.apply {
            adapter = DetailVehicleAdapter(this@LetterDetailActivity,
                letter.kendaraan.toList())
            clipToPadding = false
            setPadding(0, 0, (16).toPx(), 0)
        }
        linearSnapHelper.attachToRecyclerView(recycler_view)

        giver_of_responsibility_status.apply {
            text = statusText[letter.status_pemberi]
            setChipBackgroundColorResource(statusColors[letter.status_pemberi])
        }

        recipient_of_responsibility_status.apply {
            text = statusText[letter.status_penerima]
            setChipBackgroundColorResource(statusColors[letter.status_penerima])
        }

        btn_back.setOnClickListener {
            setResult(resultCode)
            finish()
        }
        btn_container.visibility = if (isVerified) View.GONE else View.VISIBLE
        btn_rejected.setOnClickListener { onAction(letter.id, false) }
        btn_approved.setOnClickListener { onAction(letter.id, true) }
    }

    override fun onBackPressed() {
        setResult(resultCode)
        finish()
    }
}