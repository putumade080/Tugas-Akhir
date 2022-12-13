package com.example.simpkd.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.simpkd.R
import com.example.simpkd.adapters.VehicleMaintenanceDetailAdapter
import com.example.simpkd.apis.*
import com.example.simpkd.models.Kendaraan
import com.example.simpkd.models.Pegawai
import com.example.simpkd.models.PemeliharaanKendaraan
import com.example.simpkd.models.PemeliharaanKendaraanDetail
import com.example.simpkd.viewModels.VehicleMaintenanceDetailViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_vehicle_maintenance_detail.*
import org.json.JSONArray

class VehicleMaintenanceDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: VehicleMaintenanceDetailViewModel
    private val addFormLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent = it.data!!
            val vehicle = intent.getParcelableExtra<Kendaraan>("vehicle")
            val problemType = intent.getStringExtra("problem_type")
            val vehicleTotal = intent.getIntExtra("vehicle_total", 1)
            val description = intent.getStringExtra("description")
            viewModel.vehicleDetails.value = arrayOf(*viewModel.vehicleDetails.value!!.toTypedArray(),
                PemeliharaanKendaraanDetail(vehicle!!, problemType!!, vehicleTotal,
                    description!!)
            ).toList()
        }
    }
    private val updateFormLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent = it.data!!
            val position = intent.getIntExtra("position", 0)
            val vehicle = intent.getParcelableExtra<Kendaraan>("vehicle")
            val problemType = intent.getStringExtra("problem_type")
            val vehicleTotal = intent.getIntExtra("vehicle_total", 1)
            val description = intent.getStringExtra("description")
            val vehicleDetails = viewModel.vehicleDetails.value!!.toTypedArray()
            vehicleDetails[position] = PemeliharaanKendaraanDetail(vehicle!!, problemType!!, vehicleTotal,
                description!!)
            viewModel.vehicleDetails.value = vehicleDetails.toList()
        }
    }
    private var resultCode = Activity.RESULT_CANCELED

    private fun onSave(isUpdate: Boolean = false) {
        if (isUpdate) btn_edit.submit() else btn_save.submit()

        val requestParams = HashMap<String, String>()
        requestParams["tanggal"] = viewModel.letterDate.value!!
        requestParams["nomor_surat"] = viewModel.letterNumber.value!!
        requestParams["id_kendaraan"] = JSONArray(viewModel.vehicleDetails.value!!.map { it.kendaraan.id }).toString()
        requestParams["jenis_kerusakan"] = JSONArray(viewModel.vehicleDetails.value!!.map { it.jenis_kerusakan }).toString()
        requestParams["jumlah"] = JSONArray(viewModel.vehicleDetails.value!!.map { it.jumlah }).toString()
        requestParams["keterangan"] = JSONArray(viewModel.vehicleDetails.value!!.map { it.keterangan }).toString()

        if (isUpdate) {
            requestParams["id"] = "${viewModel.id.value}"
            updatePemeliharaanKendaraan(root, requestParams, HashMap(), {
                btn_edit.submitFinish()
                resultCode = Activity.RESULT_OK
                onBack()
            }, { btn_edit.submitFinish() })
        } else {
            createPemeliharaanKendaraan(root, requestParams, HashMap(), {
                btn_save.submitFinish()
                resultCode = Activity.RESULT_OK
                onBack()
            }, { btn_save.submitFinish() })
        }
    }

    private fun onDelete() {
        btn_delete.submit()
        val requestParams = HashMap<String, String>()
        requestParams["id"] = "${viewModel.id.value}"
        deletePemeliharaanKendaraan(root, requestParams, HashMap(), {
            btn_delete.submitFinish()
            resultCode = Activity.RESULT_OK
            onBack()
        }, { btn_delete.submitFinish() })
    }

    private fun onSuccessVerify(response: Any?) {
        if (response == null) {
            btn_approve_container.visibility = View.GONE
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
                verifyPemeliharaanKendaraan(root, requestParams, hashMapOf(), this::onSuccessVerify, {})
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun onBack() {
        setResult(resultCode)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_maintenance_detail)

        val statusColors = listOf(R.color.yellow, R.color.green, R.color.red)
        val statusText = listOf("Menunggu Verifikasi", "Disetujui", "Ditolak")
        val pemeliharaanKendaraan = intent.getParcelableExtra<PemeliharaanKendaraan>("vehicle_maintenance")
        val me = intent.getParcelableExtra<Pegawai>("me")
        var isEditable = if (pemeliharaanKendaraan == null) true else !listOf(pemeliharaanKendaraan.status_pptk,
            pemeliharaanKendaraan.status_kasubagTU, pemeliharaanKendaraan.status_kepalaUPT).any { it > 0 }
        viewModel = ViewModelProvider(this)[VehicleMaintenanceDetailViewModel::class.java]

        if (pemeliharaanKendaraan != null && me != null) {
            viewModel.id.value = pemeliharaanKendaraan.id
            viewModel.letterDate.value = pemeliharaanKendaraan.tanggal
            viewModel.letterNumber.value = pemeliharaanKendaraan.nomor_surat
            viewModel.vehicleDetails.value = pemeliharaanKendaraan.detail.toList()

            pptk_status.apply {
                text = statusText[pemeliharaanKendaraan.status_pptk]
                setChipBackgroundColorResource(statusColors[pemeliharaanKendaraan.status_pptk])
            }
            kasubag_tu_status.apply {
                text = statusText[pemeliharaanKendaraan.status_kasubagTU]
                setChipBackgroundColorResource(statusColors[pemeliharaanKendaraan.status_kasubagTU])
            }
            kepala_upt_status.apply {
                text = statusText[pemeliharaanKendaraan.status_kepalaUPT]
                setChipBackgroundColorResource(statusColors[pemeliharaanKendaraan.status_kepalaUPT])
            }

            if (listOf(pemeliharaanKendaraan.pptk.id, pemeliharaanKendaraan.kasubagTU.id,
                    pemeliharaanKendaraan.kepalaUPT.id).contains(me.id)) {
                if ((me.id == pemeliharaanKendaraan.kasubagTU.id && pemeliharaanKendaraan.status_kasubagTU == 0) ||
                    (me.id == pemeliharaanKendaraan.pptk.id && pemeliharaanKendaraan.status_kasubagTU > 0 &&
                            pemeliharaanKendaraan.status_pptk == 0) ||
                    (me.id == pemeliharaanKendaraan.kepalaUPT.id && pemeliharaanKendaraan.status_pptk > 0 &&
                            pemeliharaanKendaraan.status_kepalaUPT == 0)
                ) {
                    btn_approve_container.visibility = View.VISIBLE
                } else {
                    btn_approve_container.visibility = View.GONE
                }
                isEditable = false
            }
            btn_save.visibility = View.GONE
            btn_add.visibility = if (isEditable) View.VISIBLE else View.GONE
            btn_action_container.visibility = if (isEditable) View.VISIBLE else View.GONE
        } else {
            val vehicle = intent.getParcelableExtra<Kendaraan>("vehicle")
            val problemType = intent.getStringExtra("problem_type")
            val vehicleTotal = intent.getIntExtra("vehicle_total", 1)
            val description = intent.getStringExtra("description")

            viewModel.letterNumber.value = intent.getStringExtra("letter_number")
            viewModel.letterDate.value = intent.getStringExtra("date")
            viewModel.vehicleDetails.value = arrayOf(*viewModel.vehicleDetails.value!!.toTypedArray(),
                PemeliharaanKendaraanDetail(vehicle!!, problemType!!, vehicleTotal,
                description!!)
            ).toList()

            pptk_status.apply {
                text = statusText[0]
                setChipBackgroundColorResource(statusColors[0])
            }
            kasubag_tu_status.apply {
                text = statusText[0]
                setChipBackgroundColorResource(statusColors[0])
            }
            kepala_upt_status.apply {
                text = statusText[0]
                setChipBackgroundColorResource(statusColors[0])
            }
        }

        letter_number.text = viewModel.letterNumber.value
        letter_date.text = viewModel.letterDate.value

        viewModel.vehicleDetails.observe(this, {
            recycler_view.adapter = VehicleMaintenanceDetailAdapter(this, it, viewModel,
                updateFormLauncher, isEditable)
        })

        btn_add.setOnClickListener {
            val intent = Intent(this, FormVehicleMaintenanceActivity::class.java)
            intent.putExtra("vehicle_details",
                arrayListOf(*viewModel.vehicleDetails.value!!.toTypedArray()))
            addFormLauncher.launch(intent)
        }
        btn_save.setOnClickListener { onSave() }
        btn_delete.setOnClickListener { onDelete() }
        btn_edit.setOnClickListener { onSave(isUpdate = true) }
        btn_approved.setOnClickListener { onAction(pemeliharaanKendaraan!!.id, true) }
        btn_rejected.setOnClickListener { onAction(pemeliharaanKendaraan!!.id, false) }
        btn_back.setOnClickListener { onBack() }
    }
}