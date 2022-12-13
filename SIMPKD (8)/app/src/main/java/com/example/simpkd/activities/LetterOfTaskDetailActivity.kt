package com.example.simpkd.activities

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.simpkd.R
import com.example.simpkd.adapters.DetailTaskReceiverAdapter
import com.example.simpkd.apis.deleteSuratPerintahTugas
import com.example.simpkd.apis.verifySuratPerintahTugas
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.SuratPerintahTugas
import com.example.simpkd.utils.formatFullDate
import com.example.simpkd.utils.toPx
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_letter_of_task_detail.*

class LetterOfTaskDetailActivity : AppCompatActivity() {
    private lateinit var letterOfTask: SuratPerintahTugas
    private val editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        resultCode = it.resultCode
        onBack()
    }
    private var resultCode = Activity.RESULT_CANCELED

    private fun onSuccessVerify(response: Any?) {
        if (response == null) {
            btn_container.visibility = View.GONE
            resultCode = Activity.RESULT_OK
            onBack()
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
                verifySuratPerintahTugas(root, requestParams, hashMapOf(), this::onSuccessVerify, {})
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun onDelete() {
        btn_delete.submit()
        val requestParams = HashMap<String, String>()
        requestParams["id"] = "${letterOfTask.id}"
        deleteSuratPerintahTugas(root, requestParams, HashMap(), {
            btn_delete.submitFinish()
            resultCode = Activity.RESULT_OK
            onBack()
        }, { btn_delete.submitFinish() })
    }

    private fun onBack() {
        setResult(resultCode)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_of_task_detail)

        letterOfTask = intent.getParcelableExtra("letter")!!
        val me: Pegawai = intent.getParcelableExtra("me")!!
        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")
        val dppaNumber = "DPPA No: ${letterOfTask.dasar.dasar} tanggal ${letterOfTask.dasar.tanggal}"
        val linearSnapHelper = LinearSnapHelper()
        val vehicleType = "Roda ${letterOfTask.kendaraan.model.jumlah_roda}"

        dppa.text = dppaNumber
        letter_number.text = letterOfTask.no_surat
        usage_purpose.text = letterOfTask.tujuan_pemakaian
        start_date.text = formatFullDate(letterOfTask.tanggal_mulai)
        end_date.text = formatFullDate(letterOfTask.tanggal_selesai)
        giver_name.text = letterOfTask.pemberi_tugas.nama
        giver_nik.text = letterOfTask.pemberi_tugas.nik
        giver_position.text = letterOfTask.pemberi_tugas.jabatan
        giver_address.text = letterOfTask.pemberi_tugas.alamat
        giver_phone.text = letterOfTask.pemberi_tugas.no_hp
        recycler_view.apply {
            adapter = DetailTaskReceiverAdapter(this@LetterOfTaskDetailActivity,
                letterOfTask.staf.toList())
            clipToPadding = false
            setPadding(0, 0, (16).toPx(), 0)
        }
        linearSnapHelper.attachToRecyclerView(recycler_view)
        vehicle_registration_number.text = letterOfTask.kendaraan.no_polisi
        vehicle_brand.text = letterOfTask.kendaraan.model.merk
        vehicle_type.text = vehicleType
        vehicle_color.text = letterOfTask.kendaraan.warna
        btn_task_letter.visibility = if (letterOfTask.surat_disposisi.isBlank()) View.GONE else View.VISIBLE

        giver_of_task_status.apply {
            text = statusText[letterOfTask.status_pemberi_tugas]
            setChipBackgroundColorResource(statusColors[letterOfTask.status_pemberi_tugas])
        }
        kasubag_tu_status.apply {
            text = statusText[letterOfTask.status_kasubagTU]
            setChipBackgroundColorResource(statusColors[letterOfTask.status_kasubagTU])
        }

        if (me.id == letterOfTask.pemberi_tugas.id || me.id == letterOfTask.kasubagTU.id) {
            if ((me.id == letterOfTask.pemberi_tugas.id && letterOfTask.status_pemberi_tugas == 0) ||
                (me.id == letterOfTask.kasubagTU.id && letterOfTask.status_pemberi_tugas > 0 &&
                        letterOfTask.status_kasubagTU == 0)) {
                btn_container.visibility = View.VISIBLE
            } else {
                btn_container.visibility = View.GONE
            }
            btn_delete.visibility = View.GONE
            btn_edit.visibility = View.GONE
            btn_edit_placeholder.visibility = View.VISIBLE
        } else {
            btn_container.visibility = View.GONE
            btn_edit_placeholder.visibility = View.GONE
            btn_delete.visibility = View.VISIBLE
            btn_edit.visibility = View.VISIBLE
        }

        if (letterOfTask.status_pemberi_tugas > 0 || letterOfTask.status_kasubagTU > 0) {
            btn_delete.visibility = View.GONE
            btn_edit.visibility = View.GONE
            btn_edit_placeholder.visibility = View.VISIBLE
        }

        btn_back.setOnClickListener { onBack() }
        btn_edit.setOnClickListener {
            val intent = Intent(this, FormLetterOfTaskActivity::class.java)
            intent.putExtra("letter", letterOfTask)
            editLauncher.launch(intent)
        }
        btn_task_letter.setOnClickListener {
            Toast.makeText(this, "Sedang mengunduh surat tugas", Toast.LENGTH_LONG).show()
            val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(letterOfTask.surat_disposisi)
            val request = DownloadManager.Request(uri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            manager.enqueue(request)
        }
        btn_approved.setOnClickListener { onAction(letterOfTask.id, true) }
        btn_rejected.setOnClickListener { onAction(letterOfTask.id, false) }
        btn_delete.setOnClickListener { onDelete() }
    }

    override fun onBackPressed() {
        onBack()
    }
}